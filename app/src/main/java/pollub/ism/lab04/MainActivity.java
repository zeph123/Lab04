package pollub.ism.lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private Button[][] boardButtons = new Button[3][3];
    private Button playAgainButton = null;
    private int turnPlayer = 1, roundCounter = 0, whoIsWinner;
    private TextView currentPlayer = null, gameResult = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentPlayer = (TextView) findViewById(R.id.currentPlayer);
        currentPlayer.setText("Aktualny gracz: X (krzyżyk)");
        gameResult = (TextView) findViewById(R.id.gameResult);

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                String buttonID = "button_" + i + "_" + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                boardButtons[i][j] = findViewById(resourceID);
            }
        }

        playAgainButton = (Button) findViewById(R.id.buttonPlayAgain);

    }

    public void kliknieciePrzycisku(View view){

        if ( !((Button) view).getText().toString().equals("") ){
            return;
        }

        if ( turnPlayer == 1 ){
            ((Button) view).setText("X"); // na pole przycisku ustawiamy X ( krzyżyk )
            turnPlayer = 0; // zmieniamy kolej gracza na O ( kółko ) - 0
            currentPlayer.setText("Aktualny gracz: O ( kółko )");
        } else {
            ((Button) view).setText("O"); // na pole przycisku ustawiamy O ( kółko )
            turnPlayer = 1; // zmieniamy kolej gracza na X ( krzyżyk ) - 1
            currentPlayer.setText("Aktualny gracz: X (krzyżyk)");
        }

        roundCounter++; // inkrementacja licznika rund

        if( checkConditions() != 0 ){

            if( checkConditions() == 1 ){
                playerXWon(); // wygrywa krzyżyk
                enableBoard(false);
            }
            if( checkConditions() == 2 ){
                playerOWon(); // wygrywa kółko
                enableBoard(false);
            }

        } else if( roundCounter == 9 ){
            draw(); // remis
            enableBoard(false);
        }
    }

    public int checkConditions(){

        String[][] board = new String[3][3];
        // przypisanie wartosci z przyciskow do planszy
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                board[i][j] = boardButtons[i][j].getText().toString();
            }
        }
        // sprawdzenie po wierszach
        for(int i=0; i<3; i++){
            if( board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && board[i][0].equals("X") ){
                whoIsWinner = 1; // przypisujemy wygraną X ( krzyżyk ) - 1
            }
            if( board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && board[i][0].equals("O") ){
                whoIsWinner = 2; // przypisujemy wygraną O ( kółko ) - 2
            }
        }
        // sprawdzenie po kolumnach
        for(int j=0; j<3; j++){
            if( board[0][j].equals(board[1][j]) && board[0][j].equals(board[2][j]) && board[0][j].equals("X") ){
                whoIsWinner = 1; // przypisujemy wygraną X ( krzyżyk ) - 1
            }
            if( board[0][j].equals(board[1][j]) && board[0][j].equals(board[2][j]) && board[0][j].equals("O") ){
                whoIsWinner = 2; // przypisujemy wygraną O ( kółko ) - 2
            }
        }
        // sprawdzenie po skosie pierwszym
        if( board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && board[0][0].equals("X") ){
            whoIsWinner = 1; // przypisujemy wygraną X ( krzyżyk ) - 1
        }
        if( board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && board[0][0].equals("O") ){
            whoIsWinner = 2; // przypisujemy wygraną O ( kółko ) - 2
        }
        // sprawdzenie po skosie drugim
        if( board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && board[0][2].equals("X") ){
            whoIsWinner = 1; // przypisujemy wygraną X ( krzyżyk ) - 1
        }
        if( board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && board[0][2].equals("O") ){
            whoIsWinner = 2; // przypisujemy wygraną O ( kółko ) - 2
        }

        return whoIsWinner; // zwracamy wartosc wygranej

    }

    public void playerXWon(){

        gameResult.setText("Wygrał gracz X (krzyżyk)");

    }

    public void playerOWon(){

        gameResult.setText("Wygrał gracz O (kółko)");

    }

    public void draw(){

        gameResult.setText("Remis");

    }

    public void playAgain(View view){

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                boardButtons[i][j].setText("");
            }
        }
        roundCounter = 0;
        turnPlayer = 1;

        currentPlayer.setText("Aktualny gracz: X (krzyżyk)");
        gameResult.setText("Wynik:");

        whoIsWinner = 0;

        enableBoard(true);
    }

    public void enableBoard(boolean state){
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                String buttonID = "button_" + i + "_" + j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                boardButtons[i][j] = findViewById(resourceID);
                boardButtons[i][j].setEnabled(state);
            }
        }
    }

}