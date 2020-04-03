package com.java.foodshop.interceptor;

import com.java.foodshop.annotation.UnInterception;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ControllerInterceptor implements HandlerInterceptor {
    //存放鉴权信息的Header名称，默认是Authorization
    private String httpHeaderName = "Authorization";

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    //存放登录用户模型Key的Request Key
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";


    /**
     * 在所有http请求进来之前
     * 所有方法落到这个上面去校验
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //解决拦截器跨域问题
        // 将data数据进行响应
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        // 支持跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String methodName = method.getName();
        log.info("====拦截到了方法：{}，在该方法执行之前执行====", methodName);
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(UnInterception.class)) {
            log.info("有进行这步");
            UnInterception passToken = method.getAnnotation(UnInterception.class);
            if (passToken.required()) {
                return true;
            }
        }


        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        StringRedisTemplate stringRedisTemplate = (StringRedisTemplate) factory.getBean("stringRedisTemplate");
        log.info("进入拦截器-{}", request.getHeader("token"));

        //从请求头中拿得到token，则登陆过
        String token = request.getHeader("token");
        String loginname = stringRedisTemplate.opsForValue().get(token);
        log.info("Get loginname from Redis is {}", loginname);
        if (loginname != null && !loginname.trim().equals("")) {
            Long tokeBirthTime = Long.valueOf(stringRedisTemplate.opsForValue().get(token + loginname));
            log.info("token Birth time is: {}", tokeBirthTime);
            Long diff = System.currentTimeMillis() - tokeBirthTime;
            log.info("token is exist : {} ms", diff);
            //过期时限，如果还剩10s还没登录，就会报401
            if (diff > 30000) {
                stringRedisTemplate.expire(loginname, 60*5, TimeUnit.SECONDS);
                stringRedisTemplate.expire(token, 60*5, TimeUnit.SECONDS);
                log.info("Reset expire time success!");
                Long newBirthTime = System.currentTimeMillis();
                log.info("token new Birth time is: {}", newBirthTime);
                stringRedisTemplate.opsForValue().set(token + loginname, newBirthTime.toString());
                return true;
            }else {
                JSONObject jsonObject = new JSONObject();

                PrintWriter out = null;
                try {
                    response.setStatus(unauthorizedErrorCode);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                    jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    out = response.getWriter();
                    out.println(jsonObject);

                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                }

            }

        }

        response.setStatus(unauthorizedErrorCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        request.setAttribute(REQUEST_CURRENT_KEY, null);

        return false;

//    @Override
//      public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        String methodName = method.getName();
//        log.info("====拦截到了方法：{}，在该方法执行之前执行====", methodName);
//
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(UnInterception.class)) {
//            UnInterception passToken = method.getAnnotation(UnInterception.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
//
//        // 判断用户有没有登陆，一般登陆之后的用户都有一个对应的token
//        String token = request.getHeader("token");
//        log.info("token:",token);
//        //String token = request.getParameter("token");
//        if (null == token || "".equals(token)) {
//            log.info("用户未登录，没有权限执行……请登录");
//            return false;
//        }
//        // 返回true才会继续执行，返回false则取消当前请求
//        return true;
//    }

    }
}
