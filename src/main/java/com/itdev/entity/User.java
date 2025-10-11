package com.itdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class User {

    Integer id;
    String login;
    @Builder.Default
    List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        account.setUserId(this.id);
        accounts.add(account);
    }
}
