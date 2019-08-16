package com.nkrause.shipt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    GameDriver game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new GameDriver(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_new_game) {
            showNewGameDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Shows a dialog when user wants to start a new game
     *
     */
    private void showNewGameDialog(){
        new AlertDialog.Builder(this)
                .setMessage(getResources().getText(R.string.new_game_dialog_message))
                .setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                game.startNewGame();
                            }
                        })
                .setNegativeButton(R.string.no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Do do my action here
                                dialog.dismiss();
                            }
                        })
                .create()
                .show();
    }
}
