package requireproject.service.role.dao;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 分组实体
 */
@Data
public class Role {

    /**
     * 用户id
     */
    private String id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色对应英文code（对应menu表menu_role）
     */
    private String menuPerm;
    /**
     * 角色备注
     */
    private String roleRemark;
    /**
     * 角色状态  0正常1为禁用
     */
    private String state;


    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
