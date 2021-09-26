package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {
    public static Stage homepageStage= new Stage();

    @FXML
    TextField txtusername;

    @FXML
    PasswordField txtpassword;

    public void Login(ActionEvent actionEvent) throws Exception{
        String username=txtusername.getText();
        String password=txtpassword.getText();
        if (!username.equals("") && !password.equals("")){
            if(username.equals("waas") && password.equals("123")){
                Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
                homepageStage.setTitle("Home Page");
                homepageStage.setScene(new Scene(root, 650, 650));
                homepageStage.show();

                Stage close = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                close.close();
            }else{
                Alert a = new Alert(Alert.AlertType.NONE);
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Username or password is incorrect");
                a.showAndWait();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Username or password field is empty");
            a.showAndWait();
        }


    }
}
