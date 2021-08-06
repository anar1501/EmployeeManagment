package az.company.employee.dao.abstracts;

import az.company.employee.enums.UserStatusEnum;
import az.company.employee.model.concrets.Role;
import az.company.employee.model.concrets.RolePermission;
import az.company.employee.model.concrets.User;
import java.util.List;

public interface UserDaoService {

    void save(User user);

    User findByEmail(String email);

    User findByActivationCode(String activationCode);

    void updateStatusById(int id, UserStatusEnum userStatusEnum);

    User findById(int id);

    void updateConfirmation(User user);

    void updateForgetPasswordConfirmation(User user);

    User findByPasswordConfirmationCode(String code);

    void updatePassword(User user);

    String findByIdAndPageUrl(int roleId, String pageUrl);

    List<User> findExceptAdmin(int id);

    int findRoleIdByUserId(int id);

    void updateRoleIdAndStatus(User user);

    Role findPermissionOfRoleById(int roleId);
}
