package az.company.employee.dao.abstracts;

import az.company.employee.model.concrets.Role;
import java.util.List;

public interface RoleDaoService {

    List<Role> findAll();

    List<Role> findRolenameById(int roleId);

    List<String> findRoleIdById(int roleId);

    List<Role>  findPermissionIdByRoleId(int roleId);

}
