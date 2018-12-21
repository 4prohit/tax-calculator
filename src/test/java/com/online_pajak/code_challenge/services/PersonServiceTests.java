package com.online_pajak.code_challenge.services;

import com.online_pajak.code_challenge.models.Person;
import org.junit.Assert;
import org.junit.Test;

import static com.online_pajak.code_challenge.services.PersonService.getPerson;
import static org.junit.Assert.assertNotNull;

public class PersonServiceTests {

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_null_arguments() {
        getPerson(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_empty_arguments() {
        getPerson(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_1_argument() {
        getPerson(new String[]{"1000000"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_2_argument() {
        getPerson(new String[]{"1000000", "M"});
    }

    @Test
    public void test_getPerson_with_3_arguments() {
        final Person person = getPerson(new String[]{"1000000", "M", "1"});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertTrue(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_null_salary() {
        getPerson(new String[]{null, "M", "1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_blank_salary() {
        getPerson(new String[]{"  ", "M", "1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_invalid_salary() {
        getPerson(new String[]{"10fa 0s00", "M", "1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_negative_salary() {
        getPerson(new String[]{"-1000000", "M", "1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_decimal_salary() {
        getPerson(new String[]{"1000000.12", "M", "1"});
    }

    @Test
    public void test_getPerson_with_whitespaces_in_salary() {
        final Person person = getPerson(new String[]{" 1000000  ", "M", "1"});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertTrue(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test
    public void test_getPerson_with_zero_salary() {
        final Person person = getPerson(new String[]{"0000", "M", "1"});
        assertNotNull(person);
        Assert.assertEquals(0, person.getSalary());
        Assert.assertTrue(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_null_marrital_status() {
        getPerson(new String[]{"1000000", null, "1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_blank_marrital_status() {
        getPerson(new String[]{"1000000", "  ", "1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_inavlid_marrital_status() {
        getPerson(new String[]{"1000000", "xyz", "1"});
    }

    @Test
    public void test_getPerson_with_whitespaces_in_marital_status() {
        final Person person = getPerson(new String[]{"1000000", " S ", "1"});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertFalse(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test
    public void test_getPerson_with_single_status_in_small() {
        final Person person = getPerson(new String[]{"1000000", "s", "1"});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertFalse(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test
    public void test_getPerson_with_single_status_in_caps() {
        final Person person = getPerson(new String[]{"1000000", "S", "1"});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertFalse(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test
    public void test_getPerson_with_married_status_in_small() {
        final Person person = getPerson(new String[]{"1000000", "m", "1"});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertTrue(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test
    public void test_getPerson_with_married_status_in_caps() {
        final Person person = getPerson(new String[]{"1000000", "M", "1"});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertTrue(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_null_no_of_dependents() {
        getPerson(new String[]{"1000000", "M", null});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_blank_no_of_dependents() {
        getPerson(new String[]{"1000000", "M", "  "});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_invalid_no_of_dependents() {
        getPerson(new String[]{"1000000", "M", "a1d "});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_negative_no_of_dependents() {
        getPerson(new String[]{"1000000", "M", "-1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getPerson_with_decimal_no_of_dependents() {
        getPerson(new String[]{"1000000", "M", "1.2"});
    }

    @Test
    public void test_getPerson_with_whitespaces_in_no_of_dependents() {
        final Person person = getPerson(new String[]{"1000000", "M", " 1 "});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertTrue(person.isMarried());
        Assert.assertEquals(1, person.getNoOfDependents());
    }

    @Test
    public void test_getPerson_with_zero_in_no_of_dependents() {
        final Person person = getPerson(new String[]{"1000000", "M", "0000"});
        assertNotNull(person);
        Assert.assertEquals(1000000, person.getSalary());
        Assert.assertTrue(person.isMarried());
        Assert.assertEquals(0, person.getNoOfDependents());
    }
}
