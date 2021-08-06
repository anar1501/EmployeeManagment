package az.company.employee.model.concrets;

import az.company.employee.model.abstracts.Entity;

public class RolePermission implements Entity {

    private int roleId;

    private int permissionId;

    private String permissionName;

    private int status;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RolePermission{" + "roleId=" + roleId + ", permissionId=" + permissionId + ", permissionName=" + permissionName + ", status=" + status + '}';
    }

}
