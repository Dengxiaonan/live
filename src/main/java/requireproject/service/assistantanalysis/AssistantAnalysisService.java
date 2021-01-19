package requireproject.service.assistantanalysis;


import requireproject.repository.assistantanalysis.entity.AssistantAnalysisEntity;

import java.util.List;

public interface AssistantAnalysisService {
    List<AssistantAnalysisEntity> getAssAll();
    //浏览
    AssistantAnalysisEntity getAssistantInfo(String uuid);
}
