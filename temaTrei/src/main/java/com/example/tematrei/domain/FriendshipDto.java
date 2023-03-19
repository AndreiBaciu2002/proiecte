package com.example.tematrei.domain;

import java.time.LocalDate;


public class FriendshipDto {
    private String firstName;
    private String lastName;
    private LocalDate friendshipDate;

    public FriendshipDto(String firstName, String lastName, LocalDate friendshipDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.friendshipDate = friendshipDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getFriendshipDate() {
        return friendshipDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFriendshipDate(LocalDate friendshipDate) {
        this.friendshipDate = friendshipDate;
    }

    @Override
    public String toString() {
        return "FriendshipDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", friendshipDate=" + friendshipDate +
                '}';
    }
}

