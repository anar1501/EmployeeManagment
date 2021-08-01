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

    
}
