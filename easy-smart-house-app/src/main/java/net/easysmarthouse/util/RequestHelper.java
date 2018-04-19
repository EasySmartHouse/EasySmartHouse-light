package net.easysmarthouse.util;

import javax.servlet.http.HttpServletRequest;

public class RequestHelper {

    private RequestHelper() {
    }

    public static String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
