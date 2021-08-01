package az.company.employee.dao.concrets;

import az.company.employee.connection.concrets.DbConnection;
import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.enums.UserStatusEnum;
import az.company.employee.model.concrets.RolePermission;
import az.company.employee.model.concrets.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDaoManager implements UserDaoService {

    @Override
    public void save(User user) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users(email,password,name,surname,activation_code,expired_date) values(?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getActivationCode());
            preparedStatement.setString(6, user.getExpiredDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("save error: " + e);

        }
    }

    @Override
    public User findByEmail(String email) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select u.id,u.email,u.password,u.name,u.surname,u.status,u.role_id,r.name from users u "
                    + "left join role r "
                    + "on r.id=u.role_id "
                    + "where email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setName(resultSet.getString(4));
                user.setSurname(resultSet.getString(5));
                user.setStatus(resultSet.getInt(6));
                user.setRoleId(resultSet.getInt(7));
                user.setRoleName(resultSet.getString(8));
                return user;

            }
        } catch (Exception e) {
            System.out.println("findByEmail error: " + e);
        }
        return null;
    }

    @Override
    public User findByActivationCode(String activationCode) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select id,expired_date,status from users where activation_code=?");
            preparedStatement.setString(1, activationCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setExpiredDate(LocalDateTime.parse(resultSet.getString(2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
                user.setStatus(resultSet.getInt(3));
                return user;
            }
        } catch (Exception e) {
            System.out.println("findByActivationCode error " + e);
        }
        return null;
    }

    @Override
    public void updateStatusById(int id, UserStatusEnum userStatusEnum) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set status=? where id=?");
            preparedStatement.setInt(1, userStatusEnum.getValue());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateStatusById error" + e);
        }
    }

    @Override
    public User findById(int id) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select id,email,name,created_date,status,role_id from users where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setCreatedDate(LocalDateTime.parse(resultSet.getString(4), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
                user.setStatus(resultSet.getInt(5));
                user.setRoleId(resultSet.getInt(6));
                return user;
            }
        } catch (Exception e) {
            System.out.println("findById error: " + e);
        }
        return null;
    }

    @Override
    public void updateConfirmation(User user) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set activation_code=?, expired_date=? where id=?");
            preparedStatement.setString(1, user.getActivationCode());
            preparedStatement.setString(2, user.getExpiredDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateConfirmation error: " + e);
        }
    }

    @Override
    public void updateForgetPasswordConfirmation(User user) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set password_confirmation_code=?, password_expired_date=? where id=?");
            preparedStatement.setString(1, user.getPswActivationCode());
            preparedStatement.setString(2, user.getPswExpiredDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateForgetPasswordConfirmation error: " + e);
        }
    }

    @Override
    public User findByPasswordConfirmationCode(String passwordConfirmationcode) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select id,password_expired_date from users where password_confirmation_code=?");
            preparedStatement.setString(1, passwordConfirmationcode);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setPswExpiredDate(LocalDateTime.parse(resultSet.getString(2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
                return user;
            }
        } catch (Exception e) {
            System.out.println("findByPasswordConfirmationCode error " + e);
        }
        return null;
    }

    @Override
    public void updatePassword(User user) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set password=?,password_confirmation_code=null,password_expired_date=null where id=?");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("updatePassword error : " + e);
        }
    }

    @Override
    public String findByIdAndPageUrl(int roleId, String pageUrl) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select p.page_name from role_permission rp "
                    + "join permission p "
                    + "on rp.permission_id=p.id "
                    + "where rp.status=1 and rp.role_id=? and p.page_url=?");

            preparedStatement.setInt(1, roleId);
            preparedStatement.setString(2, pageUrl);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }

        } catch (Exception e) {
            System.out.println("findByIdAndPageUrl error: " + e);
        }
        return null;
    }

    @Override
    public List<User> findExceptAdmin(int id) {
        List<User> users = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select u.id,u.name,u.created_date,r.name,u.status from users u "
                    + "left join role r "
                    + "on u.role_id=r.id "
                    + "where u.id<>?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setCreatedDate(LocalDateTime.parse(resultSet.getString(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")));
                user.setRoleName(resultSet.getString(4));
                user.setStatus(resultSet.getInt(5));
                users.add(user);
            }
        } catch (Exception e) {
            System.out.println("findExceptAdmin error: " + e);
        }
        return users;
    }

    @Override
    public int findRoleIdByUserId(int id) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement pr = con.prepareStatement("select role_id from users where id=?");
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("findRoleIdByUserId error:" + e);
        }
        return 0;
    }

    @Override
    public void updateRoleIdAndStatus(User user) {
        try (Connection con = DbConnection.getConnection()) {
            PreparedStatement pr = con.prepareStatement("update users set id=?,role_id=?, status=? where id=?");
            pr.setInt(1, user.getId());
            pr.setInt(2, user.getRoleId());
            pr.setInt(3, user.getStatus());
            pr.setInt(4, user.getId());
            pr.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateRoleIdAndStatus error: " + e);
        }
    }

    @Override
    public int findPermissionOfRoleById(int roleId) {
        try (Connection c = DbConnection.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "select p.id from role_permission rp "
                    + "left join permission p "
                    + "on rp.permission_id=p.id "
                    + "where rp.role_id=?"
            );
            p.setInt(1, roleId);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("findPermissionOfRoleById error: " + e);
        }
        return 0;

    }

}
