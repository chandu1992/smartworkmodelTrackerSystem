package com.jarvis.worksmartmoduelcompliancetracker.service;

import com.jarvis.worksmartmoduelcompliancetracker.dto.ExceptionOdDataDto;
import com.jarvis.worksmartmoduelcompliancetracker.entity.ExceptionOdDataEntity;

import java.util.List;

public interface ExceptionalOdRaiseService {
    String raiseExceptionOdata(String jwtTokenId, String username, String empId, ExceptionOdDataDto odData);

    String deleteOdByUser(long request);

    List<ExceptionOdDataEntity> getPendingReqForEmp(String empId);
}
