package requireproject.service.sysuser;


import requireproject.common.remind.JsonResult;
import requireproject.repository.sysuser.entity.SysUserEntity;
import requireproject.service.sysuser.request.ParamVO;
import requireproject.service.sysuser.request.SysUserRequest;

/**
 *
 * 用户信息接口类
 */


public interface SysUserService {

//获取用户登录信息
JsonResult userLogin(String userName, String password);
// 添加用户信息
JsonResult addSysUser(SysUserRequest sysUserRequest);
// 修改用户信息
void updSysUser(SysUserRequest sysUserRequest);
// 删除用户信息
void delSysUser(String userName);
//获取用户信息列表
Object getUserList(ParamVO paramVO);
//根据用户名获取 用户信息
SysUserEntity getUserInfo(String userName);

}
