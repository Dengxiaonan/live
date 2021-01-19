package requireproject.repository.assistantanalysis.entity;

import lombok.Data;

@Data
public class AssistantAnalysisEntity {
    private String uuid;
    private String number;//编号
    private String title;//标题
    private String status;//状态
    private String keyword;//关键词
    private String person;//负责人
    private String priority;//优先级
    private String requireType;//需求类型
    private String functionalDescription;//功能描述
    private String createTime;//创建时间
    private String remark;//备注


}
