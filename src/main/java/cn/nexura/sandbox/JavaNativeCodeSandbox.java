package cn.nexura.sandbox;

import cn.nexura.sandbox.model.ExecuteCodeRequest;
import cn.nexura.sandbox.model.ExecuteCodeResponse;
import cn.nexura.sandbox.model.ExecuteMessage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * java原生实现
 * @author PeiYP
 * @since 2024年01月04日 12:23
 */
@Component
public class JavaNativeCodeSandbox extends JavaCodeSandboxTemplate{

    @Override
    protected File saveCodeToFile(String code) {
        return super.saveCodeToFile(code);
    }

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

    @Override
    public ExecuteCodeResponse doExecute(ExecuteCodeRequest executeCodeRequest) {
        return super.doExecute(executeCodeRequest);
    }
}
