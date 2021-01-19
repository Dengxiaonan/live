package requireproject.controller.requireapplicant;

import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import requireproject.common.remind.JsonResult;
import requireproject.repository.requireapplicant.entity.CommentEntity;
import requireproject.service.requireapplicant.RequireService;
import requireproject.service.requireapplicant.request.RequireRequest;

import javax.annotation.Resource;

@Api(value = "requireApplicant",tags={"需求-需求申请"})
@RestController
@RequestMapping("/require/req")
public class RequireController {
    @Resource
    RequireService requireService;

    @ApiOperation(value = "添加", produces = "application/json")
    @PostMapping("/addRequire")
    public JsonResult addProjectMainInfoOrType(@RequestBody RequireRequest requireRequest) {

        requireService.addRequire(requireRequest);

        return JsonResult.success("OK");
    }
    @ApiOperation(value = "删除", produces = "application/json")
    @PostMapping("/delRequire/{uuid}")
    public JsonResult delRequire(@PathVariable("uuid") String uuid) {

        requireService.delRequire(uuid);

        return JsonResult.success("OK");
    }

    @ApiOperation(value = "修改", produces = "application/json")
    @PostMapping("/updRequire")
    public JsonResult updSysUser(@RequestBody RequireRequest requireRequest) {

        requireService.updRequire(requireRequest);

        return JsonResult.success("OK");
    }
//
//    @ApiOperation(value = "分页查询列表", produces = "application/json")
//    @PostMapping(value = "/getUserList/{uuid}", produces = "application/json")
//    public JsonResult getUserList(@PathVariable("uuid") String uuid,@ModelAttribute(name = "page") int p, @ModelAttribute(name = "count") int c) {
//
//        return JsonResult.success(requireService.getRequireList(uuid,p,c));
//    }


    @PostMapping("/getUserList")
    @ApiOperation(value = "需求申请分页查询列表",notes = "需求申请列表分页查询(条件查询)",httpMethod = "POST",response = PageInfo.class)
    public JsonResult getUserList(@RequestBody RequireRequest requireRequest) {
        System.out.println("aa");
        JsonResult a=JsonResult.success(requireService.getUserList(requireRequest));
        return JsonResult.success(requireService.getUserList(requireRequest));
    }









    @PostMapping("/getApproveList")
    @ApiOperation(value = "需求审批分页查询列表",notes = "需求审批列表分页查询(条件查询)",httpMethod = "POST",response = PageInfo.class)
    public JsonResult getApproveList(@RequestBody RequireRequest requireRequest) {
        JsonResult a=JsonResult.success(requireService.getApproveList(requireRequest));
        return JsonResult.success(requireService.getApproveList(requireRequest));
    }



    @PostMapping("/getAssistList")
    @ApiOperation(value = "需求辅助分析分页查询列表",notes = "需求辅助分析列表分页查询(条件查询)",httpMethod = "POST",response = PageInfo.class)
    public JsonResult getAssistList(@RequestBody RequireRequest requireRequest) {
        JsonResult a=JsonResult.success(requireService.getAssistList(requireRequest));
        return JsonResult.success(requireService.getAssistList(requireRequest));
    }


    @PostMapping("/getDocumentList")
    @ApiOperation(value = "需求文档发布分页查询列表",notes = "需求文档发布列表分页查询(条件查询)",httpMethod = "POST",response = PageInfo.class)
    public JsonResult getDocumentList(@RequestBody RequireRequest requireRequest) {
        JsonResult a=JsonResult.success(requireService.getDocumentList(requireRequest));
        return JsonResult.success(requireService.getDocumentList(requireRequest));
    }

    @ApiOperation(value = "详情", produces = "application/json")
    @PostMapping("/getRequireInfo/{uuid}")
    public JsonResult getRequireInfo(@PathVariable("uuid") String uuid) {

        return JsonResult.success(requireService.getRequireInfo(uuid));
    }

    @ApiOperation(value = "需求申请模块(页面按钮)", produces = "application/json")
    @PostMapping("/submitDemand")
    public JsonResult submitDemand(@RequestBody RequireRequest requireRequest) {

        requireService.submitDemand(requireRequest);

        return JsonResult.success("OK");
    }
    @ApiOperation(value = "需求审批模块审批(审批按钮)", produces = "application/json")
    @PostMapping("/demandExamine")
    public JsonResult demandExamine(@RequestBody RequireRequest requireRequest) {
        if (requireRequest.getStatus() == 4){
            requireService.demandExamines(requireRequest);
            return JsonResult.success("OK");
        }else {
            requireService.demandExamine(requireRequest);

            return JsonResult.success("OK");
        }
    }
    @ApiOperation(value = "需求辅助分析-评论添加" ,  produces = "application/json")
    @PostMapping("/addComment")
    public JsonResult addComment(@RequestBody CommentEntity commentEntity){
        //这个地方需要添加一个用户的名字因为登录信息没有放所以放好了获取更改
        if (commentEntity!=null){
            requireService.addComment(commentEntity);
        }
        return JsonResult.success("OK");
    }
    @ApiOperation(value = "需求辅助分析-评论查询" ,  produces = "application/json")
    @PostMapping("/getComment")
    public JsonResult queryComment(@RequestBody CommentEntity commentEntity){

        if (commentEntity.getRid()!= null){
            return JsonResult.success(requireService.queryComment(commentEntity));
        }
        return JsonResult.success("no");
    }

//    @ApiOperation(value = "获取所有战区信息" ,produces = "application/json")
//    @PostMapping("/getZone")
//    public JsonResult getZone(){
//        try{
//            return JsonResult.success(requireService.getZone());
//        }catch (Exception e){
//            return JsonResult.error("30001","获取所有战区信息错误");
//        }
//    }

    @ApiOperation(value = "获取所有战区信息名称" ,produces = "application/json")
    @PostMapping("/getZoneUser")
    public JsonResult getZoneUser(@PathVariable("userId") String userId){
        try{
            return JsonResult.success(requireService.getZoneUser(userId));
        }catch (Exception e){
            return JsonResult.error("30001","获取所有战区信息错误");
        }
    }
}