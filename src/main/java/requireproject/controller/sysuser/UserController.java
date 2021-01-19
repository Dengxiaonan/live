package requireproject.controller.sysuser;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import requireproject.common.AuthCodeUtil;
import requireproject.common.UseDetailsDTO;
import requireproject.common.exception.AuthCodeConstant;
import requireproject.common.redis.RedisUtil;
import requireproject.common.remind.JsonResult;
import requireproject.service.sysuser.SysUserService;
import requireproject.service.sysuser.request.ParamVO;
import requireproject.service.sysuser.request.SysUserRequest;

import javax.annotation.Resource;

@Api(value = "sysUser",tags = {"需求-用户相关信息"})
@RestController
@RequestMapping("/sysUser")
@CrossOrigin
public class UserController {

    @Resource
    SysUserService sysUserService;

    @Resource
    RedisUtil redisUtil;


    @GetMapping("/get/code")
    @ApiOperation(value = "获取授权码",notes = "获取授权码",httpMethod = "GET",response = String.class)
    public JsonResult getVerification (){
        try {
            // 2.验证通过生成token
            String authCode = AuthCodeUtil.getToken();
            if (authCode == null) {
                return JsonResult.error("301","token生成失败");
            }
            redisUtil.set(AuthCodeConstant.AUTH_CODE_TOKEN+authCode, JSON.toJSONString(new UseDetailsDTO()));

            return JsonResult.success(authCode);
        }catch (Exception e){
            return JsonResult.error("-1","授权码生成错误！！！");
        }
    }

    @ApiOperation(value = "登录", produces = "application/json")
    @PostMapping("/login")
    public JsonResult userLogin(@RequestBody SysUserRequest sysUserRequest) {
        JsonResult jsonResult = sysUserService.userLogin(sysUserRequest.getUserName(), sysUserRequest.getPassword());
        return jsonResult;
    }

    @ApiOperation(value = "需求-添加", produces = "application/json")
    @PostMapping("/addSysUser")
    public JsonResult addProjectMainInfoOrType(@RequestBody SysUserRequest sysUserRequest) {



        return sysUserService.addSysUser(sysUserRequest);
    }

    @ApiOperation(value = "需求-删除", produces = "application/json")
    @PostMapping("/delSysUser/{userName}")
    public JsonResult delSysUser(@PathVariable("userName") String userName) {

        sysUserService.delSysUser(userName);

        return JsonResult.success("OK");
    }

    @ApiOperation(value = "需求-修改", produces = "application/json")
    @PostMapping("/updSysUser")
    public JsonResult updSysUser(@RequestBody SysUserRequest sysUserRequest) {

        sysUserService.updSysUser(sysUserRequest);

        return JsonResult.success("OK");
    }

    @PostMapping("/getUserList")
    @ApiOperation(value = "用户列表分页查询",notes = "用户列表分页查询",httpMethod = "POST",response = PageInfo.class)
    public JsonResult getUserList(@RequestBody ParamVO paramVO) {

        return JsonResult.success(sysUserService.getUserList(paramVO));
    }

    @ApiOperation(value = "需求-详情", produces = "application/json")
    @PostMapping("/getUserInfo/{userName}")
    public JsonResult getUserInfo(@PathVariable("userName") String userName) {

        return JsonResult.success(sysUserService.getUserInfo(userName));
    }

}
