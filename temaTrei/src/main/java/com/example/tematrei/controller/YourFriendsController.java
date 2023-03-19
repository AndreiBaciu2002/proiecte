package com.example.tematrei.controller;

import com.example.tematrei.domain.*;
import com.example.tematrei.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.example.tematrei.utils.SceneUtils;

import java.io.IOException;

public class YourFriendsController implements EventHandler<ActionEvent> {
    private Service service;

    private final SceneUtils sceneUtils = new SceneUtils();

    ObservableList<FriendshipDto> modelGrade = FXCollections.observableArrayList();

    @FXML
    private TableColumn<FriendshipDto, String> colFirstName;

    @FXML
    private TableColumn<FriendshipDto, String> colLastName;

    @FXML
    private TableColumn<FriendshipDto, String> colDate;

    @FXML
    private TableView<FriendshipDto> tableView;

    @FXML
    private Button removeButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button logoutButton;

    public void setService(Service service1) {
        this.service = service1;
        modelGrade.setAll(getFriendships());
        initialize();
    }

    public ObservableList<FriendshipDto> getFriendships() {
        ObservableList<FriendshipDto> friendships = FXCollections.observableArrayList();
        for (Friendship friendship : service.getUserFriendships()) {
            if (friendship.getId2().equals(service.getCurrentUserId())) {
                User user = service.findOne(friendship.getId1());
                friendships.add(new FriendshipDto(user.getFirstName(), user.getLastName(), friendship.getFriendshipDate()));
            }
        }
        return friendships;
    }

    public void initialize() {
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("friendshipDate"));
        tableView.setItems(modelGrade);
    }

    @Override
    public void handle(ActionEvent event) {
        Long id = 0L;
        if (event.getSource() == cancelButton) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        } else if (event.getSource() == logoutButton) {
            try {
                sceneUtils.switchToLoginScene(event, this.service);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == removeButton) {
            FriendshipDto friendshipDto = tableView.getSelectionModel().getSelectedItem();
            if (friendshipDto != null) {
                for (Friendship friendship : service.getUserFriendships()) {
                    if (friendship.getId2().equals(service.getCurrentUserId())) {
                        User user = service.findOne(friendship.getId1());
                        if (user.getLastName().equals(friendshipDto.getLastName()) && user.getFirstName().equals(friendshipDto.getFirstName())) {
                            id = friendship.getId();
                        }
                    }
                }
                for (User user : service.getAllUsers()) {
                    if (user.getFirstName().equals(friendshipDto.getFirstName()) && user.getLastName().equals(friendshipDto.getLastName())) {
                        service.deleteFriendship(id);
                    }
                }
            }
        }
        modelGrade.setAll(getFriendships());
        initialize();
    }
}
