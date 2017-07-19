package com.plexonic.test.domain;

/**
 * @author Aram Kirakosyan
 */
public enum  RetentionType {

    DAY1(1),
    DAY7 (7),
    DAY40 (40);

    private  int value;

    RetentionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
