package com.lightning.portal.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author gyf
 * @Date 2021-02-23 17:16
 * @ClassName LoginInterceptor
 * @Description
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("请求路径为",request.getRequestURI());
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser!=null){
            return true;
        }
        request.setAttribute("msg","请先登录！");
        request.getRequestDispatcher("/login").forward(request,response);
        return false;
    }
}
