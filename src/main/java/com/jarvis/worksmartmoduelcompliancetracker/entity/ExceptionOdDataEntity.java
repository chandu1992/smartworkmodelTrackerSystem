package com.jarvis.worksmartmoduelcompliancetracker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Audited
@AuditTable(value="tbl_wsm_exceptional_od_data_audit")
@Table(name="tbl_wsm_exceptional_od_data")
public class ExceptionOdDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long requestId;

    private String empName;
    private String empId;
    private String username;
    private String emailId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String reason;

    private String managerName;
    private String managerEmpId;
    private String managerEmailId;

    private String approverManagerName;
    private String approverManagerEmpId;
    private String approverManagerUserName;
    private String approverManagerEmailId;

    private int weekNo;

    private String managerApprovalLevel;

    private String reasonDetails;

    private int odStatus;

    private String statusText;
    private String compliantStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime createdOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private LocalDateTime updatedOn;

    @PrePersist
    protected void onCreate(){
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedOn=LocalDateTime.now();
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmpId() {
        return managerEmpId;
    }

    public void setManagerEmpId(String managerEmpId) {
        this.managerEmpId = managerEmpId;
    }

    public String getManagerEmailId() {
        return managerEmailId;
    }

    public void setManagerEmailId(String managerEmailId) {
        this.managerEmailId = managerEmailId;
    }

    public String getApproverManagerName() {
        return approverManagerName;
    }

    public void setApproverManagerName(String approverManagerName) {
        this.approverManagerName = approverManagerName;
    }

    public String getApproverManagerEmpId() {
        return approverManagerEmpId;
    }

    public void setApproverManagerEmpId(String approverManagerEmpId) {
        this.approverManagerEmpId = approverManagerEmpId;
    }

    public String getApproverManagerUserName() {
        return approverManagerUserName;
    }

    public void setApproverManagerUserName(String approverManagerUserName) {
        this.approverManagerUserName = approverManagerUserName;
    }

    public String getApproverManagerEmailId() {
        return approverManagerEmailId;
    }

    public void setApproverManagerEmailId(String approverManagerEmailId) {
        this.approverManagerEmailId = approverManagerEmailId;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public String getManagerApprovalLevel() {
        return managerApprovalLevel;
    }

    public void setManagerApprovalLevel(String managerApprovalLevel) {
        this.managerApprovalLevel = managerApprovalLevel;
    }

    public String getReasonDetails() {
        return reasonDetails;
    }

    public void setReasonDetails(String reasonDetails) {
        this.reasonDetails = reasonDetails;
    }

    public int getOdStatus() {
        return odStatus;
    }

    public void setOdStatus(int odStatus) {
        this.odStatus = odStatus;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getCompliantStatus() {
        return compliantStatus;
    }

    public void setCompliantStatus(String compliantStatus) {
        this.compliantStatus = compliantStatus;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
