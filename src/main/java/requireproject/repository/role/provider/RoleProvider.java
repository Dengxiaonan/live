package requireproject.repository.role.provider;


import io.micrometer.core.instrument.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import requireproject.service.role.dao.Role;

/**
 * @author ldy
 * @date
 * @email
 *  //            LEFT_OUTER_JOIN("ehr_employee ee on ee.id = ees.id");
 */
public class RoleProvider extends SQL {

//    public String listByParamVO(ParamVO paramVO) {
//        return new SQL() {{
//
//            SELECT("id id,file_name fileName,file_size fileSize,file_founder fileFounder,file_type fileType,url,create_time createTime");
//            FROM("user_file ");
//            WHERE("file_delete = 0 ");
//            if (!StringUtils.isEmpty(paramVO.getName())) {
//                WHERE("file_name like concat('%',#{name},'%')");
//            }
//
//            if (StringUtils.isEmpty(paramVO.getPid())) {
//                WHERE("pid = '0'");
//            }
//
//            if (!StringUtils.isEmpty(paramVO.getPid())) {
//                WHERE("pid = #{pid}");
//            }
//            if (!StringUtils.isEmpty(paramVO.getDel())) {
//                WHERE("delete = #{del}");
//            }
//
//            if (!StringUtils.isEmpty(paramVO.getType())) {
//                WHERE("file_type = #{type}");
//            }
//            ORDER_BY("file_type ASC ");
//        }}.toString();
//    }

    public String addRole(Role role) {
        String sql = new SQL() {{
            INSERT_INTO("role");
            VALUES("id", "#{id}");
            VALUES("role_name", "#{roleName}");
            VALUES("role_remark", "#{roleRemark}");
            VALUES("menu_perm", "#{menuPerm}");
            VALUES("create_time", "#{fileSize}");
        }}.toString();
        return sql;
    }

    public String updateRole(Role role) {
        return new SQL() {{
            UPDATE("role");
            if(StringUtils.isNotBlank(role.getRoleName())){

                SET("role_name = #{roleName}");
            }
            if(StringUtils.isNotBlank(role.getRoleRemark())){

                SET("role_remark = #{roleRemark}");
            }
            if(StringUtils.isNotBlank(role.getMenuPerm())){

                SET("menu_perm = #{menuPerm}");
            }
            if(StringUtils.isNotBlank(role.getState())){

                SET("state = #{state}");
            }
            WHERE(" id = #{id}");
        }}.toString();
    }




}
