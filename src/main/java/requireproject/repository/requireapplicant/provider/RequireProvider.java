package requireproject.repository.requireapplicant.provider;


import io.micrometer.core.instrument.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import requireproject.repository.documentsearch.request.DocumentsearchRequest;
import requireproject.repository.requireapplicant.entity.CommentEntity;
import requireproject.service.requireapplicant.request.LogRequest;
import requireproject.service.requireapplicant.request.RequireRequest;
import requireproject.service.requireapplicant.request.ResearchRequest;

public class RequireProvider extends SQL {



    public String getUserList(RequireRequest requireRequest) {
        String s = new SQL() {{
            SELECT("ra.uuid,ra.number,ra.project_name,ra.status,ra.subject_term,ra.person,ra.priority,ra.require_type,ra.function_description,ra.create_time ,ra.remark ,ra.operation , ra.select_import_mode");
            FROM("require_applicant  ra");
            if (StringUtils.isEmpty(String.valueOf(requireRequest.getStatus()))){
                WHERE("ra.status IN (5,6,7)");}
            if (!StringUtils.isEmpty(requireRequest.getNumber())) {
                WHERE("ra.number LIKE concat'%'#{number}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getSubjectTerm())) {
                WHERE("ra.subject_term LIKE concat'%'#{subjectTerm}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getPerson())) {
                WHERE("ra.person LIKE concat'%'#{person}'%'");
            }
            if (!StringUtils.isEmpty(String.valueOf(requireRequest.getStatus()))) {
                WHERE("ra.status = #{status}" );
            }
        }}.toString();
        System.out.println(s);
        return s;
    }

    public String getApproveList(RequireRequest requireRequest) {
        String s = new SQL() {{
            SELECT("ra.uuid,ra.number,ra.subject_term,ra.title,ra.status,ra.person,ra.priority,ra.require_type,ra.function_description,ra.create_time ,ra.remark ,ra.operation");
            FROM("require_approve  ra");
            if (StringUtils.isEmpty(String.valueOf(requireRequest.getStatus()))){
            WHERE("ra.status IN (0)");}
            if (!StringUtils.isEmpty(requireRequest.getNumber())) {
                WHERE("ra.number LIKE concat'%'#{number}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getSubjectTerm())) {
                WHERE("ra.subject_term LIKE concat'%'#{subjectTerm}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getPerson())) {
                WHERE("ra.person LIKE concat'%'#{person}'%'");
            }
            if (!StringUtils.isEmpty(String.valueOf(requireRequest.getStatus()))) {
                WHERE("ra.status = #{status}" );
            }

        }}.toString();
        System.out.println(s);
        return s;
    }


    public String getAssistList(RequireRequest requireRequest) {
        String s = new SQL() {{
            SELECT("ra.uuid,ra.number,ra.title,ra.status,ra.keyword,ra.person,ra.priority,ra.require_type,ra.function_description,ra.create_time ,ra.remark ,ra.operation");
            FROM("require_assistant_analysis  ra");
            if (StringUtils.isEmpty(String.valueOf(requireRequest.getStatus()))){
                WHERE("ra.status IN (5,6,7)");}
            if (!StringUtils.isEmpty(requireRequest.getProjectName())) {
                WHERE("ra.project_name LIKE concat'%'#{ProjectName}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getSubjectTerm())) {
                WHERE("ra.subject_term LIKE concat'%'#{subjectTerm}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getPerson())) {
                WHERE("ra.person LIKE concat'%'#{person}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getRequireType())) {
                WHERE("ra.require_type LIKE concat'%'#{require_type}'%'");
            }

            if (!StringUtils.isEmpty(String.valueOf(requireRequest.getStatus()))) {
                WHERE("ra.status = #{status}");
            }

        }}.toString();
        System.out.println(s);
        return s;
    }

    public String getDocumentList(RequireRequest requireRequest) {
        String s = new SQL() {{
            SELECT("ra.uuid,ra.number,ra.project_name,ra.status,ra.subject_term,ra.person,ra.priority,ra.require_type,ra.function_description,ra.create_time ,ra.remark ,ra.operation , ra.select_import_mode");
            FROM("require_applicant  ra");
            if (StringUtils.isEmpty(String.valueOf(requireRequest.getStatus()))){
                WHERE("ra.status IN (7)");}
            if (!StringUtils.isEmpty(requireRequest.getNumber())) {
                WHERE("ra.number LIKE concat'%'#{number}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getSubjectTerm())) {
                WHERE("ra.subject_term LIKE concat'%'#{subjectTerm}'%'");
            }
            if (!StringUtils.isEmpty(requireRequest.getPerson())) {
                WHERE("ra.person LIKE concat'%'#{person}'%'");
            }
            if (!StringUtils.isEmpty(String.valueOf(requireRequest.getStatus()))) {
                WHERE("ra.status = #{status}");
            }

        }}.toString();
        System.out.println(s);
        return s;
    }

    public String getDocumentSearchList(DocumentsearchRequest documentsearchRequest){
        String s = new SQL(){{
            SELECT("ds.uuid,ds.number,ds.project_name,ds.file_name,ds.start_time,ds.end_time,ds.document_name,ds.type,ds.keyword,ds.issuer,ds.detailed_information,ds.remark");
            FROM("document_search  ds");
            if (!StringUtils.isEmpty(documentsearchRequest.getNumber())) {
                WHERE("ds.number LIKE concat'%'#{number}'%'");
            }
            if (!StringUtils.isEmpty(documentsearchRequest.getProjectName())) {
                WHERE("ds.project_name LIKE concat'%'#{projectName}'%'");
            }
            if (!StringUtils.isEmpty(documentsearchRequest.getFilename())) {
                WHERE("ds.file_name LIKE concat'%'#{fileName}'%'");
            }
            if (!StringUtils.isEmpty(documentsearchRequest.getIssuer())) {
                WHERE("ds.issuer LIKE concat'%'#{issuer}'%'");
            }

        }}.toString();
        System.out.println(s);
        return s;
    }

   public String getResearchList(ResearchRequest researchRequest){
      String s = new SQL(){{
        SELECT("ds.uuid,ds.number,ds.module_name,ds.module_version,ds.keyword,ds.issuer,ds.subject_terms,ds.project_name,ds.status,ds.priority,ds.person,ds.create_time,ds.operation");
        FROM("require_research_module  ds");
        if (!StringUtils.isEmpty(researchRequest.getNumber())) {
            WHERE("ds.number LIKE concat'%'#{number}'%'");
        }
        if (!StringUtils.isEmpty(researchRequest.getKeyword())) {
            WHERE("ds.keyword LIKE concat'%'#{keyword}'%'");
        }
        if (!StringUtils.isEmpty(researchRequest.getIssuer())) {
            WHERE("ds.issuer LIKE concat'%'#{issuer}'%'");
        }

    }}.toString();
    System.out.println(s);
    return s;
}

public String getLogList(LogRequest logRequest) {
    String s = new SQL() {{
        SELECT("lm.uuid,lm.log_number,lm.request_method,lm.host,lm.operation_place,lm.operation,lm.system_module,lm.operator,lm.operation_type,lm.operation_status,lm.operation_date");
        FROM("log_management lm");
        if (!StringUtils.isEmpty(logRequest.getSystemModule())) {
            WHERE("lm.system_module LIKE concat'%'#{systemModule}'%'");
        }
        if (!StringUtils.isEmpty(logRequest.getOperator())) {
            WHERE("lm.operator LIKE concat'%'#{operator}'%'");
        }
        if (!StringUtils.isEmpty(logRequest.getOperationType())) {
            WHERE("lm.operation_type LIKE concat'%'#{operationType}");
        }
        if (!StringUtils.isEmpty(logRequest.getOperationStatus())) {
            WHERE("lm.operation_status LIKE concat'%'#{operationStatus}");
        }
        if (!StringUtils.isEmpty(logRequest.getOperationDate())) {
            WHERE("lm.operation_date LIKE concat'%'#{operationDate}");
        }
    }}.toString();
    System.out.println(s);
    return s;
}









    public String submitDemand(RequireRequest requireRequest){
        String s = new SQL(){{
            UPDATE(" require_applicant SET `status` = 1");
            WHERE(" `uuid` = #{uuid} ");
        }}.toString();
        System.out.println(s);
        return s;
    }


    public String demandExamine(RequireRequest requireRequest){
        String s = new  SQL(){{
            UPDATE(" require_approve SET `status` = #{status} ");
            WHERE(" `uuid` = #{uuid} ");
        }}.toString();
        return s;
    }

    public String demandExamines(RequireRequest requireRequest){
        String s = new  SQL(){{
            UPDATE(" require_approve SET `status` = #{status} , `cause` = #{cause} ");
            WHERE(" `uuid` = #{uuid} ");
        }}.toString();
        return s;
    }

    public String addComment(CommentEntity commentEntity){
        String sql = new SQL(){{
       INSERT_INTO("`demand_comments`");
       VALUES("`id`","#{id}");
       VALUES("`commentator`","#{commentator}");
       VALUES("`edit_time`","#{editTime}");
       VALUES("`content`","#{content}");
       VALUES("`r_id`","#{rid}");
        }}.toString();
        System.out.println(sql);
        return sql;
    }


    public String queryComment(CommentEntity commentEntity){
        String sql = new SQL(){{
            SELECT("`id`, `commentator`, `edit_time`, `content`, `r_id`");
            FROM("demand_comments");
            WHERE("r_id = #{rid}");
        }}.toString();
        System.out.println(sql);
        return sql;
    }

}
