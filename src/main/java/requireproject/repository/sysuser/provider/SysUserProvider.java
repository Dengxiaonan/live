package requireproject.repository.sysuser.provider;


import io.micrometer.core.instrument.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import requireproject.repository.sysuser.entity.SysUserEntity;
import requireproject.service.sysuser.request.ParamVO;

/**
 * @author ldy
 * @date
 * @email
 *  //            LEFT_OUTER_JOIN("ehr_employee ee on ee.id = ees.id");
 */
public class SysUserProvider extends SQL {
    public String getUserList(ParamVO paramVO) {
        return new SQL() {{

            SELECT("su.uuid uuid,su.units,su.user_name userName,su.nick_name nickName,su.user_type userType,su.email,su.phone_number phoneNumber,su.sex,su.avatar,su.`status`,su.remark,r.state roleStatus,r.role_name roleName,su.create_time createTime");
            FROM("sys_user  su");
            LEFT_OUTER_JOIN("role r on r.id = su.role_id ");
            WHERE("su.del_flag = 0 ");
            if (!StringUtils.isEmpty(paramVO.getName())) {
                WHERE("su.nick_name like concat('%',#{name},'%')");
            }

            if (!StringUtils.isEmpty(paramVO.getStatus())) {
                WHERE("su.status = #{status}");
            }

            if (!StringUtils.isEmpty(paramVO.getEndTime())) {
                WHERE("su.create_time <= #{endTime}");
            }
            if (!StringUtils.isEmpty(paramVO.getStartTime())) {
                WHERE("su.create_time  >= #{startTime}");
            }

            ORDER_BY("su.create_time desc ");
        }}.toString();
    }

    public String addSysUser(SysUserEntity sysUserEntity) {

        String sql = new SQL() {{
            INSERT_INTO("sys_user");
            VALUES("user_name", "#{userName}");
            VALUES("units", "#{units}");
            VALUES("nick_name", "#{nickName}");
            VALUES("password", "#{password}");
            VALUES("status", "#{status}");
            VALUES("login_date", "#{loginDate}");
            VALUES("update_by", "#{updateBy}");
            VALUES("update_time", "#{updateTime}");
            VALUES("create_time", "#{createTime}");
            VALUES("remark", "#{remark}");
            VALUES("role_id", "#{roleId}");
        }}.toString();
        return sql;
    }



}
