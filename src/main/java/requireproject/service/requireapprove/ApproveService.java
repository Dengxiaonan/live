package requireproject.service.requireapprove;


import requireproject.repository.requireapprove.entity.ApproveEntity;
import requireproject.service.requireapprove.request.ApproveRequest;

public interface ApproveService {
    void addApprove(ApproveRequest approveRequest);

    void updApprove(ApproveRequest approveRequest);

    void delApprove(String uuid);

    Object getApproveList(String uuid,int p, int c);

    ApproveEntity getApproveInfo(String uuid);

}
