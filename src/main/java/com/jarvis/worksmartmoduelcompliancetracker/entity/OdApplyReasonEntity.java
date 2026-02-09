package com.jarvis.worksmartmoduelcompliancetracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_od_reasons")
public class OdApplyReasonEntity {

    @Id
    private long id;
    private String reason;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "OdApplyReasonEntity{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                '}';
    }
}
