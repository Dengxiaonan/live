package requireproject.repository.sysuser.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * @Author ldy
 * @Descriptions 用户信息实体类
 * @Date 2020/11/25 11:36
 * @Version: V1.0
 **/
@Data
public class SysUserEntity {
    private String uuid;
    private String userId; //用户ID
    private String deptId; //部门ID
    private String units;//单位
    private String userName; //用户账号
    private String nickName; //用户昵称
    private String userType; //用户类型（00系统用户）
    private String email; //用户邮箱
    private String phoneNumber; //手机号码
    private String sex; //用户性别（0男 1女 2未知）
    private String avatar; //头像地址
    private String password; //密码
    private int status; //帐号状态（0正常 1停用）
    private int delFlag; //删除标志（0代表存在 2代表删除）
    private String loginIp; //最后登录IP
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loginDate; //最后登录时间
    private String createBy; //创建者
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime; //创建时间
    private String updateBy; //更新者
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime; //更新时间
    private String remark; //备注
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;//注册时间
    private int militaryId;//军官证号码
    private String roleId;//角色id
    private String roleName;//角色名称
    private String roleStatus;//角色状态0正常1禁用
    private String menuPerm;
    private String route;
}
