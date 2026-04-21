package com.lab11;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.BankAccount;
import com.Customer;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("=== Program started ===");

        BankAccount account = new BankAccount(1000.0);

        // 1. Normal deposit
        account.deposit(500.0);

        // 2. Negative deposit (WARN will occur)
        account.deposit(-200.0);

        // 3. Normal withdraw
        account.withdraw(300.0);

        // 4. Excessive withdraw (ERROR will occur)
        account.withdraw(9999.0);

        // 5. FATAL example
        account.simulateFatal();

        // Customer debug
        logger.info("=== Customer test ===");
        Customer c1 = new Customer("Bold", "bold@example.com");
        logger.info("Domain: {}", c1.getDomain());

        // NullPointerException fixed test
        Customer c2 = new Customer("Erhes", null);
        logger.info("Null email domain: {}", c2.getDomain());

        logger.info("=== Program finished ===");
    }
}
