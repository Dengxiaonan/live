package requireproject.controller.assistantanalysis;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import requireproject.common.remind.JsonResult;
import requireproject.service.assistantanalysis.AssistantAnalysisService;

import javax.annotation.Resource;

@Api(value="/AssistantAnalysis", tags={"需求-辅助分析"})
@RestController
@RequestMapping("/require/ass")
public class AssistantAnalysisController {
    @Resource
    private AssistantAnalysisService assistantAnalysisService;

    @ApiOperation(value="查询列表信息",produces ="application/json" )
    @PostMapping(value = "/getAssAll", produces = "application/json")
    public JsonResult getAssAll() {

        return JsonResult.success(assistantAnalysisService.getAssAll());
    }
    @ApiOperation(value="浏览",produces = "application/json")
    @PostMapping(value="/getAssistantInfo/{uuid}")
    public JsonResult getAssistantInfo(@PathVariable("uuid") String uuid){
        return JsonResult.success(assistantAnalysisService.getAssistantInfo(uuid));

    }

}
