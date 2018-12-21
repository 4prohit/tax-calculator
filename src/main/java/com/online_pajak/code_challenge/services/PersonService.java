package com.online_pajak.code_challenge.services;

import com.online_pajak.code_challenge.models.Person;
import org.apache.log4j.Logger;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.asList;

public class PersonService {

    private static final Logger logger = Logger.getLogger(PersonService.class);

    /**
     * Read person data from program arguments
     */
    public static Person getPerson(String[] args) {
        if (null == args || 3 > args.length) {
            throw new IllegalArgumentException("Please provide valid arguments for the program. (Salary, Single(S)/Married(M), No. of Dependents)");
        }
        return Person.builder()
                .salary(getSalary(args[0]))
                .isMarried(getMaritalStatus(args[1]))
                .noOfDependents(getNoOfDependents(args[2]))
                .build();
    }

    /**
     * Read first argument as salary
     */
    private static long getSalary(String input) {
        if (null == input || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide valid number for salary.");
        }
        long salary = parseLong(input.trim());
        if (0 > salary) {
            throw new IllegalArgumentException("Please provide positive number for salary.");
        }
        logger.info(String.format("Salary: %s", salary));
        return salary;
    }

    /**
     * Read second argument as marital status
     */
    private static boolean getMaritalStatus(String input) {
        if (null == input || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide valid character S or M for marital status.");
        }
        if (!asList("s", "m").contains(input.trim().toLowerCase())) {
            throw new IllegalArgumentException("Please provide 'S' for single and 'M' for married.");
        }
        logger.info(String.format("Marital Status: %s", input.trim()));
        return !"s".equalsIgnoreCase(input.trim());
    }

    /**
     * Read third argument as no. of dependents
     */
    private static int getNoOfDependents(String input) {
        if (null == input || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide valid number for no. of dependents.");
        }
        int noOfDependents = parseInt(input.trim());
        if (0 > noOfDependents) {
            throw new IllegalArgumentException("Please provide positive number for no. of dependents.");
        }
        logger.info(String.format("No. of dependents: %s", noOfDependents));
        return noOfDependents;
    }
}
