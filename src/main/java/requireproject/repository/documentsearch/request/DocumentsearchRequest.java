package requireproject.repository.documentsearch.request;

import lombok.Data;

@Data
public class DocumentsearchRequest {
    private String uuid;
    private String number;
    private String projectName;
    private String filename;
    private String startTime;
    private String endTime;
    private String documentName;
    private String type;
    private String keyword;
    private String issuer;
    private String detailedInformation;
    private String remark;
}
