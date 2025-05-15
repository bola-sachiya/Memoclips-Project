package lk.ijse.memoclips.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {
    public AnchorPane ancDashboard;

    public void btnAdminOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Admin.fxml");
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Customer.fxml");
    }

    public void btnAlbumDetailsOnAction(ActionEvent actionEvent) {
        navigateTo("/view/AlbumDetails.fxml");
    }

    public void btnAssistantOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Assistant.fxml");
    }

    public void btnBookingsOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Bookings.fxml");
    }

    public void btnInvoiceOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Invoice.fxml");
    }

    public void btnItemOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Item.fxml");
    }

    public void btnpaymentOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Payment.fxml");
    }

    public void btnPhotoSessionOnAction(ActionEvent actionEvent) {
        navigateTo("/view/PhotoSession.fxml");
    }

    public void btnPhotoStorageOnAction(ActionEvent actionEvent) {
        navigateTo("/view/PhotoStorage.fxml");
    }

    public void btnphotoAlbumOnAction(ActionEvent actionEvent) {
        navigateTo("/view/PhotoAlbum.fxml");
    }

    public void btnPhotographerOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Photographer.fxml");
    }

    public void btnSupplierOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Supplier.fxml");
    }

    public void btnVideoSessionOnAction(ActionEvent actionEvent) {
        navigateTo("/view/VideoSession.fxml");
    }

    public void btnVideographerOnAction(ActionEvent actionEvent) {
        navigateTo("/view/Videographer.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resource) {

    }
    private void navigateTo(String path) {
        try {
            ancDashboard.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancDashboard.widthProperty());
            anchorPane.prefHeightProperty().bind(ancDashboard.heightProperty());

            ancDashboard.getChildren().add(anchorPane);

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            e.printStackTrace();
        }
    }
}
