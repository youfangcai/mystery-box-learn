package org.example.mysteryboxlearn.common.domain.vo.response;

import lombok.Data;
import org.example.mysteryboxlearn.common.exception.ResultCode;

@Data
public class ApiResult<T> {

    private Boolean success;

    private Integer errorCode;

    private String errorMsg;

    private T data;

    public static <T> ApiResult<T> success(){
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setSuccess(true);
        apiResult.setData(null);
        return apiResult;
    }
    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setSuccess(true);
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> fail(Integer errorCode, String msg){
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setSuccess(false);
        apiResult.setErrorCode(errorCode);
        apiResult.setErrorMsg(msg);
        return apiResult;
    }

    public static <T> ApiResult<T> fail(ResultCode resultCode){
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setSuccess(false);
        apiResult.setErrorCode(resultCode.getCode());
        apiResult.setErrorMsg(resultCode.getName());
        return apiResult;
    }
}
