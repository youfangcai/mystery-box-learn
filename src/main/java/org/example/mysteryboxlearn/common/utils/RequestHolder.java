package org.example.mysteryboxlearn.common.utils;

import org.example.mysteryboxlearn.common.domain.dto.RequestInfoDto;

// 处理请求，主要是获得uid
public class RequestHolder {
    private static final ThreadLocal<RequestInfoDto> threadLocal = new ThreadLocal();

    private RequestHolder(){}

    public static RequestInfoDto getRequestInfo(){
        if (threadLocal.get() == null){
            throw new IllegalStateException("RequestHolder has not been initialized");
        }
        return threadLocal.get();
    }

    // 快捷方法：获取当前用户ID
    public static String getCurrentUid() {
        return getRequestInfo().getUid();
    }

    // 快捷方法：获取客户端IP
    public static String getClientIp() {
        return getRequestInfo().getIp();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
