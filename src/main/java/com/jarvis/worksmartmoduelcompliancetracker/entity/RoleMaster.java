package com.jarvis.worksmartmoduelcompliancetracker.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

@Entity
@Table(name="tbl_wms_user_role_master")
public class RoleMaster {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int roleId;

    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleMaster{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
