
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Main extends Application{

    private int current = 90;
    private Button up = new Button("Up");
    private Button reset = new Button("Reset");
    
    private Label count = new Label(current+"");
    
    private GridPane grid = new GridPane();
    private Scene scene = new Scene(grid, 300, 200);
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    
        up.setId("up");
        reset.setId("reset");
        
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setPadding(new Insets(30.0));
        grid.add(count, 2, 1);
        
        grid.add(up, 1, 2);
        grid.add(reset, 3, 2);
        
                
        grid.setAlignment(Pos.CENTER);
        scene.getStylesheets().addAll(Main.class.getResource("style.css").toExternalForm());
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
        OptionPane.showMessageDialog("Reach", "Reach over 100!", "Start", false);
        
        up.setOnAction(e->{
            
            if(++current == 100){

                grid.getChildren().remove(up);
                
                grid.getChildren().remove(reset);
                
                grid.add(reset, 1, 2);
                grid.add(up, 3, 2);
                
                
            }
            
            if(current == 101)
                if(!OptionPane.showConfirmDialog("Whhooa", "You did it!", "Once again!", "F*ck dad shit!", false))
                {
                    OptionPane.showMessageDialog("Bye", "I hope you play again. :-)", "Bye", true);
                    primaryStage.close();
                }
                else{
                    current = 0;
            count.setText("0");
            
            grid.getChildren().remove(up);
                
            grid.getChildren().remove(reset);

            grid.add(reset, 3, 2);
            grid.add(up, 1, 2);
                }
                
            count.setText(current+"");
                
        });
        
        reset.setOnAction(e->{
            current = 0;
            count.setText("0");
            
            grid.getChildren().remove(up);
                
            grid.getChildren().remove(reset);

            grid.add(reset, 3, 2);
            grid.add(up, 1, 2);

            OptionPane.showMessageDialog("Reset", "Why so mad? :D", "continue", false);
            
        });
        
        primaryStage.setOnCloseRequest(e->{
            e.consume();
            
            OptionPane.showMessageDialog("No way!", "Finish the game first!", "Okay", false);
            
        });
        
        primaryStage.setOnHiding(e->{
            e.consume();
            
            OptionPane.showMessageDialog("No way!", "Continue!", "Okay", false);
            
        });
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
