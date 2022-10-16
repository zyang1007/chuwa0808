package com.chuwa.redbook.AOP;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Controller层的日志封装类
 * Created by macro on 2018/4/26.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class WebLog {

    private String description;  // description of operation
    private String username; // user name
    private Long startTime; // time of operation starts
    private Integer spendTime; // time spend
    private String basePath; // root path
    private String uri; // URI
    private String url; // URL
    private String method; // request type
    private String ip; // ip address
    private Object parameter; // request parameter
    private Object result;  // result that will be returned
}
