package com.itdev.dao.repository;

import com.itdev.dao.entity.Account;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AccountRepository {

    private final Map<Integer, Account> accounts;

    public AccountRepository() {
        this.accounts = new HashMap<>();
    }

    public Optional<Account> findById(Integer id) {
        return Optional.of(id)
                .map(accounts::get);
    }

    public Account create(Account account) {
        return Optional.of(account)
                .map(a -> accounts.put(a.getId(), a))
                .orElseThrow(() -> new RuntimeException("Unable to create an account with id" + account.getId()));
    }

    public Account update(Account account) {
        return account;
    }

    public void delete(Account account) {
        accounts.remove(account.getId());
    }
}
