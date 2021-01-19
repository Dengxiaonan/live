package requireproject.service.requireapprove.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import requireproject.common.MapperUtils;
import requireproject.common.UUIDUtils;
import requireproject.repository.requireapprove.entity.ApproveEntity;
import requireproject.repository.requireapprove.mapper.ApproveMapper;
import requireproject.service.requireapprove.ApproveService;
import requireproject.service.requireapprove.request.ApproveRequest;

import javax.annotation.Resource;
import java.util.List;


@Service
public class ApproveServiceImpl implements ApproveService {

    @Resource
    ApproveMapper approveMapper;

    @Override
    public void addApprove(ApproveRequest approveRequest) {
        ApproveEntity approveEntity = MapperUtils.mapperBean(approveRequest, ApproveEntity.class);

        approveEntity.setUuid(UUIDUtils.getUUID());

        approveMapper.addApprove(approveEntity);

    }


    @Override
    public void updApprove(ApproveRequest approveRequest) {
        ApproveEntity approveEntity = MapperUtils.mapperBean(approveRequest, ApproveEntity.class);

        approveMapper.updApprove(approveEntity);
    }


    @Override
    public void delApprove(String uuid) {
        approveMapper.delApprove(uuid);
    }

    @Override
    public Object getApproveList(String uuid, int p, int c) {

        Page<ApproveEntity> objects = PageHelper.startPage(p, c);
        List<ApproveEntity> approveList = approveMapper.getApproveList(uuid);
        PageInfo<ApproveEntity> objectPageInfo = new PageInfo<>(approveList);
        return objectPageInfo;
    }

    @Override
    public ApproveEntity getApproveInfo(String uuid) {

        return approveMapper.getByIdApproveInfo(uuid);
    }

}
