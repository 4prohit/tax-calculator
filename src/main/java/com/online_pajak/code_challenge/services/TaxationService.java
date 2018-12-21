package com.online_pajak.code_challenge.services;

import com.online_pajak.code_challenge.models.Person;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.online_pajak.code_challenge.services.MetadataService.getTaxRelief;

public class TaxationService {

    private static final Logger logger = Logger.getLogger(TaxationService.class);

    /**
     * Calculate income tax
     */
    public static long calculateIncomeTax(Person person) {
        long taxableIncome = getTaxableIncome(person);
        long incomeTax = 0;
        final List<String> calculationReport = new ArrayList<>();
        if (0 < taxableIncome) {
            final Map<Integer, Long> taxationSchemes = MetadataService.getTaxationSchemes();
            long currentLowerLimit = 0;
            for (Integer taxRate : taxationSchemes.keySet()) {
                final double percent = taxRate / 100.0;
                if (0 > taxationSchemes.get(taxRate)) {
                    /* If income is greater than top slab. */
                    calculationReport.add(getMessage(taxableIncome, taxRate));
                    incomeTax += taxableIncome * percent;
                    break;
                } else {
                    /* If income is in smaller than top slab */
                    long taxSlab = taxationSchemes.get(taxRate) - currentLowerLimit;
                    if (taxSlab >= taxableIncome) {
                        /*
                            If income is smaller than current slab,
                            then calculate income tax based on income.
                        */
                        calculationReport.add(getMessage(taxableIncome, taxRate));
                        incomeTax += taxableIncome * percent;
                        break;
                    } else {
                        /*
                            If income is greater than current slab,
                            then calculate income tax based portion of the current slab.
                        */
                        calculationReport.add(getMessage(taxSlab, taxRate));
                        incomeTax += taxSlab * percent;
                        taxableIncome -= taxSlab;
                    }
                }
                /* For next iteration, set lower limit to current slab's upper limit. */
                currentLowerLimit = taxationSchemes.get(taxRate);
            }
        }
        logger.info(String.format("Income tax calculation: %s = %s IDR", String.join(" + ", calculationReport), incomeTax));
        return incomeTax;
    }

    /**
     * Get message for calculation report
     */
    private static String getMessage(long taxableIncome, int taxRate) {
        return "(" + taxRate + "% * " + taxableIncome + ")";
    }

    /**
     * Get person;s table income based on tax relief
     */
    private static long getTaxableIncome(Person person) {
        final long taxRelief = getTaxRelief(person);
        long taxableIncome = (person.getSalary() * 12) - taxRelief;
        if (0 > taxableIncome) {
            taxableIncome = 0;
        }
        logger.info(String.format("Your taxable income is ((%s * 12) - %s) = %s IDR", person.getSalary(), taxRelief, taxableIncome));
        return taxableIncome;
    }
}
