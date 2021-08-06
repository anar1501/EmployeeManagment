package az.company.employee.dao.concrets;

import az.company.employee.connection.concrets.DbConnection;
import az.company.employee.dao.abstracts.PermissionDaoService;
import az.company.employee.model.concrets.Permission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermissionDaoManager implements PermissionDaoService {

    @Override
    public List<Permission> findAll() {
        List<Permission> permissions = new ArrayList<>();
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement pr = con.prepareStatement("select * from permission");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Permission p = new Permission();
                p.setId(resultSet.getInt(1));
                p.setPageName(resultSet.getString(2));
                permissions.add(p);
            }
        } catch (Exception e) {
            System.out.println("findAll error: " + e);
        }
        return permissions;
    }

    @Override
    public Permission findById(int permissionId) {
        try (Connection c = DbConnection.getConnection()) {
            PreparedStatement p = c.prepareStatement("select id,page_name from permission where id=?");
            p.setInt(1, permissionId);
            ResultSet resultSet = p.executeQuery();
            if (resultSet.next()) {
                Permission permission = new Permission();
                permission.setId(resultSet.getInt(1));
                permission.setPageName(resultSet.getString(2));
                return permission;
            }

        } catch (Exception e) {
            System.out.println("findById error: " + e);
        }
        return null;
    }

}
