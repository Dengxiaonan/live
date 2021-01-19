package requireproject.common.exception;

/**
 * @author ldy
 * @version 1.0
 * @className AuthCodeConstant
 * @date 2020/11/16
 * @description AuthCode 相关常量
 **/
public class AuthCodeConstant {

    /**
     * 校验头部key
     */
    public static String AUTH_HEADER_KEY = "authorization";
    /**
     * 授权码未登录存放位置\
     */
    public static String AUTH_CODE_TOKEN = "require:authCode:token:";
    /**
     * 授权码登录存放位置\
     */
    public static String AUTH_CODE_SAVE = "require:authCode:save:";

    /**
     * 授权码登录存放位置
     */
    public static String AUTH_CODE_LOGIN_SAVE = "require:authCode:save:login:";

    /**
     * 户型库后台权限路径
     */
    public static String BOSS_AUTH_CODE_SAVE = "require:hss:auth:save:";

    /**
     * 未登录时间
     */
    public static Integer UNLOGIN_EX = 2*24*60*60;

    /**
     * 登录时间
     */
    public static Integer Login_EX = 30*24*60*60;

    /**
     * 登录次数限制
     */
    public static Integer LOGIN_TIMES = 100;

    /**
     * 登录用户次数存放位置
     */
    public static String LOGIN_TIMES_SAVE = "require:user:login:times:";


}
