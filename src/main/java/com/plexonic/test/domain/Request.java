package com.plexonic.test.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Aram Kirakosyan.
 */
@Entity
@Table(name = "requests", indexes = { @Index(columnList = "requestDate")})
public class Request implements Serializable {

    private long requestId;
    private Date requestDate;
    private User user;

    @Id
    @GeneratedValue
    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    @Column
    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        if (requestId != request.requestId) return false;
        if (requestDate != null ? !requestDate.equals(request.requestDate) : request.requestDate != null) return false;
        return user != null ? user.equals(request.user) : request.user == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (requestId ^ (requestId >>> 32));
        result = 31 * result + (requestDate != null ? requestDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

}
