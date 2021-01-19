package requireproject.common.exception;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import requireproject.common.UseDetailsDTO;
import requireproject.common.redis.RedisUtil;
import requireproject.common.remind.JsonResult;
import requireproject.repository.sysuser.entity.SysUserEntity;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ldy
 * @version 1.0
 * @className CheckLoginIntercepter
 * @date 2020/12/10 10:17
 * @description 需要登录的接口校验
 **/
@Slf4j
@Component
public class CheckLoginIntercepter extends HandlerInterceptorAdapter  {


    @Resource
    RedisUtil redisUtil;
    @Autowired
    private HttpServletRequest request1;

    private static Logger logger = LoggerFactory.getLogger(SysUserEntity.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("===========================================");
        response.setCharacterEncoding("UTF-8");
        //获取token
        final String authHeader = request.getHeader(AuthCodeConstant.AUTH_HEADER_KEY);
        if (StringUtils.isEmpty(authHeader)) {
            return false;
        }
        String json = redisUtil.get(AuthCodeConstant.AUTH_CODE_SAVE+authHeader).toString();
        if(null == json){
            return false;
        }
        UseDetailsDTO useDetailsDTO = JSON.parseObject(json,UseDetailsDTO.class);
        //校验授权码是否登录
        if(useDetailsDTO == null || Integer.valueOf(useDetailsDTO.getUserId()) == 0){
            response.getWriter().println(JSON.toJSONString(JsonResult.success(AuthCodeConstant.UNLOGIN_EX)));
            return false;
        }
        //校验授权码状态
        if(null != useDetailsDTO.getStatus() && useDetailsDTO.getStatus() != 0){
            //删除无用授权码
            redisUtil.del(AuthCodeConstant.AUTH_CODE_SAVE ,authHeader);
            response.getWriter().println(JSON.toJSONString(JsonResult.success(AuthCodeConstant.AUTH_CODE_LOGIN_SAVE)));
            return false;
        }
        // 传递所需信息
        request.setAttribute("useDetailsDTO", useDetailsDTO);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
