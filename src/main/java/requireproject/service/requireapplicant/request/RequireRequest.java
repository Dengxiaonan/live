package requireproject.service.requireapplicant.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequireRequest {

        private String uuid;//用户id
        private String number;//编号
        private String projectName;//项目名称
        private Integer status;//状态0未提交1已提交2待审批3已审批4已退回5拒绝
        private Integer statuss;//1收集2评审3发布
        private String subjectTerm;//主题词
        private String person;//负责人
        private String priority;//优先级
        private String requireType;//需求类型
        private String functionDescription;//功能描述
        private LocalDateTime createTime;//创建时间
        private String remark;//备注
        private String cause;//退回原因
        private String examine;//审批人
        private String selectImportMode;
        @ApiModelProperty(value = "页数")
        private Integer pageNo = 1;
        @ApiModelProperty(value = "条数")
        private Integer pageSize = 10;

}
