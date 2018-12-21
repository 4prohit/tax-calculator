package com.online_pajak.code_challenge.services;

import com.online_pajak.code_challenge.models.Person;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.String.valueOf;
import static java.lang.System.exit;

public class MetadataService {

    private static final Logger logger = Logger.getLogger(MetadataService.class);

    @Getter
    private static final Properties taxReliefProps;

    @Getter
    private static final Map<Integer, Long> taxationSchemes;

    /**
     * Initialize the metadata
     */
    static {
        logger.info("Reading data for taxation scheme...");
        final Properties taxationSchemeProps = getProperties("taxation-schemes.properties");

        /* Load in tree map to sort it in ascending order of tax rates */
        taxationSchemes = new TreeMap<>();
        taxationSchemeProps.forEach((k, v) -> taxationSchemes.put(parseInt(valueOf(k).trim()), parseLong(valueOf(v).trim())));

        logger.info("Reading data for tax reliefs...");
        taxReliefProps = getProperties("tax-reliefs.properties");
    }

    /**
     * Get tax relief based on person's marital status and no. of dependents
     */
    public static long getTaxRelief(Person person) {
        final String taxReliefCategory = getTaxReliefCategory(person);
        long taxRelief = 0;
        try {
            taxRelief = parseLong(valueOf(getTaxReliefProps().get(taxReliefCategory)).trim());
            logger.info(String.format("Your profile is in '%s' category hence your tax relief is %s IDR", taxReliefCategory, taxRelief));
            return taxRelief;
        } catch (NumberFormatException e) {
            logger.debug("Failed to parse tax relief amount", e);
            throw e;
        } catch (RuntimeException e) {
            logger.debug("Failed to get tax relief", e);
            throw e;
        }
    }

    /**
     * Get tax relief category
     */
    private static String getTaxReliefCategory(Person person) {
        if (!person.isMarried()) {
            return "TK0";
        } else {
            if (0 >= person.getNoOfDependents()) {
                return "K0";
            } else if (1 == person.getNoOfDependents()) {
                return "K1";
            } else if (2 == person.getNoOfDependents()) {
                return "K2";
            } else {
                return "K3";
            }
        }
    }

    /**
     * Read properties file from classpath
     */
    private static Properties getProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream inputStream = MetadataService.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Failed to read properties file.", e);
            exit(0);
        }
        return properties;
    }
}
