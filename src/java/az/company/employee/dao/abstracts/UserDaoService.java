package az.company.employee.dao.abstracts;

import az.company.employee.enums.UserStatusEnum;
import az.company.employee.model.concrets.User;

public interface UserDaoService {

    void save(User user);

    User findByEmail(String email);

    User findByActivationCode(String activationCode);

    void updateStatusById(int id, UserStatusEnum userStatusEnum);

    User findById(int id);

    public void updateConfirmation(User user);
}
