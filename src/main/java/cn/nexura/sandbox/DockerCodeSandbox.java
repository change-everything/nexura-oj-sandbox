package cn.nexura.sandbox;

import cn.hutool.core.util.ArrayUtil;
import cn.nexura.sandbox.model.ExecuteCodeRequest;
import cn.nexura.sandbox.model.ExecuteCodeResponse;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.*;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.ExecStartResultCallback;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author PeiYP
 * @since 2024年01月03日 16:50
 */
public class DockerCodeSandbox implements CodeSandbox {

    public static boolean FIRST_INIT = false;

    public static void main(String[] args) {

        DockerCodeSandbox dockerCodeSandbox = new DockerCodeSandbox();
        dockerCodeSandbox.doExecute(null);

    }

    @Override
    public ExecuteCodeResponse doExecute(ExecuteCodeRequest executeCodeRequest) {

        ArrayList<String> inputList = new ArrayList<>();

        // 创建docker客户端
        DockerClient dockerClient = DockerClientBuilder.getInstance().build();

        // 指定要拉取的镜像
        String image = "openjdk:8-alpine";

        // 判断是否已经初始化过
        if (FIRST_INIT) {
            // 第一次 下载并拉取镜像
            PullImageCmd pullImageCmd = dockerClient.pullImageCmd(image);
            PullImageResultCallback pullImageResultCallback = new PullImageResultCallback() {
                @Override
                public void onNext(PullResponseItem item) {
                    System.out.println("下载镜像: " + item.getStatus());
                    super.onNext(item);
                }
            };
            try {
                pullImageCmd.exec(pullImageResultCallback)
                        .awaitCompletion();
                // 已经初始化过了
                FIRST_INIT = false;
            } catch (InterruptedException e) {
                System.out.println("拉取镜像异常");
                throw new RuntimeException(e);
            }

            System.out.println("下载完成");
        }

        // 创建容器
        CreateContainerCmd containerCmd = dockerClient.createContainerCmd(image);
        HostConfig hostConfig = new HostConfig();
        hostConfig.setBinds(new Bind("", new Volume("/app")));
        hostConfig.withMemory(100 * 1000 * 1000L);
        hostConfig.withCpuCount(1L);

        // 可交互
        CreateContainerResponse createContainerResponse = containerCmd
                .withHostConfig(hostConfig)
                .withAttachStdin(true)
                .withAttachStderr(true)
                .withAttachStdout(true)
                .withTty(true)
                .exec();
        System.out.println(createContainerResponse);
        String containerId = createContainerResponse.getId();

        // docker exec keen_blackwell java -cp /app Main 1 3
        for (String inputArgs : inputList) {
            String[] inputArgsArray = inputArgs.split(" ");
            String[] cmdArray = ArrayUtil.append(new String[] {"java", "-cp", "/app", "Main"}, inputArgsArray);
            ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerId)
                    .withCmd(cmdArray)
                    .withAttachStdin(true)
                    .withAttachStderr(true)
                    .withAttachStdout(true)
                    .exec();
            System.out.println("创建执行命令: " + execCreateCmdResponse);
            String execId = execCreateCmdResponse.getId();

            ExecStartResultCallback resultCallback = new ExecStartResultCallback() {

                @Override
                public void onNext(Frame frame) {
                    // TODO: 2024/1/3 执行命令
                    super.onNext(frame);
                }
            };
            dockerClient.execStartCmd(execId).exec(resultCallback);
        }


        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        return executeCodeResponse;
    }
}
