package Controller;

import Model.Queen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Model.Board;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class GeneticAlgorithm implements Initializable {

    @FXML
    private Button DoneBTN;

    @FXML
    private TextField inputFLD;

    @FXML
    private Label errorLBL;

    public static ArrayList<Board> population = new ArrayList<Board>();
    public static Board answer;
    public static int numberOfQueens;

    public static int win = 0 ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoneBTN.setOnAction( e -> {
            if (Pattern.matches("[4-9]|\\d{2,}+",inputFLD.getText()))
            {

                numberOfQueens = Integer.parseInt(inputFLD.getText());
                populationInit(numberOfQueens);
                printPopulation();
                sortPopulation();
                geneticAlgorithm();
            }
            else {
                errorLBL.setText("Invalid Input");
            }
        });

    }

    public static void populationInit(int numberOfQueens){
        for (int i=0 ; i < numberOfQueens ; i++){
            Board board = new Board(numberOfQueens);
            population.add(board);
        }
    }

    public static void geneticAlgorithm(){
        while (true){
            sortPopulation();
            crossover();
            winCheck();
            if (answer!=null){
                System.out.println("-------- win ----------");
                answer.printLayout(1);
                break;
            }
            mutation();
            winCheck();
            if (answer!=null){
                System.out.println("-------- win ----------");
                answer.printLayout(1);
                break;
            }
        }
    }
    public static void sortPopulation(){
        for (int i = 0; i < population.size(); i++) {
            for (int j = 0; j < population.size() - 1; j++) {
                if (population.get(j).getClash()> population.get(j + 1).getClash()) {
                    Collections.swap(population, j, j + 1);
                }
            }
        }
        // print clash of board after sort
        /* for (int i=0 ; i< population.size() ; i++){
            System.out.println("Clash ="+population.get(i).getClash());}
        */
    }
    public static void printPopulation(){
        for (int k=0 ; k<population.size() ; k++){
            System.out.println("-------- board " + k + "----------");
            population.get(k).printLayout(win);
            System.out.println(population.get(k).getClash());
        }
    }
    public static void crossover(){

        Board parent_1 = population.get(0);
        Board parent_2 = population.get(1);

        int y10 = parent_1.get_y_queen(0);
        int y11 = parent_1.get_y_queen(1);
        int y2n_2 = parent_2.get_y_queen(numberOfQueens-2);
        int y2n_1 = parent_2.get_y_queen(numberOfQueens-1);

        parent_1.getQueens().set(0, new Queen(0,y2n_2));
        parent_1.getQueens().set(1,new Queen(0,y2n_1));

        parent_2.getQueens().set(numberOfQueens-2,new Queen(numberOfQueens-2,y10));
        parent_2.getQueens().set(numberOfQueens-1,new Queen(numberOfQueens-1,y11));

    }

    public static void winCheck(){
        for (Board board : population) {
            board.clashes();
        }

        for (Board board : population) {
            if (board.getClash() == 0) {
                answer = board;
                break;
            }
        }
    }

    public static void mutation() {
        Board child1 = population.get(0);
        Board child2 = population.get(1);


        Random random = new Random();
        int new_y = random.nextInt(numberOfQueens);
        int x = random.nextInt(numberOfQueens);

        child1.getQueens().set(x,new Queen(x,new_y));

        new_y = random.nextInt(numberOfQueens);
        x = random.nextInt(numberOfQueens);

        child2.getQueens().set(x,new Queen(x,new_y));
    }

}



