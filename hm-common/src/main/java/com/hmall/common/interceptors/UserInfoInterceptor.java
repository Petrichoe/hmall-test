package com.hmall.common.interceptors;


import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取登录用户
        String userinfo = request.getHeader("user-info");

        //2.判断是否获取用户，如果有，存入ThreadLocal
        if (StrUtil.isNotBlank(userinfo)){
            UserContext.setUser(Long.valueOf(userinfo));
        }
        //3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         UserContext.removeUser();
    }
}
