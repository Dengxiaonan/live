package requireproject.service.role;


import requireproject.service.role.dao.Role;

import java.util.List;

/**
 *
 * 用户信息接口类
 */


public interface RoleService {

    List<Role> getRoleList();

    Role addRole(Role role);

    Boolean updateRole(Role role);

    Boolean deleteRole(String id);
}
