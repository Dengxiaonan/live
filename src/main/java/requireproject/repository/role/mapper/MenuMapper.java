package requireproject.repository.role.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import requireproject.service.role.dao.Menu;

import java.util.List;

@Mapper
public interface MenuMapper {


    @Select("SELECT  `menu_name` menuName ,route , `path`  from menu where menu_role = #{auth} ORDER BY sort ASC")
    List<Menu> getRoleList(String auth);

    @Select("SELECT r.menu_perm menuPerm from sys_user u LEFT JOIN role r ON r.id = u.role_id WHERE u.uuid = #{userId}")
    String getUserAuthCode(String userId);


}
