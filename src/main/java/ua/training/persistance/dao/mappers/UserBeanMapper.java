package ua.training.persistance.dao.mappers;

import ua.training.persistance.beans.User;
import ua.training.persistance.beans.UserType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class UserBeanMapper {
     private static final String ID = "id";
     private static final String PASSWORD = "password";
     private static final String FIRST_NAME = "first_name";
     private static final String LAST_NAME = "last_name";
     private static final String EMAIL = "email";
     private static final String ADDRESS = "address";
     private static final String IS_PHYSICAL = "is_physical";
     private static final String USER_TYPE_ID = "user_type_id";


    public Optional<User> mapToUser(ResultSet resultSet) {
        Optional<User> optionalUser = Optional.empty();

        try {
            if (resultSet.next()) {
                final long id = resultSet.getLong(ID);
                final String login = resultSet.getString(EMAIL);
                final String firstName = resultSet.getString(FIRST_NAME);
                final String lastName= resultSet.getString(LAST_NAME);
                final String pwd = resultSet.getString(PASSWORD);
                final long userTypeId = resultSet.getLong(USER_TYPE_ID);
                final String type = resultSet.getString(UserTypeBeanMapper.TYPE);
                final UserType userType = new UserType(userTypeId, type);

                final User user = new User(id, firstName, lastName, login, pwd);
                user.setUserType(userType);

                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getCause().toString());
//            throw new BeanMappingException("can't map User bean due to error", e.getCause());
            //TODO: add logger here
        }

        return optionalUser;
    }
}
