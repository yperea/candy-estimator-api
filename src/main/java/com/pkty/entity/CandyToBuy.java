package com.pkty.entity;

import lombok.Data;

public @Data class CandyToBuy {
    private int kidCount;
    private int candyPerKid;
    private int candyToBuy;

    public CandyToBuy(int kidCount, int candyPerKid) {
        this.kidCount = kidCount;
        this.candyPerKid = candyPerKid;
    }

    public int CalculateCandyToBuy() {
        this.candyToBuy = this.kidCount * this.candyPerKid;
        return candyToBuy;
    }

}
