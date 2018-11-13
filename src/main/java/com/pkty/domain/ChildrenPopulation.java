package com.pkty.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class ChildrenPopulation {
    @NonNull
    private String address;
    @NonNull
    private String country;
    @NonNull
    private int count;
}
