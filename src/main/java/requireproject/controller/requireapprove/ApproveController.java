package requireproject.controller.requireapprove;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import requireproject.common.remind.JsonResult;
import requireproject.service.requireapprove.ApproveService;
import requireproject.service.requireapprove.request.ApproveRequest;

import javax.annotation.Resource;

@Api(value = "requireapprove",tags={"需求-需求审批"})
@RestController
@RequestMapping("/require/req")
public class ApproveController {

    @Resource
    ApproveService approveService;

    @ApiOperation(value = "添加", produces = "application/json")
    @PostMapping("/addApprove")
    public JsonResult addProjectMainInfoOrType(@RequestBody ApproveRequest approveRequest) {

        approveService.addApprove(approveRequest);

        return JsonResult.success("OK");
    }
    @ApiOperation(value = "删除", produces = "application/json")
    @PostMapping("/delApprove/{uuid}")
    public JsonResult delRequire(@PathVariable("uuid") String uuid) {

        approveService.delApprove(uuid);

        return JsonResult.success("OK");
    }

    @ApiOperation(value = "修改", produces = "application/json")
    @PostMapping("/updApprove")
    public JsonResult updSysUser(@RequestBody ApproveRequest approveRequest) {

        approveService.updApprove(approveRequest);

        return JsonResult.success("OK");
    }

    @ApiOperation(value = "分页查询列表", produces = "application/json")
    @PostMapping(value = "/getApproveList/{uuid}", produces = "application/json")
    public JsonResult getApproveList(@PathVariable("uuid") String uuid,@ModelAttribute(name = "page") int p, @ModelAttribute(name = "count") int c) {

        return JsonResult.success(approveService.getApproveList(uuid,p,c));
    }

    @ApiOperation(value = "详情", produces = "application/json")
    @PostMapping("/getApproveInfo/{uuid}")
    public JsonResult getApproveInfo(@PathVariable("uuid") String uuid) {

        return JsonResult.success(approveService.getApproveInfo(uuid));
    }

}