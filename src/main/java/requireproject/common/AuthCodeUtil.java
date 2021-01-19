package requireproject.common;


import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import requireproject.common.exception.AuthCodeConstant;
import requireproject.common.redis.RedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:  授权码使用工具ize
 */
@Component
@Slf4j
public class AuthCodeUtil {

    @Resource
    RedisUtil redisUtil;

    //生成token
    public static String getToken(){

        return UUIDUtil.getRandom32PK();
    }
    //校验登录token
    public UseDetailsDTO checkLoginToken(String authCode){
        //查询登陆文件夹
        String json = redisUtil.get(AuthCodeConstant.AUTH_CODE_SAVE+authCode).toString();
        if(null == json){
            return null;
        }
        UseDetailsDTO useDetailsDTO = JSON.parseObject(json,UseDetailsDTO.class);
        return useDetailsDTO;
    }

    public void checkLog(String userId){

        //获取授权码
        String code = redisUtil.get(AuthCodeConstant.AUTH_CODE_LOGIN_SAVE+userId).toString() == null?"":redisUtil.get(AuthCodeConstant.AUTH_CODE_LOGIN_SAVE+userId).toString();
        if(!StringUtils.isEmpty(code)){
            String json = redisUtil.get(AuthCodeConstant.AUTH_CODE_SAVE+code).toString();
            UseDetailsDTO useDetailsDTO = JSON.parseObject(json,UseDetailsDTO.class);
            useDetailsDTO.setStatus(1);
            redisUtil.set(AuthCodeConstant.AUTH_CODE_SAVE+code, JSON.toJSONString(useDetailsDTO));
        }

    }




    public boolean deleteUserKey(HttpServletRequest request){
        try {
            //删除用户 redis
            final String authCode = request.getHeader(AuthCodeConstant.AUTH_HEADER_KEY);
            UseDetailsDTO useDetailsDTO = checkLoginToken(authCode);
            String code = redisUtil.get(AuthCodeConstant.AUTH_CODE_LOGIN_SAVE+useDetailsDTO.getUserId()).toString();
            redisUtil.del(AuthCodeConstant.LOGIN_TIMES_SAVE+useDetailsDTO.getUserId());
            redisUtil.del(AuthCodeConstant.AUTH_CODE_LOGIN_SAVE+code);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
