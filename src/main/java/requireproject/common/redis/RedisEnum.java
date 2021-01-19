package requireproject.common.redis;

/**
 * @Author xupengfei
 * @Descriptions Redis公共枚举类
 * @Date 2020/11/25 10:51
 * @Version: V1.0
 **/
public enum RedisEnum {
    SEC("数据权限标识")
    ,MENU("菜单权限标识"),
    TOKEN("TOKEN");

    private String name;
    RedisEnum(String name){
        this.name=name;
    }
}
