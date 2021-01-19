package requireproject.repository.requireapprove.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApproveEntity {
    private String uuid;
    private String number;//编号
    private String subjectTerm;//主题词
    private String title;//标题
    private int status;//状态0为申请中1同意2拒绝3退回4已申请
    private String refusalCause;//拒绝原因
    private String fallbackReason;//回退原因
    private String person;//负责人
    private String priority;//优先级
    private String requireType;//需求类型
    private String functionDescription;//功能描述
    private LocalDateTime createTime;//创建时间
    private String remark;//备注

}
