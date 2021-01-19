package requireproject.service.sysuser.request;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询实体
 */
@Data
public class ParamVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "页数")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "条数")
    private Integer pageSize = 10;


}
