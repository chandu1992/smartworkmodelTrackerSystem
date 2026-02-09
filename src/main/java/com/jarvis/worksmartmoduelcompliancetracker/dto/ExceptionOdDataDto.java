package com.jarvis.worksmartmoduelcompliancetracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExceptionOdDataDto {

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

}
