package com.jarvis.worksmartmoduelcompliancetracker.repository;

import com.jarvis.worksmartmoduelcompliancetracker.entity.OdApplyReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdReasonMasterRepo extends JpaRepository<OdApplyReasonEntity,Long> {
}
