package com.plexonic.test.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * @author Aram Kirakosyan.
 */
@Entity
@Table(name = "users", indexes = { @Index(columnList = "installDate")})
public class User implements Serializable {

    private long userId;
    private Date installDate;

    @Id
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column
    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date registrationDate) {
        this.installDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return (int) (userId ^ (userId >>> 32));
    }

}
