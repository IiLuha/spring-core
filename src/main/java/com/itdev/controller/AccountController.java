package com.itdev.controller;

import com.itdev.dao.entity.Account;
import com.itdev.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    public String create(Integer userId) {
        Account account = accountService.create(userId);
        return account + " was created";
    }

    public String close(Integer id) {
        accountService.delete(id);
        return "The account with ID %s has been successfully deleted.".formatted(id);
    }

    public String transfer(Integer idFrom, Integer idTo, BigDecimal amount) {
        accountService.transferByIds(idFrom, idTo, amount);
        return "Amount %s transferred from account ID %s to account ID %s.".formatted(amount, idFrom, idTo);
    }

    public String withdraw(Integer id, BigDecimal amount) {
        accountService.withdraw(id, amount);
        return "Amount %s was withdrawn from account ID: %s".formatted(amount, id);
    }

    public String deposit(Integer id, BigDecimal amount) {
        accountService.deposit(id, amount);
        return "Amount %s deposited to account ID: %s".formatted(amount, id);
    }
}
