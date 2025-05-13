package lk.ijse.memoclips.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginPageController {
    public AnchorPane ancLoginPage;

    public void btnGoDashBoardPageOnAction(ActionEvent actionEvent) throws IOException {
        ancLoginPage.getChildren().clear();
        Parent load= FXMLLoader.load(getClass().getResource("/view/DashBoardPage.fxml"));
        ancLoginPage.getChildren().add(load);
    }
}
