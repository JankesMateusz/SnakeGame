package sample;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class ViewNavigator {

    public static final String HOLDER = "view.fxml";
    public static final String MAIN = "MainMenu.fxml";
    public static final String VIEW_1 = "MainGame.fxml";

    private static ViewController viewController;

    public static void setViewController(ViewController viewController){
        ViewNavigator.viewController = viewController;
    }

    public static void loadView(String fxml){
        try{
            viewController.setView(
                    FXMLLoader.load(
                            ViewNavigator.class.getResource(fxml)
                    )
            );
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}