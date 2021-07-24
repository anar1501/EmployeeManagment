package az.company.employee.dao.concrets;

import az.company.employee.connection.concrets.DbConnection;
import az.company.employee.dao.abstracts.UserDaoService;
import az.company.employee.enums.UserStatusEnum;
import az.company.employee.model.concrets.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            PreparedStatement preparedStatement = connection.prepareStatement("select id,email,password,name,surname,status from users where email=?");
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
                user.setExpiredDate(LocalDateTime.parse(resultSet.getString(2), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
            PreparedStatement preparedStatement = connection.prepareStatement("select email,name from users where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setEmail(resultSet.getString(1));
                user.setName(resultSet.getString(2));
                
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
    
}
