package com.itdev.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@Builder
public class Account {

    Integer id;
    Integer userId;
    BigDecimal moneyAmount;
}
