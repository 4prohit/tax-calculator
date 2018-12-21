package com.online_pajak.code_challenge.services;

import com.online_pajak.code_challenge.models.Person;
import org.junit.Test;

import static com.online_pajak.code_challenge.services.MetadataService.getTaxRelief;
import static org.junit.Assert.assertEquals;

public class MetadataServiceTest {

    @Test(expected = RuntimeException.class)
    public void test_getTaxRelief_for_null_person() {
        getTaxRelief(null);
    }

    @Test
    public void test_getTaxRelief_for_single() {
        final Person person = Person.builder()
                .salary(1000000)
                .isMarried(false)
                .noOfDependents(0)
                .build();
        assertEquals(54000000, getTaxRelief(person));
    }

    @Test
    public void test_getTaxRelief_for_single_with_dependents() {
        final Person person = Person.builder()
                .salary(2000000)
                .isMarried(false)
                .noOfDependents(2)
                .build();
        assertEquals(54000000, getTaxRelief(person));
    }

    @Test
    public void test_getTaxRelief_for_single_with_invalid_value_of_dependents() {
        final Person person = Person.builder()
                .salary(3000000)
                .isMarried(false)
                .noOfDependents(-2)
                .build();
        assertEquals(54000000, getTaxRelief(person));
    }

    @Test
    public void test_getTaxRelief_for_married_with_0_dependent() {
        final Person person = Person.builder()
                .salary(4000000)
                .isMarried(true)
                .noOfDependents(0)
                .build();
        assertEquals(58500000, getTaxRelief(person));
    }

    @Test
    public void test_getTaxRelief_for_married_with_invalid_value_of_dependents() {
        final Person person = Person.builder()
                .salary(5000000)
                .isMarried(true)
                .noOfDependents(-2)
                .build();
        assertEquals(58500000, getTaxRelief(person));
    }

    @Test
    public void test_getTaxRelief_for_married_with_1_dependent() {
        final Person person = Person.builder()
                .salary(6000000)
                .isMarried(true)
                .noOfDependents(1)
                .build();
        assertEquals(63000000, getTaxRelief(person));
    }

    @Test
    public void test_getTaxRelief_for_married_with_2_dependents() {
        final Person person = Person.builder()
                .salary(7000000)
                .isMarried(true)
                .noOfDependents(2)
                .build();
        assertEquals(67500000, getTaxRelief(person));
    }

    @Test
    public void test_getTaxRelief_for_married_with_3_dependents() {
        final Person person = Person.builder()
                .salary(8000000)
                .isMarried(true)
                .noOfDependents(3)
                .build();
        assertEquals(72000000, getTaxRelief(person));
    }

    @Test
    public void test_getTaxRelief_for_married_with_dependents_greater_than_3() {
        final Person person = Person.builder()
                .salary(9000000)
                .isMarried(true)
                .noOfDependents(4)
                .build();
        assertEquals(72000000, getTaxRelief(person));
    }
}
