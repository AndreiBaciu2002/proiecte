package com.example.tematrei.utils;


import com.example.tematrei.Main;
import com.example.tematrei.controller.*;


import com.example.tematrei.domain.User;
import com.example.tematrei.service.Service;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class SceneUtils {
    protected Parent root;
    private Stage stage;
    private Service service;

    public SceneUtils() {

    }


    public void switchToLoginScene1(Stage stage, Service service) throws IOException {
        this.service = service;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/login.fxml"));
        AnchorPane borderPane = fxmlLoader.load();
        Scene scene = new Scene(borderPane, 520, 400);
        stage.setScene(scene);
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);
        stage.show();
    }

    public void switchToLoginScene(ActionEvent event, Service service) throws IOException {
        this.service = service;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/login.fxml"));
        Scene scene;
        this.root = fxmlLoader.getRoot();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load(), 520, 400);
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(this.service);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignUpScene(ActionEvent event, Service service) throws IOException {
        this.service = service;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/signUp.fxml"));
        Scene scene;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.root = fxmlLoader.getRoot();
        scene = new Scene(fxmlLoader.load(), 520, 400);
        SignUpController signUpController = fxmlLoader.getController();
        signUpController.setService(this.service);
        stage.setScene(scene);
        stage.show();
    }

    public void showFriendRequestsDialog(Service service) throws IOException {
        this.service = service;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/showFriendRequests.fxml"));
        AnchorPane root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Notifications");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);

        ShowFriendRequestsController showFriendRequestsController = loader.getController();
        showFriendRequestsController.setService(this.service);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void showMyFriendRequestsDialog(Service service) throws IOException {
        this.service = service;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/showMyFriendRequests.fxml"));
        AnchorPane root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Notifications");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);

        ShowMyFriendRequestsController showMyFriendRequestsController = loader.getController();
        showMyFriendRequestsController.setService(this.service);
        dialogStage.show();
    }

    public void switchToUserExploreScene(ActionEvent event, Service service) throws IOException {
        this.service = service;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/UserAccountExplore.fxml"));
        Scene scene;
        root = fxmlLoader.getRoot();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);

        User user = service.getCurrentUser();
        user.setId(service.getCurrentUserId());
        UserAccountExploreController userAccountController = fxmlLoader.getController();
        userAccountController.setService(this.service);

        userAccountController.setLabelText(user);
        stage.show();
    }

    public void showFriendsDialog(Service service) throws IOException {
        this.service = service;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/friends.fxml"));
        AnchorPane root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Notifications");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);

        FriendsController friendsController = loader.getController();
        friendsController.setService(this.service);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void showYourFriendsDialog(Service service) throws IOException {
        this.service = service;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/yourFriends.fxml"));
        AnchorPane root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Notifications");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);

        YourFriendsController friendsController = loader.getController();
        friendsController.setService(this.service);
        dialogStage.setScene(scene);
        dialogStage.show();
    }
}

