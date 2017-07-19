package com.plexonic.test.domain;

import java.util.Date;

/**
 * @author Aram Kirakosyan.
 */
public class DAU {

    private Date date;
    private Integer value;

    /**
     * No args ctor for Jackson.
     */
    public DAU() {
    }

    public DAU(Date date, Integer value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DAU for date = " + date +
                ", is  " + value;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DAU dau = (DAU) o;

        if (!date.equals(dau.date)) return false;
        return value.equals(dau.value);
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

}
