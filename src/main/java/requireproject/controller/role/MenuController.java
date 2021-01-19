package requireproject.controller.role;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import requireproject.common.UseDetailsDTO;
import requireproject.common.UserUtil;
import requireproject.common.redis.RedisUtil;
import requireproject.common.remind.JsonResult;
import requireproject.service.role.MenuService;
import requireproject.service.role.dao.Role;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author danyang.liao
 */
@RestController
@CrossOrigin
@Api(value = "/menu",description = "权限菜单相关接口")
@RequestMapping("/require/menu/v1")
public class MenuController {
    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private HttpServletRequest request;
    @Resource
    RedisUtil redisUtil;

    /**
     *获取所有菜单
     * @return
     */
    @GetMapping("/menu/list")
    @ApiOperation(value = "获取所有菜单",notes = "获取所有菜单",httpMethod = "GET",response = Role.class)
    public JsonResult getMenuListAll() {
    UseDetailsDTO useDetailsDTO = UserUtil.getUserDTO(request);

//        Object userID = redisUtil.get("userID");

        return JsonResult.success(menuService.getMenuList("1"));
    }


}
