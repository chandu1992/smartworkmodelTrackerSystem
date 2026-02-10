package com.jarvis.worksmartmoduelcompliancetracker.service;

import com.jarvis.worksmartmoduelcompliancetracker.entity.ExceptionOdDataEntity;

import java.util.List;

public interface AdminService {
    List<ExceptionOdDataEntity> getRaiseExceptionDataForAdmin();
}
