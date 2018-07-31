package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setScene(
                createScene(
                        loadMainPane()
                )
        );
        primaryStage.show();
    }

    private Pane loadMainPane() throws IOException{
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = loader.load(
                getClass().getResourceAsStream(
                        ViewNavigator.HOLDER
                )
        );

        ViewController viewController = loader.getController();

        ViewNavigator.setViewController(viewController);
        ViewNavigator.loadView(ViewNavigator.MAIN);

        return mainPane;
    }

    private Scene createScene(Pane mainPane){

        return new Scene(mainPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
