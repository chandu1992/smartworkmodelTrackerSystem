package com.jarvis.worksmartmoduelcompliancetracker.dto;

import com.jarvis.worksmartmoduelcompliancetracker.entity.RoleMaster;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UserRoleDetailsDto {
    private String username;
    private String empId;
    private String completeName;
    private String officialEmail;
    private RoleMaster role;
    private String isActive;

}
