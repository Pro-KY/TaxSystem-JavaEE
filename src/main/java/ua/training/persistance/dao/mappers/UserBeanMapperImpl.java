package ua.training.persistance.dao.mappers;

import ua.training.persistance.beans.User;
import ua.training.persistance.beans.UserType;
import ua.training.util.exceptions.BeanMappingException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserBeanMapperImpl implements BeanMapper<User> {
    public static final String ID = "id";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String ORGANIZATION = "organization";
    public static final String USER_TYPE_ID = "user_type_id";


    @Override
    public Optional<User> mapRow(ResultSet resultSet) throws BeanMappingException {
        Optional<User> optionalUser = Optional.empty();

        try {
            if (resultSet.next()) {
                final long id = resultSet.getLong(ID);
                final String email = resultSet.getString(EMAIL);
                final String firstName = resultSet.getString(FIRST_NAME);
                final String lastName= resultSet.getString(LAST_NAME);
                final String organization = resultSet.getString(ORGANIZATION);
                final String adress = resultSet.getString(ADDRESS);
                final String password = resultSet.getString(PASSWORD);
                final long userTypeId = resultSet.getLong(USER_TYPE_ID);
                final String type = resultSet.getString(UserTypeBeanMapper.TYPE);
                final UserType userType = new UserType(userTypeId, type);

                final User user = new User(id, firstName, lastName, organization, email, password, adress, userType);
                optionalUser = Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getCause().toString());
            throw new BeanMappingException("can't map User bean due to absent of some fields", e.getCause());
            //TODO: add logger here
        }

        return optionalUser;
    }
}