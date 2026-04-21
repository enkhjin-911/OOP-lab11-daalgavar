package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankAccount {
    private static final Logger logger = LogManager.getLogger(BankAccount.class);
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        logger.info("BankAccount created. Initial balance={}", initialBalance);
    }

    public void deposit(double amount) {
        logger.trace("Entering deposit() method, amount={}", amount);
        if (amount < 0) {
            logger.warn("Invalid input: amount={} is negative", amount);
            return;
        }
        logger.debug("Balance before deposit={}", balance);
        balance += amount;
        logger.info("Successful deposit: amount={}, new balance={}", amount, balance);
        logger.trace("Exiting deposit() method");
    }

    public void withdraw(double amount) {
        logger.trace("Entering withdraw() method, amount={}", amount);
        if (amount < 0) {
            logger.warn("Invalid input: amount={} is negative", amount);
            return;
        }
        logger.debug("Balance before withdraw={}", balance);
        if (amount > balance) {
            logger.error("Insufficient balance! Amount to withdraw={}, Current balance={}", amount, balance);
            return;
        }
        balance -= amount;
        logger.info("Successful withdraw: amount={}, new balance={}", amount, balance);
        logger.trace("Exiting withdraw() method");
    }

    public double getBalance() {
        logger.trace("getBalance() called, value={}", balance);
        return balance;
    }

    // FATAL example ? critical condition
    public void simulateFatal() {
        logger.fatal("CRITICAL ERROR: Configuration file not found, system is crashing!");
    }
}
