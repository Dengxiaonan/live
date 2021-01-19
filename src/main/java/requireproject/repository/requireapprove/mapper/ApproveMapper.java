package requireproject.repository.requireapprove.mapper;

import org.apache.ibatis.annotations.*;
import requireproject.repository.requireapprove.entity.ApproveEntity;

import java.util.List;


@Mapper
public interface ApproveMapper {
        @Insert("INSERT INTO require_approve(`number`,`title`,`status`,`person`,`priority`,`require_type`," +
                "`function_description`,`create_time`,`remark`) VALUES (#{number},#{title},#{status},#{person},#{priority}," +
                "#{requireType},#{functionDescription},#{createTime},#{remark})")
        void addApprove(ApproveEntity approveEntity);

        @Update("UPDATE require_approve SET `status` = #{status} WHERE uuid = #{uuid}")

        void updApprove(ApproveEntity approveEntity);

        @Delete("delete from require_approve where uuid = #{uuid}")
        void delApprove(@Param("uuid") String uuid);

        //详情，查看单条信息
        @Select("SELECT `uuid`,`number`,`title`,`status`,`person`,`priority`,`require_type`," +
                "`function_description`,`create_time`,`remark` FROM require_approve  WHERE uuid = #{uuid}")

        ApproveEntity getByIdApproveInfo(@Param("uuid") String uuid);

        @Select("select * from require_approve a where a.uuid = #{uuid}")
        List<ApproveEntity> getApproveList(@Param("uuid") String uuid);
    }