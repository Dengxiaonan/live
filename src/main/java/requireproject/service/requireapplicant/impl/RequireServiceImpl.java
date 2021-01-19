package requireproject.service.requireapplicant.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.stereotype.Service;
import requireproject.common.MapperUtils;
import requireproject.common.UUIDUtils;
import requireproject.common.redis.RedisUtil;
import requireproject.repository.requireapplicant.entity.CommentEntity;
import requireproject.repository.requireapplicant.entity.MilitaryZone;
import requireproject.repository.requireapplicant.entity.RequireEntity;
import requireproject.repository.requireapplicant.mapper.RequireMapper;
import requireproject.repository.sysuser.entity.SysUserEntity;
import requireproject.service.requireapplicant.RequireService;
import requireproject.service.requireapplicant.request.RequireRequest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class RequireServiceImpl implements RequireService {

    @Resource
    RequireMapper requireMapper;

    @Resource
    RedisUtil redisUtil;

    //添加信息
    @Override
    public void addRequire(RequireRequest requireRequest) {

        requireRequest.setUuid(UUIDUtils.getUUID());

        requireMapper.addRequire(requireRequest);

    }
    //修改信息
    @Override
    public void updRequire(RequireRequest requireRequest) {

        RequireEntity requireEntity = MapperUtils.mapperBean(requireRequest, RequireEntity.class);

        requireMapper.updRequire(requireEntity);
    }
    //根据编号删除数据
    @Override
    public void delRequire(String uuid) {
        requireMapper.delRequire(uuid);

    }

    //根据编号获取信息
    @Override
    public RequireRequest getRequireInfo(String uuid) {

        return requireMapper.getRequireInfo(uuid);
    }
//需求申请
    @Override
    public Object getUserList(RequireRequest requireRequest) {
        PageHelper.startPage(requireRequest.getPageNo(), requireRequest.getPageSize());
        PageInfo<RequireRequest> pageInfo = new PageInfo(requireMapper.getUserList(requireRequest));
        return pageInfo;
    }
//需求审批
   @Override
    public Object getApproveList(RequireRequest requireRequest) {
        PageHelper.startPage(requireRequest.getPageNo(), requireRequest.getPageSize());
        PageInfo<RequireRequest> pageInfo = new PageInfo(requireMapper.getApproveList(requireRequest));
        return pageInfo;
    }
//需求辅助分析
    @Override
    public Object getAssistList(RequireRequest requireRequest) {
        PageHelper.startPage(requireRequest.getPageNo(), requireRequest.getPageSize());
        PageInfo<RequireRequest> pageInfo = new PageInfo(requireMapper.getAssistList(requireRequest));
        return pageInfo;
    }
//需求文档发布
    @Override
    public Object getDocumentList(RequireRequest requireRequest) {
        PageHelper.startPage(requireRequest.getPageNo(), requireRequest.getPageSize());
        PageInfo<RequireRequest> pageInfo = new PageInfo(requireMapper.getDocumentList(requireRequest));
        return pageInfo;
    }
//需求提交
    @Override
    public void submitDemand(RequireRequest requireRequest) {
        requireMapper.submitDemand(requireRequest);
    }
//需求审批
    @Override
    public void demandExamine(RequireRequest requireRequest) {
        requireMapper.demandExamine( requireRequest);
    }
//需求审批
    @Override
    public void demandExamines(RequireRequest requireRequest) {
        requireMapper.demandExamines( requireRequest);
    }
//添加评论
    @Override
    public void addComment(CommentEntity commentEntity) {
        commentEntity.setId(UUIDUtils.getUUID());
        commentEntity.setEditTime(LocalDateTime.now());
        requireMapper.addComment(commentEntity);
    }
//查询评论
    @Override
    public Object queryComment(CommentEntity commentEntity) {
        return requireMapper.queryComment(commentEntity);
    }

    @Override
    public List<MilitaryZone> getZone(){
        requireMapper.getZone();
        return null;
    }

    @Override
    public List<SysUserEntity> getZoneUser(String userId){
        requireMapper.getZoneUser(userId);
        return null;
    }


}
