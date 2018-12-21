package com.online_pajak.code_challenge;

import org.apache.log4j.Logger;

import static com.online_pajak.code_challenge.services.PersonService.getPerson;
import static com.online_pajak.code_challenge.services.TaxationService.calculateIncomeTax;
import static org.apache.log4j.BasicConfigurator.configure;

public class TaxCalculator {

    private static final Logger logger = Logger.getLogger(TaxCalculator.class);

    static {
        configure();
    }

    public static void main(String[] args) {

        logger.info("Welcome to Income Tax Calculator!");

        logger.info(String.format("Your income tax is %s IDR", calculateIncomeTax(getPerson(args))));
    }
}
