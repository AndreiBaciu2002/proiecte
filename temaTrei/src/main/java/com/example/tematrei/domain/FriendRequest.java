package com.example.tematrei.domain;


import java.time.LocalDate;


public class FriendRequest extends Entity<Long> {

    private Long id1;
    private Long id2;
    private String status;
    private LocalDate date;

    public FriendRequest(Long id1, Long id2, String status, LocalDate date) {
        this.id1 = id1;
        this.id2 = id2;
        this.status = status;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId1() {
        return this.id1;
    }

    public Long getId2() {
        return this.id2;
    }

    public void setId1(Long id1) {
        this.id1 = id1;
    }

    public void setId2(Long id2) {
        this.id2 = id2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id1=" + id1 +
                ", id2=" + id2 +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendRequest that)) return false;
        return getId1().equals(that.getId1()) && getId2().equals(that.getId2()) &&
                getStatus().equals(that.getStatus());
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

}

