package com.online_pajak.code_challenge.services;

import com.online_pajak.code_challenge.models.Person;
import org.junit.Test;

import static com.online_pajak.code_challenge.services.TaxationService.calculateIncomeTax;
import static org.junit.Assert.assertEquals;

public class TaxationServiceTest {

    @Test
    public void test_calculateIncomeTax_with_zero_income() {
        final Person person = Person.builder()
                .salary(0)
                .isMarried(false)
                .noOfDependents(0)
                .build();
        assertEquals(0, calculateIncomeTax(person));
    }

    @Test
    public void test_calculateIncomeTax_for_single_person() {
        final Person person = Person.builder()
                .salary(25000000)
                .isMarried(false)
                .noOfDependents(0)
                .build();
        assertEquals(31900000, calculateIncomeTax(person));
    }

    @Test
    public void test_calculateIncomeTax_for_married_person_with_1_dependent() {
        final Person person = Person.builder()
                .salary(6500000)
                .isMarried(true)
                .noOfDependents(1)
                .build();
        assertEquals(750000, calculateIncomeTax(person));
    }

    @Test
    public void test_calculateIncomeTax_for_person_with_zero_taxable_income() {
        final Person person = Person.builder()
                .salary(4300000)
                .isMarried(false)
                .noOfDependents(2)
                .build();
        assertEquals(0, calculateIncomeTax(person));
    }

    @Test
    public void test_calculateIncomeTax_for_person_in_1st_tax_slab() {
        final Person person = Person.builder()
                .salary(4900000)
                .isMarried(true)
                .noOfDependents(0)
                .build();
        assertEquals(15000, calculateIncomeTax(person));
    }

    @Test
    public void test_calculateIncomeTax_for_person_in_2nd_tax_slab() {
        final Person person = Person.builder()
                .salary(10000000)
                .isMarried(false)
                .noOfDependents(0)
                .build();
        assertEquals(4900000, calculateIncomeTax(person));
    }

    @Test
    public void test_calculateIncomeTax_for_person_in_3rd_tax_slab() {
        final Person person = Person.builder()
                .salary(28000000)
                .isMarried(false)
                .noOfDependents(0)
                .build();
        assertEquals(40500000, calculateIncomeTax(person));
    }

    @Test
    public void test_calculateIncomeTax_for_person_in_4th_tax_slab() {
        final Person person = Person.builder()
                .salary(58000000)
                .isMarried(false)
                .noOfDependents(0)
                .build();
        assertEquals(137600000, calculateIncomeTax(person));
    }
}
