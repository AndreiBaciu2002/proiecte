package com.example.tematrei.service;

import com.example.tematrei.Status;
import com.example.tematrei.domain.FriendRequest;
import com.example.tematrei.domain.Friendship;
import com.example.tematrei.domain.User;


import java.util.*;

public class Service {
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final FriendRequestService friendRequestService;

    private Long currentUserId = 0L;
    private User currentUser;

    public Service(UserService userService, FriendshipService friendshipService, FriendRequestService friendRequestService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.friendRequestService = friendRequestService;
    }

    public void loginUser(String email, String password) {
        for (User user : userService.findAll()) {
            if (user.getEmail().equals(email) && user.getPasswordHash().equals(password)) {
                user.setId(user.getId());
                currentUserId = user.getId();
                currentUser = userService.findOne(currentUserId);
            }
        }
    }

    public boolean createUser(String firstName, String lastName, String email, String password) {
        boolean validatedSignUp = false;
        try {
            User newUser = new User(firstName, lastName, email, password);
            newUser.setId(newUser.getId());
            userService.saveUser(newUser);
            validatedSignUp = true;
            currentUserId = newUser.getId();
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        loginUser(email, password);
        return validatedSignUp;
    }

    public void deleteAccount() {
        for (User user : userService.findAll()) {
            if (user.equals(userService.findOne(currentUserId))) {
                currentUserId = user.getId();
                if (currentUserId == null) {
                    return;
                }

                for (Friendship friendship : friendshipService.getUserFriendships(currentUserId)) {
                    friendshipService.deleteFriend(friendship.getId());
                }
                for (FriendRequest friendRequest : printMyFriendRequests()) {
                    friendRequestService.deleteFriendRequests(friendRequest.getId());
                }

                userService.deleteUser(currentUserId);
                currentUserId = null;
            }
        }
    }

    public void updateUser(String newPassword) {
        if (!newPassword.equals("")) {
            userService.updateUser(currentUserId, currentUser.getFirstName(), currentUser.getLastName(), currentUser.getEmail(), newPassword);
        }
    }

    public void logoutUser() {
        currentUserId = null;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public User getCurrentUser() {
        return userService.findOne(currentUserId);
    }

    public User findOne(Long x) {
        return this.userService.findOne(x);
    }

    public Iterable<User> getAllUsers() {
        Iterable<User> allUsers = userService.findAll();
        List<User> getAll = new ArrayList<>();
        for (User user : allUsers) {
            if (!user.equals(currentUser)) {
                getAll.add(user);
            }
        }
        return getAll;
    }

    //CRUD FRIENDSHIP
    public void removeFriend(Long friendshipId) {
        if (friendshipId == null) {
            throw new Error("Id not found");
        }
        friendshipService.deleteFriend(friendshipId);
    }

    public Iterable<Friendship> getUserFriendships() {
        List<Friendship> friendships = new ArrayList<>();
        for (Friendship friendship : friendshipService.findAll()) {
            if (friendship.getId1().equals(currentUserId) || friendship.getId2().equals(currentUserId)) {
                friendships.add(friendship);
            }
        }
        return friendships;
    }

    public void updateFriendRequest(Long id, Status status) {
        friendRequestService.updateFriendRequest(id, status);
    }

    public Iterable<Friendship> getAllFriendships() {
        return friendshipService.findAll();

    }

    public int getNrOfFriends(Long aLong) {
        return friendshipService.getNrOfFriends(aLong);
    }

    public boolean ifFriend(Long x) {
        return friendshipService.ifFriend(currentUserId, x);
    }


    public void sendFriendRequest(Long id) {
        try {
            friendRequestService.sendFriendRequest(currentUserId, id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public Iterable<FriendRequest> printMyFriendRequests() {
        Iterable<FriendRequest> friendRequests = this.friendRequestService.printAllFriendRequests();
        List<FriendRequest> myFriendRequests = new ArrayList<>();
        for (FriendRequest friendRequest : friendRequests) {
            if (friendRequest.getId1().equals(currentUserId) || friendRequest.getId2().equals(currentUserId)) {
                myFriendRequests.add(friendRequest);
            }
        }
        return myFriendRequests;
    }

    public void deleteFriendRequests(Long id) {
        this.friendRequestService.deleteFriendRequests(id);
    }

    public void deleteFriendship(Long id) {
        this.friendshipService.deleteFriend(id);
    }
}
