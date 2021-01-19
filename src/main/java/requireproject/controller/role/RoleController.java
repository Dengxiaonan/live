package requireproject.controller.role;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import requireproject.common.remind.JsonResult;
import requireproject.service.role.RoleService;
import requireproject.service.role.dao.Role;


/**
 * @author danyang.liao
 */
@RestController
@Api(value = "/group",description = "角色相关接口")
@RequestMapping("/require/group/v1")
@CrossOrigin
public class RoleController {
    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;


    @PostMapping("/getRoleList")
    @ApiOperation(value = "获取角色列表", notes = "获取角色列表", httpMethod = "POST", response = PageInfo.class)
    public JsonResult getRoleList() {
        try {
            return JsonResult.success(roleService.getRoleList());
        } catch (Exception e) {
            logger.info("文件分页查询", e);
            return null;
        }
    }

    @PostMapping("/addRole")
    @ApiOperation(value = "添加角色", notes = "添加角色", httpMethod = "POST", response = Role.class)
    public JsonResult addGroup(@RequestBody Role role) {
        try {
            return JsonResult.success(roleService.addRole(role));
        } catch (Exception e) {
            logger.info("添加角色", e);
            return JsonResult.error("301", "添加角色错误");
        }
    }


    @PostMapping("/updateRole")
    @ApiOperation(value = "修改角色信息", notes = "修改角色信息", httpMethod = "POST", response = PageInfo.class)
    public JsonResult updateRole(@RequestBody Role role) {
        try {
            return JsonResult.success(roleService.updateRole(role));
        } catch (Exception e) {
            logger.info("修改角色信息", e);
            return JsonResult.error("301", "修改角色信息错误");
        }
    }


    @GetMapping("/deleteRole")
    @ApiOperation(value = "删除角色信息", notes = "删除角色信息", httpMethod = "POST", response = PageInfo.class)
    public JsonResult deleteRole(@ApiParam("角色id") @RequestParam("id") String id) {
        try {
            return JsonResult.success(roleService.deleteRole(id));
        } catch (Exception e) {
            logger.info("删除角色信息", e);
            return JsonResult.error("301", "删除角色信息错误");
        }
    }


    }





