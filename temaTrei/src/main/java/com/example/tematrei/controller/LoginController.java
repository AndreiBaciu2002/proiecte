package com.example.tematrei.controller;


import com.example.tematrei.database.DatabaseConnection;
import com.example.tematrei.service.Service;

import com.example.tematrei.utils.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    public Service service;
    public final SceneUtils sceneUtils = new SceneUtils();

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordPasswordField;


    public void setService(Service service) {
        this.service = service;
    }

    public void cancelButtonOnAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        service.logoutUser();
    }

    public boolean validateLogin() {
        boolean validated = false;
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE email = '" + emailTextField.getText() + "' AND password1 = '" + passwordPasswordField.getText() + "'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet rs = statement.executeQuery(verifyLogin);

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    validated = true;
                    service.loginUser(emailTextField.getText(), passwordPasswordField.getText());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return validated;
    }

    public void switchToSignupScene(ActionEvent event) throws IOException {
        sceneUtils.switchToSignUpScene(event, this.service);
    }

    public void loginButtonOnAction(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        if (!emailTextField.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            if (validateLogin()) {
                this.loginMessageLabel.setText("bine ati venit");
                sceneUtils.switchToUserExploreScene(event, this.service);
            } else {
                passwordPasswordField.clear();
                loginMessageLabel.setText("Invalid login.Please try again!");
            }
        } else {
            loginMessageLabel.setText("Please enter the email and password!");
        }

    }
}
