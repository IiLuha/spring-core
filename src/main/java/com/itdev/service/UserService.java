package com.itdev.service;

import com.itdev.dao.entity.Account;
import com.itdev.dao.entity.User;
import com.itdev.dao.repository.AccountRepository;
import com.itdev.dao.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserService {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private int idSequence;

    public UserService(AccountService accountService, AccountRepository accountRepository, UserRepository userRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.idSequence = 1;
    }

    public User create(String login) {
        if (userRepository.isLoginExist(login)) {
            return null;
        } else {
            User user = User.builder()
                    .id(getNextId())
                    .login(login)
                    .build();
            Account acc = accountService.getDefaultAcc(user.getId());
            user.addAccount(acc);
            accountRepository.create(acc);
            return userRepository.create(user);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private int getNextId() {
        return idSequence++;
    }
}
