package com.itdev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Account {

    Integer id;
    Integer userId;
    BigDecimal moneyAmount;
}
