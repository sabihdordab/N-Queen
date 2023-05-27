package Model;
import Model.Queen;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Board {
    private ArrayList<Queen> Queens = new ArrayList<Queen>();
    private int clash;
    public Board(int numberOfQueens){

        CreateRandomQueens(numberOfQueens);
        clashes();
    }

    public void CreateRandomQueens(int numberOfQueens){
        for (int x=0 ; x< numberOfQueens ; x++){
            Random random = new Random();
            int y = random.nextInt(numberOfQueens);
            Queen queen = new Queen(x,y);
            Queens.add(queen);
        }
    }
    public void printLayout(int win){
        for (int i=0 ; i < Queens.size() ; i++){
            int x = Queens.get(i).getX();;
            int y = Queens.get(i).getY();
            System.out.println("x = "+ x +" / y = "+ y);
        }
        if (win != 0 ){
            showBoard(Queens);
        }
    }
    public void clashes(){
        clash = 0;
        for (int i=0 ; i<Queens.size() ; i++){
            int x1 = Queens.get(i).getX();;
            int y1 = Queens.get(i).getY();

            for (int j=0 ; j<Queens.size() ; j++){
                if(i==j){continue;}
                int x2 = Queens.get(j).getX();;
                int y2 = Queens.get(j).getY();

                // Diagonal , Horizontal and vertical
                if(x1 == x2 || y1==y2 || ( ((x1-y1)==(x2-y2)) )  || ( ((x1+y1)==(x2+y2)) )){
                    clash = clash + 1;
                }
            }
        }
    }

    public ArrayList<Queen> getQueens(){
        return Queens;
    }
    public int getClash(){
        return clash;
    }

    public int get_y_queen(int x){
        int y = Queens.get(x).getY();
        return y;
    }

        public static void showBoard(ArrayList<Queen> queens){
            GridPane board = new GridPane();
            board.setPrefSize(100*queens.size(),100*queens.size());
            
            Character[][] table = new Character[queens.size()][queens.size()];
            for (int i = 0 ; i < queens.size(); i++){
                Arrays.fill(table[i], '0');
            }

            for (Queen queen : queens) {
                int x = queen.getX();
                int y = queen.getY();
                table[x][y] = '1';
            }

            for (int i = 0 ; i < queens.size() ; i++){
                for (int j = 0 ; j < queens.size() ; j++){
                    System.out.print(table[i][j]);
                    Rectangle rectangle = new Rectangle(100,100);
                    rectangle.setFill(Color.BLACK);
                    rectangle.setStroke(Color.RED);
                    Text text = new Text(String.valueOf(table[i][j]));
                    text.setFill(Color.WHITE);
                    text.setFont(Font.font(40));
                    board.add(new StackPane(rectangle,text),j,i);
                }
                System.out.println();
            }
            try {

                Parent root = board;
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.UTILITY);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();

            } catch(Exception ex) {
                ex.printStackTrace();
            }

        }
}

