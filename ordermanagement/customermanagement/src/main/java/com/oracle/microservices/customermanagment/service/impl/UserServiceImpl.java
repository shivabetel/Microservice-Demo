package com.oracle.microservices.customermanagment.service.impl;

import com.oracle.microservices.common.web.exception.BusinessException;
import com.oracle.microservices.customermanagment.persistence.dao.IUserJpaDao;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.customermanagment.service.IUserService;
import com.oracle.microservices.common.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractService<User, Long> implements IUserService {

    private IUserJpaDao dao;

    public UserServiceImpl(IUserJpaDao userJpaDao) {
        this.dao = userJpaDao;
    }

    @Override
    public Optional<User> findByEmailId(String emailId) {
        return getDao().findByEmailId(emailId);
    }

    @Override
    public User save(User entity){
        Optional<User> existingUser =  getDao().findByEmailId(entity.getEmailId());
        if(existingUser.isPresent()){
            throw new BusinessException("User already exists with the email id "+entity.getEmailId());
        }

        return super.save(entity);
    }

    @Override
    public IUserJpaDao getDao() {
        return dao;
    }
}
