package com.online_pajak.code_challenge;

import org.junit.Test;

import static com.online_pajak.code_challenge.TaxCalculator.main;

public class TaxCalculatorTest {
    @Test
    public void test_main() {
        main(new String[]{"1000000", "M", "1"});
    }
}
