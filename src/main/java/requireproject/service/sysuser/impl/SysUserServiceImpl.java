package requireproject.service.sysuser.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import requireproject.common.AuthCodeUtil;
import requireproject.common.MapperUtils;
import requireproject.common.UUIDUtils;
import requireproject.common.UseDetailsDTO;
import requireproject.common.exception.AuthCodeConstant;
import requireproject.common.redis.RedisUtil;
import requireproject.common.remind.JsonResult;
import requireproject.repository.sysuser.entity.SysUserEntity;
import requireproject.repository.sysuser.mapper.SysUserMapper;
import requireproject.service.sysuser.SysUserService;
import requireproject.service.sysuser.request.ParamVO;
import requireproject.service.sysuser.request.SysUserRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


/**
 * @Author ldy
 * @Descriptions 管理员用户接口实现类
 * @Date 2020/11/25 11:13
 * @Version: V1.0
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Resource
    RedisUtil redisUtil;

    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    AuthCodeUtil authCodeUtil;

    @Autowired
    private HttpServletRequest request;


    @Override
    public JsonResult userLogin(String userName, String password ) {

        SysUserEntity sysUser = sysUserMapper.loginAuthentication(userName, password);

        if (null == sysUser) {
            return JsonResult.error("100", "账户信息不存在！！！");
        }
        if (sysUser.getStatus() == 1){
            return JsonResult.error("102", "账户已停用！！！");
        }
        if (!passwordEncoder.matches(password, sysUser.getPassword())) {
            return JsonResult.error("101", "用户名密码不对");
        }
//        request.setAttribute("useDetailsDTO" , sysUser);
//        Object useDetailsDTO1 = request.getAttribute("useDetailsDTO");
//        System.out.println(useDetailsDTO1);
//        SysUserEntity sysUserEntity = sysUserMapper.queryAll(sysUser);
//        System.out.println(sysUserEntity);
//        if (null != sysUser.getPassword()){
//            String uuid = UUIDUtils.getUUID();
//
//            redisUtil.set(RedisKeyConstants.USER + userName, uuid, 108000);
//
//        }
//        redisUtil.set(RedisKeyConstants.USER + sysUser.getUuid(), 108000);

        UseDetailsDTO useDetailsDTO = new UseDetailsDTO();
        useDetailsDTO.setUserId(sysUser.getUuid());
        useDetailsDTO.setMobile(sysUser.getUserName());
        useDetailsDTO.setStatus(0);


        redisUtil.del(AuthCodeConstant.AUTH_CODE_TOKEN+request.getHeader(AuthCodeConstant.AUTH_HEADER_KEY));
        redisUtil.set(AuthCodeConstant.AUTH_CODE_LOGIN_SAVE+sysUser.getUuid(),request.getHeader(AuthCodeConstant.AUTH_HEADER_KEY));

        redisUtil.set(AuthCodeConstant.AUTH_CODE_SAVE+request.getHeader(AuthCodeConstant.AUTH_HEADER_KEY), JSON.toJSONString(useDetailsDTO));

        return JsonResult.success(sysUser);

    }




    /**
     * @Author ldy
     * @Description 添加用户信息
     * @Date 2020/11/25 14:38
     * @Param sysUserRequest
     **/
    @Override
    public JsonResult addSysUser(SysUserRequest sysUserRequest) {

        SysUserEntity sysUserEntity = MapperUtils.mapperBean(sysUserRequest, SysUserEntity.class);
        if (StringUtils.isEmpty(sysUserEntity.getUserName()) || StringUtils.isEmpty(sysUserEntity.getPassword())) {
            return JsonResult.error("102", "账号密码不可为空");
        }
        SysUserEntity user = sysUserMapper.loginAuthentication(sysUserEntity.getUserName(), "");
        if (user != null) {
            return JsonResult.error("101", "账户已存在");
        }

        sysUserEntity.setPassword(passwordEncoder.encode(sysUserEntity.getPassword()));
        sysUserEntity.setUuid(UUIDUtils.getUUID());
        sysUserEntity.setLoginDate(LocalDateTime.now());
        sysUserEntity.setCreateTime(LocalDateTime.now());
        sysUserEntity.setUpdateTime(LocalDateTime.now());
        sysUserEntity.setRegistrationDate(LocalDateTime.now());

        sysUserMapper.addSysUser(sysUserEntity);
        return JsonResult.success("ok");
    }


    @Override
    public void updSysUser(SysUserRequest sysUserRequest) {

        SysUserEntity sysUserEntity = MapperUtils.mapperBean(sysUserRequest, SysUserEntity.class);

        sysUserEntity.setUpdateTime(LocalDateTime.now());

        sysUserMapper.updSysUser(sysUserEntity);
    }


    @Override
    public void delSysUser(String userName) {

        sysUserMapper.delSysUser(userName);
    }


    @Override
    public Object getUserList(ParamVO paramVO) {
        if (StringUtils.isNotBlank(paramVO.getEndTime())) {
            paramVO.setEndTime(paramVO.getEndTime() + " 23:59:59");
        }
        PageHelper.startPage(paramVO.getPageNo(), paramVO.getPageSize());
        PageInfo<SysUserEntity> pageInfo = new PageInfo(sysUserMapper.getUserList(paramVO));

        return pageInfo;
    }


    @Override
    public SysUserEntity getUserInfo(String userName) {

        return sysUserMapper.getUserInfo(userName);
    }


}
