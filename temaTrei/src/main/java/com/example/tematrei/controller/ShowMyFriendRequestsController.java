package com.example.tematrei.controller;


import com.example.tematrei.Status;
import com.example.tematrei.domain.*;
import com.example.tematrei.service.Service;
import com.example.tematrei.domain.FriendRequestDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ShowMyFriendRequestsController implements EventHandler<ActionEvent> {
    private Service service;

    ObservableList<FriendRequestDto> modelGrade = FXCollections.observableArrayList();

    @FXML
    protected TableColumn<FriendRequestDto, String> colFirstName;

    @FXML
    protected TableColumn<FriendRequestDto, String> colLastName;

    @FXML
    protected TableColumn<FriendRequestDto, String> colStatus;

    @FXML
    protected TableColumn<FriendRequestDto, String> colDate;

    @FXML
    protected TableView<FriendRequestDto> tableView;

    @FXML
    protected Button acceptButton;

    @FXML
    protected Button declineButton;

    @FXML
    protected Button cancelButton;

    public void setService(Service service1) {
        this.service = service1;
        modelGrade.setAll(getFriendRequests());
        initialize();
    }

    public void initialize() {
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.setItems(modelGrade);
    }

    @Override
    public void handle(ActionEvent event) {
        Long id = 0L;
        if (event.getSource() == cancelButton) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        } else if (event.getSource() == acceptButton) {
            FriendRequestDto friendRequestDTO = tableView.getSelectionModel().getSelectedItem();
            if (friendRequestDTO != null) {
                for (FriendRequest friendRequest : service.printMyFriendRequests()) {
                    if (friendRequest.getId2().equals(service.getCurrentUserId())) {
                        User user = service.findOne(friendRequest.getId1());
                        if (user.getLastName().equals(friendRequestDTO.getLastName()) && user.getFirstName().equals(friendRequestDTO.getFirstName())) {
                            id = friendRequest.getId();
                        }
                    }
                }
                for (User user : service.getAllUsers()) {
                    if (user.getFirstName().equals(friendRequestDTO.getFirstName()) && user.getLastName().equals(friendRequestDTO.getLastName())) {
                        service.updateFriendRequest(id, Status.APPROVED);

                    }
                }
            }

        } else if (event.getSource() == declineButton) {
            FriendRequestDto friendRequestDTO = tableView.getSelectionModel().getSelectedItem();
            if (friendRequestDTO != null) {
                for (FriendRequest friendRequest : service.printMyFriendRequests()) {
                    if (friendRequest.getId2().equals(service.getCurrentUserId())) {
                        User user = service.findOne(friendRequest.getId1());
                        if (user.getLastName().equals(friendRequestDTO.getLastName()) && user.getFirstName().equals(friendRequestDTO.getFirstName())) {
                            id = friendRequest.getId();
                        }
                    }
                }
                for (User user : service.getAllUsers()) {
                    if (user.getFirstName().equals(friendRequestDTO.getFirstName()) && user.getLastName().equals(friendRequestDTO.getLastName())) {
                        service.updateFriendRequest(id, Status.REJECTED);
                    }
                }
            }
        }
        modelGrade.setAll(getFriendRequests());
        initialize();
    }

    public ObservableList<FriendRequestDto> getFriendRequests() {
        ObservableList<FriendRequestDto> friendRequests = FXCollections.observableArrayList();
        for (FriendRequest friendRequest : service.printMyFriendRequests()) {
            if (friendRequest.getId2().equals(service.getCurrentUserId())) {
                User user = service.findOne(friendRequest.getId1());
                friendRequests.add(new FriendRequestDto(user.getFirstName(), user.getLastName(), friendRequest.getStatus(), friendRequest.getDate()));
            }
        }
        return friendRequests;
    }
}


