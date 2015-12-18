package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.UserDao;
import com.epam.ag.dao.VehicleBodyColorDao;
import com.epam.ag.dao.impl.exception.DaoException;
import com.epam.ag.model.User;
import com.epam.ag.model.dict.VehicleBodyColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class UserService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private DaoFactory daoFactory;

    public List<User> getUsersList() {
        daoFactory = DaoFactory.getInstance();
        UserDao dao = daoFactory.getDao(UserDao.class);
        List<User> users = dao.getAll();
        daoFactory.close();
        return users;
    }

    public boolean saveUser(User user) {
        daoFactory = DaoFactory.getInstance();
        UserDao dao = daoFactory.getDao(UserDao.class);
        return save(user, dao);
    }

    private boolean save(User entity, UserDao dao) {
        try {
            dao.save(entity);
        } catch (DaoException e) {
            log.error("Unable to save user", e);
            return false;
        } finally {
            daoFactory.close();
        }
        return true;
    }

    public User getUserById(Long userId) {
        daoFactory = DaoFactory.getInstance();
        UserDao dao = daoFactory.getDao(UserDao.class);
        User user = dao.getById(userId);
        daoFactory.close();
        return user;
    }

    public boolean deleteById(Long userId) {
        daoFactory = DaoFactory.getInstance();
        UserDao dao = daoFactory.getDao(UserDao.class);
        User user = dao.getById(userId);
        if (user == null) {
            daoFactory.close();
            return false;
        }

        boolean isDeleted = dao.delete(user);
        daoFactory.close();
        return isDeleted;
    }
}
