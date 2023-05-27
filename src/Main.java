import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("View/MainPage.fxml"));
        loader.load();
        Scene scene = new Scene (loader.getRoot());
        scene.getStylesheets().add(getClass().getResource("/Style/MainPageStyle.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}