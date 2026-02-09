package com.jarvis.worksmartmoduelcompliancetracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_wsm_status")
public class OdStatusMasterEntity {

    @Id
    private int id;
    private int odSatatusId;
    private String odStatusText;
}
