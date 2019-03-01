package cm.atilla.devecenturion;

import cm.atilla.devecenturion.connection.HibernateUtil;
import cm.atilla.devecenturion.dao.UserDao;
import cm.atilla.devecenturion.entiy.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {

        if (HibernateUtil.setSessionFactory()) {
            launch(args);

            //Test d'une insertion d'un utilisateur en base de donnée
            User user = new User("Dave","Devalgas",true,true, LocalDate.of(2000,1,1), "Rue Gp");
            UserDao userDao = new UserDao();
            userDao.addUser(user);

            HibernateUtil.getSessionFactory().close();
        } else {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An error has occured!");
                alert.setHeaderText("Database Connection Error!");
                alert.setContentText("Please contact the developer");
                alert.showAndWait();
                Platform.exit();
            });
        }



    }
}
