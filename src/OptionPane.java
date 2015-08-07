

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class OptionPane {
    
    private static Stage primaryStage;
    private static VBox vbContent;
    private static HBox hbContent;
    private static Label message;
    private static Scene scene;
    private static double width = 300;
    private static double height = 125;
    private static String input;
    
    private static boolean returnValue;
    
    /**
     * 
     * @param title Needed to display title of the Stage
     * @param information Needed to display information of the Stage
     * 
     */
    public static void showMessageDialog(String title, String information, String btnMsg, boolean closable){
        
        prepareStds(information, title, closable);
        
        Button confirm = new Button(btnMsg);
        confirm.setOnAction(e-> primaryStage.close());
        
        
        hbContent = new HBox(20);
        hbContent.setAlignment(Pos.CENTER);
        hbContent.getChildren().add(confirm);
        
        vbContent.getChildren().add(hbContent);
        
        scene = new Scene(vbContent, width, height);
        
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        
        
        
    }
    
    /**
     * 
     * @param title Needed to display title of the Stage
     * @param information Needed to display information of the Stage
     * @param btnMsg1 Text of the confirm-button
     * @param btnMsg2 Text of the decline-button
     * @return returns true if confirm <br>
     * 
     * @return returns false if decline <br>
     * 
     * @return returns false if closing Stage <br>
     */
    public static boolean showConfirmDialog(String title, String information, String  btnMsg1, String btnMsg2, boolean closable){
        
        prepareStds(information, title, closable);
        
        Button confirm = new Button(btnMsg1);
        Button decline = new Button(btnMsg2);
        
        hbContent = new HBox(20);
        hbContent.setAlignment(Pos.CENTER);
        hbContent.getChildren().addAll(confirm, decline);
        
        confirm.setOnAction(e->{
            returnValue = true;
            primaryStage.close();
        });
        
        decline.setOnAction(e->{
            returnValue = false;
            primaryStage.close();
        });
        
        vbContent.getChildren().add(hbContent);
        
        scene = new Scene(vbContent, width, height);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
        
        return returnValue;
    }
    
    public static String showInputDialog(String title, String information, String btnMsg, String eqwNlMsg, boolean closable){
        
        prepareStds(information, title, closable);
        
        
        TextField inputField = new TextField();
        
        Button confirm = new Button(btnMsg);
        confirm.setOnAction(e->{
            if(inputField.getText().equals("")){
                message.setTextFill(Color.RED);
                message.setText(eqwNlMsg);
            }
            else if(!inputField.getText().equals("")){
                input = inputField.getText();
                primaryStage.close();
            } 
                
        });
        
        
        vbContent.getChildren().addAll(inputField, confirm);
        
        scene = new Scene(vbContent, width, height);
        
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
                 
       
        return input;
    }
    
    private static void prepareStds(String information, String title, boolean closable){
        
        
        
        primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle(title);
        
        vbContent = new VBox(20.0);
        vbContent.setAlignment(Pos.CENTER);
        message = new Label(information);
        vbContent.getChildren().add(message);
        
        //Sorgt dafür, dass der Nutzer das Fenster nicht schließen kann
        if(!closable)
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            public void handle(WindowEvent e){
                e.consume();
            }
        });
        
    }
    
}
