package com.jarvis.worksmartmoduelcompliancetracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_wsm_user_role_details")
public class UserRoleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String empId;
    private String completeName;
    private String officialEmail;

    @ManyToOne
    @JoinColumn(name="roleId", referencedColumnName = "roleId", insertable = false,updatable = false)
    private RoleMaster role;

//    private int roleId;
    private String isActive;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public RoleMaster getRole() {
        return role;
    }

    public void setRole(RoleMaster role) {
        this.role = role;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "UserRoleDetailEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", empId='" + empId + '\'' +
                ", completeName='" + completeName + '\'' +
                ", officialEmail='" + officialEmail + '\'' +
                ", role=" + role +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}
