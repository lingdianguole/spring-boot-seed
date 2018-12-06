package com.company.project.configurer;

import java.util.ArrayList;
import java.util.List;

public class DataSourceContextHolder {
    public static final List<Object> supportList = new ArrayList<>();
    // 线程本地环境
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setType(String type) {
        contextHolder.set(type);
    }

    public static String getType() {
        return (contextHolder.get());
    }

    public static void clear() {
        contextHolder.remove();
    }

    public static boolean support(String type) {
        return supportList.contains(type);
    }

}
