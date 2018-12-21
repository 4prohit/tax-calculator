package com.online_pajak.code_challenge.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {

    private long salary;

    private boolean isMarried;

    private int noOfDependents;
}
