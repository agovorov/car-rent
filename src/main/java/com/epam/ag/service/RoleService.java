package com.epam.ag.service;

import com.epam.ag.dao.DaoFactory;
import com.epam.ag.dao.UserRoleDao;
import com.epam.ag.model.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Govorov Andrey.
 */
public class RoleService extends BaseService {

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);

    public List<UserRole> getRolesList() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        UserRoleDao dao = daoFactory.getDao(UserRoleDao.class);
        List<UserRole> roles = dao.getAll();
        daoFactory.close();

        return roles;
    }
}
