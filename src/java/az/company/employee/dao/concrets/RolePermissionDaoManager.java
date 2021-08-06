package az.company.employee.dao.concrets;

import az.company.employee.connection.concrets.DbConnection;
import az.company.employee.dao.abstracts.RolePermissionDaoService;
import az.company.employee.model.concrets.Role;
import az.company.employee.model.concrets.RolePermission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RolePermissionDaoManager implements RolePermissionDaoService {

    @Override
    public List<RolePermission> findAll() {
        List<RolePermission> rolePermissions = new ArrayList<>();
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement pr = con.prepareStatement(
                    "select p.id from role_permission as rp "
                    + "inner join permission as p on rp.permission_id=p.id");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                RolePermission r = new RolePermission();
                r.setRoleId(resultSet.getInt(1));
                r.setPermissionId(resultSet.getInt(2));
                rolePermissions.add(r);
            }
        } catch (Exception e) {
            System.out.println("findAll error: " + e);
        }
        return rolePermissions;
    }



    @Override
    public void save(int roleId, int permissionId) {

        try (Connection c = DbConnection.getConnection()) {

            PreparedStatement p = c.prepareStatement("insert into role_permission(role_id,permission_id) values(?,?)");
            p.setInt(1, roleId);
            p.setInt(2, permissionId);
            p.executeUpdate();

        } catch (Exception e) {

            System.out.println("save error: " + e);

        }
    }

}
