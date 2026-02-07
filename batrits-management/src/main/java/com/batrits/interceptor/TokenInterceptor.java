package com.batrits.interceptor;

import com.batrits.utils.CurrentHolder;
import com.batrits.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        if(requestURI.contains("/login")){
//            log.info("登录请求，放行...");
//            return true;
//        }

        String token = request.getHeader("token");
        if(token==null||token.isEmpty()){
            log.info("令牌为空，拦截...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try{
            Claims claims = JwtUtils.parseToken(token);
            Integer id = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(id);
            log.info("当前操作的用户的ID是{}, 将其存入ThreadLocal中", id);
        }catch(Exception e){
            log.info("令牌非法，拦截...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        log.info("令牌合法，放行...");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentHolder.remove();

    }
}
