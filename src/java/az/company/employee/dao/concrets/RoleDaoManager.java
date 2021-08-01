package az.company.employee.dao.concrets;

import az.company.employee.connection.concrets.DbConnection;
import az.company.employee.dao.abstracts.RoleDaoService;
import az.company.employee.model.concrets.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoManager implements RoleDaoService {

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement pr = con.prepareStatement("select * from role");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Role r = new Role();
                r.setId(resultSet.getInt(1));
                r.setName(resultSet.getString(2));
                r.setStatus(resultSet.getInt(3));
                roles.add(r);
            }
        } catch (Exception e) {
            System.out.println("findAll error: " + e);
        }
        return roles;
    }

    @Override
    public List<Role> findRolenameById(int roleId) {
        List<Role> roles = new ArrayList<>();
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement pr = con.prepareCall(
                    "select p.id,p.page_name from role_permission rp "
                    + "left join permission p "
                    + "on rp.permission_id=p.id "
                    + "where rp.status=1 and rp.role_id=?");
            pr.setInt(1, roleId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt(1));
                role.setName(rs.getString(2));
                roles.add(role);
            }

        } catch (Exception e) {
            System.out.println("findRolenameById error: " + e);
        }
        return roles;
    }

    @Override
    public List<String> findRoleIdById(int roleId) {
        List<String> permissionsId = new ArrayList();
        try (Connection c = DbConnection.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "select p.page_name from role_permission rp "
                    + "inner join permission p "
                    + "on rp.permission_id=p.id "
                    + "where rp.role_id=?");
            p.setInt(1, roleId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                permissionsId.add(rs.getString(1));
            }

        } catch (Exception e) {
            System.out.println("findPermissionOfRoleById error: " + e);
        }
        return permissionsId;
    }

    @Override
    public List<Role> findPermissionIdByRoleId(int roleId) {
        List<Role> rolesPermission = new ArrayList<>();
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement pr = con.prepareStatement(
                    "select p.page_name,p.id from role_permission rp "
                    + "left join permission p on p.id=rp.permission_id "
                    + "where rp.role_id=?");
            pr.setInt(1, roleId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Role role = new Role();
                role.setPermissionName(rs.getString(1));
                rolesPermission.add(role);
            }
        } catch (Exception e) {
            System.out.println("findRoleIdByUserId error:" + e);
        }
        return rolesPermission;

    }
}
