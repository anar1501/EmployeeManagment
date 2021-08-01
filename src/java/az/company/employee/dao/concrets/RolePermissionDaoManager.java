package az.company.employee.dao.concrets;

import az.company.employee.connection.concrets.DbConnection;
import az.company.employee.dao.abstracts.RolePermissionDaoService;
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
    public int findPermissionIdByRoleId(int roleId) {
        try (Connection c = DbConnection.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "select p.id from role_permission rp "
                    + "join permission p on p.id=rp.permission_id "
                    + "where rp.role_id=,");
            p.setInt(1, roleId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("findPermissionIdByRoleId error: " + e);
        }
        return 0;
    }

    @Override
    public List<RolePermission> findRoleAndPermissionId(int roleId, int permissionId) {
        List<RolePermission> rolePermissions = new ArrayList<>();
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement pr = con.prepareStatement(
                    "select p.page_name,p.id from role_permission as rp "
                    + "inner join permission as p on rp.permission_id=p.id "
                    + "where rp.role_id=? and rp.permission_id=?");
            pr.setInt(1, roleId);
            pr.setInt(2, roleId);
            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()) {

                RolePermission rp = new RolePermission();
                rp.setName(resultSet.getString(1));
                rp.setPermissionId(resultSet.getInt(2));
                rolePermissions.add(rp);

            }
        } catch (Exception e) {
            System.out.println("findRoleAndPermissionId error: " + e);
        }
        return rolePermissions;
    }

}
