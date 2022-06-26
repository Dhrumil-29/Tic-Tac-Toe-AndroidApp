package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 - X
    //1 - O
    //2 - NULL
    int currentPlayer = 0;
    int[] gameState = { 2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winStates = { {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6},};
    boolean gameActive = true;
    boolean gameDraw = false;
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        TextView textView = findViewById(R.id.status);
        textView.setText("Player X Turn - Tap to Continue");
        int tappedimg = Integer.parseInt(img.getTag().toString());

        if (gameActive) {
            if (gameState[tappedimg] != 2)
                Toast.makeText(this, "Please Tap on Empty Area", Toast.LENGTH_SHORT).show();
            else {
                gameState[tappedimg] = currentPlayer;
                img.setTranslationY(-1100f);
//                TextView textView = findViewById(R.id.status);
                if (currentPlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    currentPlayer = 1;
                    String str = "Player O Turn - Tap to Continue";
                    textView.setText(str);
                } else {
                    img.setImageResource(R.drawable.o);
                    currentPlayer = 0;
                    String str = "Player X Turn - Tap to Continue";
                    textView.setText(str);
                }

                img.animate().translationYBy(1100f).setDuration(300);
                for (int[] winpos : winStates) {
                    if (gameState[winpos[0]] == gameState[winpos[1]] && gameState[winpos[2]] == gameState[winpos[1]] && gameState[winpos[0]] == gameState[winpos[2]] && gameState[winpos[0]] != 2) {
//                        TextView textView = findViewById(R.id.status);
                        if (gameState[winpos[0]] == 0) {
                            gameActive = false;
                            String str = "Player X is Winner";
                            textView.setText(str);
                        } else {
                            gameActive = false;
                            String str = "Player O is Winner";
                            textView.setText(str);
                        }
                    }
                }
                if(gameActive)
                {
                    for(int i =0 ;i < gameState.length;i++) {
                        if (gameState[i] == 2) {
                            gameDraw = false;
                            break;
                        }
                        gameDraw = true;
                    }
                    if(gameDraw)
                    {
                        String str = "Draw Game!!!";
                        textView.setText(str);
                        gameActive = false;
                    }
                }
            }
        }else gameReset(view);

    }

    public void gameReset(View view) {
        for(int i = 0;i < gameState.length;i++)
            gameState[i] = 2;
        gameActive = true;
        currentPlayer = 0;

        ((ImageView)findViewById(R.id.img0)).setImageResource(0);
        ((ImageView)findViewById(R.id.img1)).setImageResource(0);
        ((ImageView)findViewById(R.id.img2)).setImageResource(0);
        ((ImageView)findViewById(R.id.img3)).setImageResource(0);
        ((ImageView)findViewById(R.id.img4)).setImageResource(0);
        ((ImageView)findViewById(R.id.img5)).setImageResource(0);
        ((ImageView)findViewById(R.id.img6)).setImageResource(0);
        ((ImageView)findViewById(R.id.img7)).setImageResource(0);
        ((ImageView)findViewById(R.id.img8)).setImageResource(0);
        TextView textView = findViewById(R.id.status);
        String str = "Player X Turn - Tap to Continue";
        textView.setText(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}