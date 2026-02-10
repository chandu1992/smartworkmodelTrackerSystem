package com.jarvis.worksmartmoduelcompliancetracker.repository;

import com.jarvis.worksmartmoduelcompliancetracker.entity.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMasterRepo extends JpaRepository<RoleMaster,Integer> {
}
