package requireproject.service.requireapplicant.request;

import lombok.Data;

@Data
public class ResearchRequest {
    private String uuid;
    private String number;
    private String moduleName;//组件名称
    private String moduleVersion;//组件版本
    private String keyword;
    private String issuer;
    private String subjectTerms;
    private String projectName;
    private String status;//状态0已发布1未发布
    private String priority;
    private String person;
    private String createTime;

    }
