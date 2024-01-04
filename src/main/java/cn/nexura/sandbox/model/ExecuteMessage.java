package cn.nexura.sandbox.model;

import lombok.Data;

/**
 * 进程执行信息
 * @author PeiYP
 * @since 2024年01月04日 8:58
 */
@Data
public class ExecuteMessage {

    private Integer exitValue;

    private String message;

    private String errorMessage;

    private Long time;

    private Long memory;
}

