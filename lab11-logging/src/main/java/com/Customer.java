package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Customer {
    private static final Logger logger = LogManager.getLogger(Customer.class);
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        logger.debug("Customer created: name={}, email={}", name, maskEmail(email));
    }

    public String getDomain() {
        logger.trace("getDomain() called, email={}", maskEmail(email));

        // Bug: if email is null, NullPointerException will occur
        if (email == null) {
            logger.warn("email is null! getDomain returning no value.");
            return "unknown";
        }

        String domain = email.substring(email.indexOf("@") + 1).toUpperCase();
        logger.debug("Domain found: {}", domain);
        return domain;
    }

    // Mask sensitive information (Best practice)
    private static String maskEmail(String s) {
        if (s == null || s.length() < 4) return "***";
        return s.substring(0, 2) + "***" + s.substring(s.length() - 2);
    }
}
