package com.itdev.listener;

import com.itdev.controller.AccountController;
import com.itdev.controller.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class OperationsConsoleListener {

    private static final String MAIN_MESSAGE = """
            Please enter one of operation type:
            -ACCOUNT_CREATE
            -SHOW_ALL_USERS
            -ACCOUNT_CLOSE
            -ACCOUNT_WITHDRAW
            -ACCOUNT_DEPOSIT
            -ACCOUNT_TRANSFER
            -USER_CREATE
            """;
    private static final Set<String> OPERATIONS = Set.of(
            "ACCOUNT_CREATE",
            "SHOW_ALL_USERS",
            "ACCOUNT_CLOSE",
            "ACCOUNT_WITHDRAW",
            "ACCOUNT_DEPOSIT",
            "ACCOUNT_TRANSFER",
            "USER_CREATE"
    );

    private final AccountController accountController;
    private final UserController userController;

    public void doListen(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(MAIN_MESSAGE);
            while (scanner.hasNext()) {
                String maybeOperation = scanner.nextLine();
                if (isValid(maybeOperation)) {
                    Operation operation = Operation.valueOf(maybeOperation);
                    switch (operation) {
                        case ACCOUNT_CREATE -> accountCreate(scanner);
                        case SHOW_ALL_USERS -> showAllUsers();
                        case ACCOUNT_CLOSE -> accountClose(scanner);
                        case ACCOUNT_WITHDRAW -> accountWithdraw(scanner);
                        case ACCOUNT_DEPOSIT -> accountDeposit(scanner);
                        case ACCOUNT_TRANSFER -> accountTransfer(scanner);
                        case USER_CREATE -> userCreate(scanner);
                    }
                } else {
                    System.out.println("Unsupported operation. Try again with the operation from the list.");
                }
                System.out.println(MAIN_MESSAGE);
            }
        }
    }

    private boolean isValid(String maybeOperation) {
        return OPERATIONS.contains(maybeOperation);
    }

    public void accountCreate(Scanner scanner) {
        System.out.println("Enter the user id for which to create an account:");
        Optional<Integer> maybeId = listenId(scanner);
        if (maybeId.isEmpty()) return;
        Integer id = maybeId.get();
        System.out.println(accountController.create(id));
    }

    public void showAllUsers() {
        System.out.println(userController.findAll());
    }

    public void accountClose(Scanner scanner) {
        System.out.println("Enter account ID to close:");
        Optional<Integer> maybeId = listenId(scanner);
        if (maybeId.isEmpty()) return;
        Integer id = maybeId.get();

        System.out.println(accountController.close(id));
    }

    public void accountWithdraw(Scanner scanner) {
        System.out.println("Enter account ID:");
        Optional<Integer> maybeId = listenId(scanner);
        if (maybeId.isEmpty()) return;
        Integer id = maybeId.get();

        System.out.println("Enter amount to withdraw:");
        Optional<BigDecimal> maybeAmount = listenAmount(scanner);
        if (maybeAmount.isEmpty()) return;
        BigDecimal amount = maybeAmount.get();

        System.out.println(accountController.withdraw(id, amount));
    }

    public void accountDeposit(Scanner scanner) {
        System.out.println("Enter account ID:");
        Optional<Integer> maybeId = listenId(scanner);
        if (maybeId.isEmpty()) return;
        Integer id = maybeId.get();

        System.out.println("Enter amount to deposit:");
        Optional<BigDecimal> maybeAmount = listenAmount(scanner);
        if (maybeAmount.isEmpty()) return;
        BigDecimal amount = maybeAmount.get();

        System.out.println(accountController.deposit(id, amount));
    }

    public void accountTransfer(Scanner scanner) {
        System.out.println("Enter source account ID:");
        Optional<Integer> maybeId = listenId(scanner);
        if (maybeId.isEmpty()) return;
        Integer idFrom = maybeId.get();

        System.out.println("Enter target account ID:");
        maybeId = listenId(scanner);
        if (maybeId.isEmpty()) return;
        Integer idTo = maybeId.get();

        System.out.println("Enter amount to transfer:");
        Optional<BigDecimal> maybeAmount = listenAmount(scanner);
        if (maybeAmount.isEmpty()) return;
        BigDecimal amount = maybeAmount.get();

        System.out.println(accountController.transfer(idFrom, idTo, amount));
    }

    public void userCreate(Scanner scanner) {
        System.out.println("Enter login for new user:");
        if (scanner.hasNext()) {
            String login = scanner.nextLine();
            System.out.println(userController.create(login));
        }
    }

    private Optional<Integer> listenId(Scanner scanner) {
        Optional<Integer> id;
        if (scanner.hasNext()) {
            String maybeId = scanner.nextLine();
            try {
                id = Optional.of(Integer.parseInt(maybeId));
            } catch (NumberFormatException e) {
                System.out.println("Invalid id. Please enter a positive integer.");
                return Optional.empty();
            }
            if (id.get() < 1) {
                System.out.println("Invalid id. Please enter a positive integer.");
                return Optional.empty();
            }
            return id;
        }
        return Optional.empty();
    }

    private Optional<BigDecimal> listenAmount(Scanner scanner) {
        Optional<BigDecimal> amount;
        if (scanner.hasNext()) {
            String maybeAmount = scanner.nextLine();
            try {
                amount = Optional.of(new BigDecimal(maybeAmount));
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a positive number.");
                return Optional.empty();
            }
            if (amount.get().compareTo(BigDecimal.ONE) < 0) {
                System.out.println("Invalid amount. Please enter a positive number.");
                return Optional.empty();
            }
            return amount;
        }
        return Optional.empty();
    }
}
//    Pavel Sorokin
