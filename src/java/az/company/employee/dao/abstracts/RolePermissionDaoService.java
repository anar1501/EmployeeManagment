package az.company.employee.dao.abstracts;

import az.company.employee.model.concrets.RolePermission;
import java.util.List;

public interface RolePermissionDaoService {

    List<RolePermission> findAll();

    void save(int roleId, int permissionId);

}
