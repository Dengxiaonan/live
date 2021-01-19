package requireproject.service.role.dao;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Description: 菜单实体
 */
@Data
public class Menu {

    /**
     * 用户id
     */
    private String id;

    /**
     * 菜单名称
     */
    private String menuName;


    /**
     * 备注
     */
    private String menuRemark;

    /**
     * 父级id
     */
    private String pid;

    /**
     * 菜单的角色（角色有固定的菜单）
     */
    private String menuRole;
    /**
     * 路由
     */
    private String route;

    private String path;
    private String component;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private List<Menu> children;

}
