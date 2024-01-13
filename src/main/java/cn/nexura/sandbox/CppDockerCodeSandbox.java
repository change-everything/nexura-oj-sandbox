package cn.nexura.sandbox;

import cn.nexura.sandbox.model.ExecuteCodeResponse;
import cn.nexura.sandbox.model.ExecuteMessage;

import java.io.File;
import java.util.List;

/**
 * @author peiYP
 * @create 2024-01-13 21:48
 **/
public class CppDockerCodeSandbox extends CodeSandboxTemplate {

    @Override
    protected ExecuteMessage compileFile(File userCodeFile) {
        return super.compileFile(userCodeFile);
    }

    @Override
    protected List<ExecuteMessage> runFile(File userCodeFile, List<String> inputList) {
        return super.runFile(userCodeFile, inputList);
    }

    @Override
    protected ExecuteCodeResponse getOutputResponse(List<ExecuteMessage> executeMessageList) {
        return super.getOutputResponse(executeMessageList);
    }

    @Override
    protected boolean deleteFile(File userCodeFile) {
        return super.deleteFile(userCodeFile);
    }
}
