package com.example.tematrei.controller;

import com.example.tematrei.domain.validators.ValidationException;
import com.example.tematrei.service.Service;
import com.example.tematrei.utils.SceneUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class SignUpController implements EventHandler<ActionEvent> {
    private Service service;
    private final SceneUtils sceneUtils = new SceneUtils();

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private PasswordField wordPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button signInButton;


    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == cancelButton) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
            service.logoutUser();
        } else if (event.getSource() == signUpButton) {
            try {
                handleSave(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (event.getSource() == signInButton) {
            try {
                sceneUtils.switchToLoginScene(event, this.service);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handleSave(ActionEvent event) throws IOException {
        String firstName = firstnameTextField.getText();
        String lastName = lastnameTextField.getText();
        String email = emailTextField.getText();
        String password = wordPasswordField.getText();
        try {
            if (service.createUser(firstName, lastName, email, password)) {
                try {
                    sceneUtils.switchToUserExploreScene(event, service);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            } else {
                firstnameTextField.clear();
                lastnameTextField.clear();
                emailTextField.clear();
                wordPasswordField.clear();
            }
        } catch (ValidationException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
