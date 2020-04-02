package com.august.core.bean;

public class Constant {
    //权限不足
    public static final Integer UNAUTHORIZED=-1;
    //认证失败
    public static final Integer UNAHTUENTICATED=-2;
    //参数错误
    public static final Integer PARAMETER_ERROR=-3;

    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "orderField";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";
}
