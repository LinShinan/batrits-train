package com.batrits.filter;

import com.batrits.utils.CurrentHolder;
import com.batrits.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns="/*")
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();

        if(requestURI.contains("/login")){
            log.info("登录请求，放行...");
            filterChain.doFilter(request,response);
            return;
        }

        String token = request.getHeader("Token");
        if(token==null||token.isEmpty()) {
            log.info("令牌为空，拦截...");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try{
            Claims claims = JwtUtils.parseToken(token);
            Integer id = (Integer) claims.get("id");
//            Integer id = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(id);

            log.info("当前操作的用户的ID是{}, 将其存入ThreadLocal中", id);
        }catch(Exception e){
            log.info("令牌非法，拦截...");
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.info("令牌合法，放行...");
        //防止参数脏数据
        try{
            filterChain.doFilter(request,response);
        }finally{
            CurrentHolder.remove();
        }

    }
}
