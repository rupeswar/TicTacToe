package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int cellStatus[] = new int[9], activePlayer = 1, winPositions[][] = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameFinished = false;

    public void playerTap(View view) {
        ImageView img = (ImageView)view;
        int id = Integer.parseInt(img.getTag().toString());
        TextView status = findViewById(R.id.status);
        if(gameFinished)
        {
            gameFinished = false;
            cellStatus = new int[9];
            ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
            status.setText("X's turn - Tap to play");
            activePlayer = 1;
        }
        else if(cellStatus[id] == 0)
        {
            cellStatus[id] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 1)
            {
                img.setImageResource(R.drawable.x_shape);
                status.setText("O's turn - Tap to play");
            }
            else
            {
                img.setImageResource(R.drawable.o_shape);
                status.setText("X's turn - Tap to play");
            }
            activePlayer ^= 3;
            img.animate().translationYBy(1000f).setDuration(300);
        }

        for(int[] winPosition : winPositions)
        {
            if(cellStatus[winPosition[0]] == cellStatus[winPosition[1]] && cellStatus[winPosition[1]] == cellStatus[winPosition[2]] && cellStatus[winPosition[0]] != 0)
            {
                gameFinished = true;
                if(activePlayer == 2)
                    status.setText("X wins");
                else
                    status.setText("O wins");
                break;
            }
        }

        if(!gameFinished)
        {
            boolean isTie = true;
            for (int i : cellStatus)
                if (i == 0)
                    isTie = false;

            if(isTie)
            {
                status.setText("It's a Tie");
                gameFinished = true;
            }
        }
    }
}