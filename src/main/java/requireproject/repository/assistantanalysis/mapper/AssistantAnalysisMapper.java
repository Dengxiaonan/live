package requireproject.repository.assistantanalysis.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import requireproject.repository.assistantanalysis.entity.AssistantAnalysisEntity;

import java.util.List;

@Mapper
public interface AssistantAnalysisMapper {
    @Select("SELECT * FROM require_assistant_analysis")
    List<AssistantAnalysisEntity> getAssAll();
    //浏览，查看单条信息
    @Select("SELECT `uuid`,`number`,`title`,`status`,`person`,`priority`,`require_type`,`function_description`,`create_time`,`remark`," +
            " FROM require_assistant_analysis  WHERE uuid = #{uuid}")

    AssistantAnalysisEntity getAssistantInfo(@Param("uuid") String uuid);
}
