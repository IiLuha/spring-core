package com.itdev.controller;

import com.itdev.dao.entity.Account;
import com.itdev.exception.AccountNotFoundException;
import com.itdev.exception.DeleteFirstAccountException;
import com.itdev.exception.InsufficientFundsException;
import com.itdev.exception.UserNotFoundException;
import com.itdev.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    public String create(Integer userId) {
//        try {
            Account account = accountService.create(userId);
            return account + " was created";
//        } catch (UserNotFoundException e) {
//            return e.getMessage() +
//                    System.lineSeparator() +
//                    "Try again with another user id.";
//        }
    }

    public String close(Integer id) {
//        try {
            accountService.delete(id);
            return "The account with ID %s has been successfully deleted.".formatted(id);
//        } catch (AccountNotFoundException | DeleteFirstAccountException e) {
//            return e.getMessage() +
//                    System.lineSeparator() +
//                    "Try again with another account id.";
//        }
    }

    public String transfer(Integer idFrom, Integer idTo, BigDecimal amount) {
        accountService.transferByIds(idFrom, idTo, amount);
        return "Amount %s transferred from account ID %s to account ID %s.".formatted(amount, idFrom, idTo);
    }

    public String withdraw(Integer id, BigDecimal amount) {
//        try {
            accountService.withdraw(id, amount);
            return "Amount %s was withdrawn from account ID: %s".formatted(amount, id);
//        } catch (AccountNotFoundException e) {
//            return e.getMessage() +
//                    System.lineSeparator() +
//                    "Try again with another account id.";
//        } catch (InsufficientFundsException e) {
//            return e.getMessage() +
//                    System.lineSeparator() +
//                    "There are %s funds available.".formatted(e.getAvailableFunds());
//        }
    }

    public String deposit(Integer id, BigDecimal amount) {
//        try {
            accountService.deposit(id, amount);
            return "Amount %s deposited to account ID: %s".formatted(amount, id);
//        } catch (AccountNotFoundException e) {
//            return e.getMessage() +
//                    System.lineSeparator() +
//                    "Try again with another account id.";
//        }
    }
}
