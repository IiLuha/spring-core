package com.itdev.controller;

import com.itdev.dao.entity.User;
import com.itdev.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public String create(String login) {
        User user = userService.create(login);
        return "User created: " + user;
    }

    public String findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return "There are no users yet.";
        } else {
            StringBuilder stringBuilder = new StringBuilder("List of all users: ");
            users.forEach(user -> stringBuilder.append(System.lineSeparator())
                    .append(user));
            return  stringBuilder.toString();
        }
    }
}
/*
SHOW_ALL_USERS
USER_CREATE
Pavel Sorokin
USER_CREATE
Vasya Pypkin
SHOW_ALL_USERS
ACCOUNT_CREATE
1
ACCOUNT_DEPOSIT
11
100
ACCOUNT_DEPOSIT
3
100
ACCOUNT_TRANSFER
3
1
600
ACCOUNT_TRANSFER
1
2
1600
ACCOUNT_TRANSFER
1
2
1100
ACCOUNT_WITHDRAW
1
100
ACCOUNT_WITHDRAW
2
40
ACCOUNT_CLOSE
1
ACCOUNT_CLOSE
2
ACCOUNT_DEPOSIT
3
100
SHOW_ALL_USERS
ACCOUNT_CLOSE
3
SHOW_ALL_USERS

*/