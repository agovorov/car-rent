package com.epam.ag.service;

import com.epam.ag.dao.AddressDao;
import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.IDDocumentDao;
import com.epam.ag.dao.UserDao;
import com.epam.ag.dao.impl.exception.DaoException;
import com.epam.ag.model.User;
import com.epam.ag.model.user.Address;
import com.epam.ag.model.user.IDDocument;
import com.epam.ag.model.user.Passport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class UserService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public List<User> getUsersList() {
        daoFactory = DaoFactory.getInstance();
        UserDao dao = daoFactory.getDao(UserDao.class);
        List<User> users = dao.getAll();
        daoFactory.close();
        return users;
    }

    public boolean saveUser(User user) {
        daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        UserDao userDao = daoFactory.getDao(UserDao.class);

        // Save passport if exists
        if (user.getPassport() != null) {
            // Looking for a new
            Passport passport = (Passport) user.getPassport();
            passport.setOwner(user);

            // Save address if exists
            if (passport.getLivingAddress() != null) {
                Address address = passport.getLivingAddress();
                AddressDao addressDao = daoFactory.getDao(AddressDao.class);
                try {
                    addressDao.save(address);
                } catch (Exception e) {
                    log.error("Unable to save address model", e);
                    daoFactory.rollback();
                    daoFactory.close();
                    return false;
                }
            }

            // Save passport
            IDDocumentDao documentDao = daoFactory.getDao(IDDocumentDao.class);
            try {
                documentDao.save(passport);
            } catch (Exception e) {
                log.error("Unable to save passport model", e);
                daoFactory.rollback();
                daoFactory.close();
                return false;
            }

        }

        // Save user
        try {
            //userDao.save(user);
        } catch (DaoException e) {
            log.error("Unable to save user", e);
            daoFactory.rollback();
            daoFactory.close();
            return false;
        }
        daoFactory.commit();
        daoFactory.close();
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

    public User getUserByEmail(String email) {
        daoFactory = DaoFactory.getInstance();
        UserDao dao = daoFactory.getDao(UserDao.class);
        User user = dao.getByEmail(email);
        daoFactory.close();
        return user;
    }

    public void loadPassportData(User user) {
        daoFactory = DaoFactory.getInstance();
        IDDocumentDao documentDao = daoFactory.getDao(IDDocumentDao.class);
        IDDocument passport = documentDao.getByUser(user);
        daoFactory.close();
    }
}
