package com.thundersdata.backend.common.utils;

/**
 * @Description:
 */
public class ResultCode {
    /**
     * 请求成功
     */
    public static final int SUCCESS = 20000;
    /**
     * 请求参数access_token错误
     */
    public static final int ACCESS_TOKEN_ERROR = 40001;
    /**
     * 不合法的文件大小
     */
    public static final int FILE_SIZE_ERROR = 40002;
    /**
     * 不支持的文件类型
     */
    public static final int FILE_TYPE_ERROR = 40003;
    /**
     * 请求参数缺失
     */
    public static final int PARAMETER_MISSING_ERROR = 40004;
    /**
     * 没有权限
     */
    public static final int NOT_AUTHORIZED = 40005;
    /**
     * 请求被拒绝
     */
    public static final int REQUEST_FORBIDDEN = 40300;
    /**
     * 请求不存在或错误
     */
    public static final int REQUEST_NOT_FOUND = 40400;
    /**
     * 请求超时
     */
    public static final int REQUEST_TIMEOUT = 40800;
    /**
     * 请求失败
     */
    public static final int FAIL = 50000;
    /**
     * 服务不可用
     */
    public static final int SERVICE_UNAVAILABLE = 50300;
    /**
     * 数据库操作失败
     */
    public static final int DB_OPERATION_FAIL = 50001;
}
