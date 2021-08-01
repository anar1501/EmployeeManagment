package az.company.employee.model.concrets;

import az.company.employee.model.abstracts.Entity;

public class RolePermission implements Entity {

    private int roleId;

    private int permissionId;

    private int status;
    
    private String name;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RolePermission{" + "roleId=" + roleId + ", permissionId=" + permissionId + ", status=" + status + '}';
    }

}
