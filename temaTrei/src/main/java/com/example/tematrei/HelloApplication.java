package com.example.tematrei;

import com.example.tematrei.domain.FriendRequest;
import com.example.tematrei.domain.Friendship;
import com.example.tematrei.domain.User;
import com.example.tematrei.domain.validators.FriendRequestValidator;
import com.example.tematrei.domain.validators.FriendshipValidator;
import com.example.tematrei.domain.validators.UserValidator;
import com.example.tematrei.repository.Repository;
import com.example.tematrei.repository.db.FriendRequestDbRepository;
import com.example.tematrei.repository.db.FriendshipsDbRepository;
import com.example.tematrei.repository.db.UsersDbRepository;
import com.example.tematrei.service.FriendRequestService;
import com.example.tematrei.service.FriendshipService;
import com.example.tematrei.service.Service;
import com.example.tematrei.service.UserService;
import com.example.tematrei.utils.SceneUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    Service service;

    @Override
    public void start(Stage stage) throws IOException {
        String username = "postgres";
        String pasword = "postgre";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        FriendshipsDbRepository friendshipsDbRepository = new FriendshipsDbRepository(url, username, pasword, new FriendshipValidator());
        Repository<Long, Friendship> friendshipRepository = new FriendshipsDbRepository(url, username, pasword, new FriendshipValidator());
        Repository<Long, User> userRepository = new UsersDbRepository(url, username, pasword, friendshipsDbRepository, new UserValidator());
        Repository<Long, FriendRequest> friendRequestRepository = new FriendRequestDbRepository(url, username, pasword, new FriendRequestValidator());
        UserService userService = new UserService(userRepository);
        FriendshipService friendshipService = new FriendshipService(userRepository, friendshipRepository);
        FriendRequestService friendRequestService = new FriendRequestService(friendRequestRepository, friendshipRepository);
        this.service = new Service(userService, friendshipService, friendRequestService);
        SceneUtils sceneUtils = new SceneUtils();
        sceneUtils.switchToLoginScene1(stage, service);
    }

    public static void main(String[] args) {
        launch();
    }
}