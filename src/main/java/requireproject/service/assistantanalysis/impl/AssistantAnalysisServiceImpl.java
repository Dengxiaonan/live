package requireproject.service.assistantanalysis.impl;


import org.springframework.stereotype.Service;
import requireproject.repository.assistantanalysis.entity.AssistantAnalysisEntity;
import requireproject.repository.assistantanalysis.mapper.AssistantAnalysisMapper;
import requireproject.service.assistantanalysis.AssistantAnalysisService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AssistantAnalysisServiceImpl implements AssistantAnalysisService {
    @Resource
    private AssistantAnalysisMapper assistantAnalysisMapper;
    @Override
    public List<AssistantAnalysisEntity> getAssAll() {
        return assistantAnalysisMapper.getAssAll();
    }

    @Override
    public AssistantAnalysisEntity getAssistantInfo(String uuid) {
        return assistantAnalysisMapper.getAssistantInfo(uuid);
    }
}
