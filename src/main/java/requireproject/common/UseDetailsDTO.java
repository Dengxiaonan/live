package requireproject.common;


import lombok.Data;

/**
 * @Description: 初始化用户信息
 */
@Data
public class UseDetailsDTO {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 授权码失效原因 0: 正常        -1: 二次登陆失效
     */
    private Integer status=0;


}
