package requireproject.service.requireapplicant.request;

import lombok.Data;

@Data
public class LogRequest {
    private String uuid;
    private String logNumber;//日志编号
    private String systemModule;//系统模块
    private String operationType;//操作类型
    private String requestMethod;//请求方式
    private String operator;//操作人员
    private String host;//主机
    private String operationPlace;//操作地点
    private String operationStatus;//操作状态
    private String operationDate;//操作日期


}
