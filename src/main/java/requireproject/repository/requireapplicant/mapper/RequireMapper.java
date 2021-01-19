package requireproject.repository.requireapplicant.mapper;


import org.apache.ibatis.annotations.*;
import requireproject.repository.requireapplicant.entity.CommentEntity;
import requireproject.repository.requireapplicant.entity.MilitaryZone;
import requireproject.repository.requireapplicant.entity.RequireEntity;
import requireproject.repository.requireapplicant.provider.RequireProvider;
import requireproject.repository.sysuser.entity.SysUserEntity;
import requireproject.service.requireapplicant.request.RequireRequest;

import java.util.List;

@Mapper
public interface RequireMapper {
    @Insert("INSERT INTO `required`.`require_applicant` ( `uuid`, `number`, `project_name`, `status`, `subject_term`, `person`, `priority`, `require_type`, `function_description`, `create_time`, `remark`  , `statuss`)\n" +
            "VALUES\n" +
            "\t(#{uuid},#{number},#{projectName},0,#{subjectTerm},#{person},#{priority},#{requireType},#{functionDescription},#{createTime},#{remark}, #{statuss});")
    void addRequire(RequireRequest requireRequest);

    @Update("UPDATE require_applicant SET  `number`= #{number},`project_name` = #{projectName}," +
            "`person` = #{person},`subject_term`= #{subjectTerm},`priority` = #{priority},`require_type` = #{requireType}," +
            "`function_description` = #{functionDescription}," +
            "`remark`= #{remark} WHERE uuid = #{uuid}")

    void updRequire(RequireEntity requireEntity);

    @Delete("delete from require_applicant where uuid = #{uuid}")
    void delRequire(@Param("uuid") String uuid);

    @Select("select * from require_applicant r where r.uuid = #{uuid}")
    RequireRequest getRequireInfo(@Param("uuid") String uuid);

    @SelectProvider(method = "getUserList", type = RequireProvider.class)
    List<RequireRequest> getUserList(RequireRequest requireRequest);

    @SelectProvider(method = "getApproveList", type = RequireProvider.class)
    List<RequireRequest> getApproveList(RequireRequest requireRequest);

    @SelectProvider(method = "getAssistList", type = RequireProvider.class)
    List<RequireRequest> getAssistList(RequireRequest requireRequest);

    @SelectProvider(method = "getDocumentList", type = RequireProvider.class)
    List<RequireEntity> getDocumentList(RequireRequest requireRequest);

    @UpdateProvider(method = "submitDemand" , type = RequireProvider.class )
    void submitDemand(RequireRequest requireRequest);

    @UpdateProvider(method = "demandExamine" , type = RequireProvider.class )
    void demandExamine(RequireRequest requireRequest);

    @UpdateProvider(method = "demandExamines" , type = RequireProvider.class )
    void demandExamines(RequireRequest requireRequest);

    @InsertProvider(method = "addComment" , type = RequireProvider.class)
    void addComment(CommentEntity commentEntity);

    @SelectProvider(method = "queryComment" , type = RequireProvider.class)
    List<CommentEntity> queryComment(CommentEntity commentEntity);

    @Select("select id,name from military_zone")
    List<MilitaryZone> getZone();


    @Select("select uuid,nickName from sys_user WHERE m_id = #{userId}")
    List<SysUserEntity> getZoneUser(String userId);




}


