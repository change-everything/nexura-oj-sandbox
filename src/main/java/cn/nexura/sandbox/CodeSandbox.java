package cn.nexura.sandbox;


import cn.nexura.sandbox.model.ExecuteCodeRequest;
import cn.nexura.sandbox.model.ExecuteCodeResponse;

/**
 * @author peiYP
 * @create 2023-12-31 17:54
 **/
public interface CodeSandbox {

    ExecuteCodeResponse doExecute(ExecuteCodeRequest executeCodeRequest);
}

