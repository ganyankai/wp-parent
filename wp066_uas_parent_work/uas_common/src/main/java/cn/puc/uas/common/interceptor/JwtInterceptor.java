package cn.puc.uas.common.interceptor;

import cn.puc.uas.common.entity.ResultCode;
import cn.puc.uas.common.exception.CommonException;
import cn.puc.uas.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  自定义拦截器
 *  preHandle:进入到控制器方法之前执行的内容
 *  postHandle:执行控制器方法之后执行的内容
 *  afterCompletion:响应结束之前执行的内容
 *
 *  1.简化获取token数据的代码编写
 *      统一的用户权限校验(是否登录)
 *  2.判断用户是否具有访问api的权限
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer")){
            //获取token数据
            String token = authorization.replace("Bearer ", "");
            //解析token获取claims
            Claims claims = jwtUtils.parseJwt(token);
            if (claims!=null){
                //通过claims获取到当前用户可访问api权限列表
                String apis = (String) claims.get("apis");  //api-user-delete,api-user-update
                //通过handler
                HandlerMethod  h = (HandlerMethod) handler;
                RequestMapping annotation = h.getMethodAnnotation(RequestMapping.class);
                String name = annotation.name();
                //判断当前用户是否具有当前请求的权限
                if(apis.contains(name)){
                    request.setAttribute("user_claims",claims);
                    return true;
                }else {
                    throw new CommonException(ResultCode.UNAUTHORISE);
                }
//                request.setAttribute("user_claims",claims);
//                return true;
            }
        }
        throw new CommonException(ResultCode.UNAUTHORISE);
//        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
