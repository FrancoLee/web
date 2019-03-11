package com.lx.animation.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by root on 18-8-5.
 */
@Component
public class AddressUtil {
    public String ipToString(long ip) {
        StringBuffer strIp = new StringBuffer("");
        strIp.append(String.valueOf((ip & 0xff000000) >>> 24));
        strIp.append(".");
        strIp.append(String.valueOf((ip & 0x00ff0000) >>> 16));
        strIp.append(".");
        strIp.append(String.valueOf((ip & 0x0000ff00) >>> 8));
        strIp.append(".");
        strIp.append(String.valueOf(ip & 0x000000ff));
        return strIp.toString();
    }

    public long stringToLong(String strIp) {
        if ("0".equals(strIp.substring(0, 1)))
            return 0;
        long ip = 0;
        String[] str = strIp.split("\\.");
        for (int i = 0; i < str.length; i++) {
            ip += Long.valueOf(str[i]) << (24 - (i * 8));
        }
        return ip;
    }

    public String getIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        
        return ip;
    }
}
