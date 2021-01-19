package requireproject.service.role.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import requireproject.common.UUIDUtils;
import requireproject.repository.role.mapper.RoleMapper;
import requireproject.service.role.RoleService;
import requireproject.service.role.dao.Role;

import java.util.Date;
import java.util.List;

/**
 * @Author xupengfei
 * @Descriptions 管理员用户接口实现类
 * @Date 2020/11/25 11:13
 * @Version: V1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * @Author
     * @Description
     * @Date 2020/11/25 18:04
     **/
    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }


    /**
     * @Author
     * @Description 添加角色
     * @Date 2020/11/25 18:04
     **/
    @Override
    public Role addRole(Role role) {
        //添加时候生成主键id
        role.setId(UUIDUtils.getUUID());
        //判断添加角色是角色对应的菜单权限  1为管理员  2为开发者 3普通用户
        if(role.getMenuPerm().equals("1")){
            role.setMenuPerm("admin");
        } else if(role.getMenuPerm().equals("2")){
            role.setMenuPerm("editor");
        }else if (role.getMenuPerm().equals("3")){
            role.setMenuPerm("common");
        }
        role.setCreateTime(new Date());
        roleMapper.addRole(role);
        return role;
    }
    /**
     * @Author
     * @Description 修改角色
     * @Date 2020/11/25 18:04
     **/
    @Override
    public Boolean updateRole(Role role) {
        roleMapper.updateRole(role);
        return true;
    }


    /**
     * @Author
     * @Description 修改角色
     * @Date 2020/11/25 18:04
     **/
    @Override
    public Boolean deleteRole(String id) {
        roleMapper.deleteRole(id);
        return true;
    }




}
