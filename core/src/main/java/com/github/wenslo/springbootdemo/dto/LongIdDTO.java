package com.github.wenslo.springbootdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-19 13:02
 * @description
 */
public class LongIdDTO implements Serializable {
    protected Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedAt;
}
