package ua.training.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.persistence.dao.IUserDao;
import ua.training.persistence.dao.factory.DaoFactory;
import ua.training.persistence.dao.factory.MysqlDaoFactory;
import ua.training.persistence.entities.User;

import java.util.Optional;

public class SignInService {
    private static final Logger log = LogManager.getLogger(SignInService.class);
    private DaoFactory daoFactory;
    private static SignInService instance;

    public static SignInService getInstance() {
        if (instance == null) {
            instance = new SignInService(MysqlDaoFactory.getInstance());
        }
        return instance;
    }

    private SignInService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Optional<User> getAuthorizedUser(String login, String password) {
        final IUserDao userDao = daoFactory.getUserDao();
        final Optional<User> foundUser = userDao.getUserByEmailAndPassword(login, password);
        foundUser.ifPresent(user -> user.setPassword(null));
        return foundUser;
    }
}
