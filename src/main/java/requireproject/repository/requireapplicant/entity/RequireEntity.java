package requireproject.repository.requireapplicant.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequireEntity {
    private String uuid;
    private String number;
    private String projectName;
    private int status;
    private String subjectTerm;
    private String person;
    private String priority;
    private String requireType;
    private String functionDescription;
    private LocalDateTime createTime;
    private String remark;




}
