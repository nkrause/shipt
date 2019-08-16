package com.nkrause.shipt;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class GameDriver {

    private ImageView[] board;
    private boolean xTurn;
    private TextView turnDisplay;
    private Activity activity;
    private int numTurns;
    private String winner;

    GameDriver(Activity activity){
        this.activity = activity;
        this.board = new ImageView[9];
        this.xTurn = true;
        this.turnDisplay = this.activity.findViewById(R.id.turn_display);
        this.numTurns = 0;
        init();
    }

    /**
     * Init the game board by setting the tag and implementing a click listener
     *
     */
    private void init(){
        for(int i=0;i<9;i++){
            final int j = i;
            board[j] = this.activity.findViewById(getResourceId(i));
            board[j].setTag("");
            board[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switchTurn(j);
                }
            });
        }
    }

    /**
     * Switching turns involves changing the image resource, setting the tag,
     * changing the display text, and checking for a winner
     *
     * @param i the integer value of the space clicked (0-8)
     */
    private void switchTurn(int i){
        if(this.xTurn){
            board[i].setImageResource(R.drawable.ic_x);
            board[i].setTag("x");
            this.xTurn = false;
            this.turnDisplay.setText(R.string.o_turn);
        }
        else{
            board[i].setImageResource(R.drawable.ic_o);
            board[i].setTag("o");
            this.xTurn = true;
            this.turnDisplay.setText(R.string.x_turn);
        }
        board[i].setEnabled(false);
        numTurns++;
        //we don't need to check for a winner until at least 5 turns have been made
        if(numTurns >= 5){
            boolean isWinner = checkWinner();
            //if there is a winner, display it and disable the board
            if(isWinner){
                this.turnDisplay.setText(this.winner);
                disableBoard();
            }
            //draw
            else{
                if(numTurns == 9 ){
                    this.turnDisplay.setText(R.string.draw);
                }
            }
        }
    }

    /**
     * Checks if there is a winner
     */
    private boolean checkWinner(){
        return allSame(0,1,2) || allSame(3,4,5) || allSame(6,7,8) ||
                allSame(0,3,6) || allSame(1,4,7) || allSame(2,5,8) ||
                allSame(0,4,8) || allSame(2,4,6);
    }

    /**
     * Helper function for checking a winner by seeing if any
     * three spaces on the board are the same
     *
     * @param pos1 first space to compare
     * @param pos2 second space to compare
     * @param pos3 third space to compare
     */
    private boolean allSame(int pos1, int pos2, int pos3){

        String a = board[pos1].getTag().toString();
        String b = board[pos2].getTag().toString();
        String c= board[pos3].getTag().toString();

        if(a.equals("") || b.equals("") || c.equals("")) return false;

        boolean isWinner = a.equals(b) && b.equals(c);
        if(isWinner){
            this.winner = a.equals("x") ? "X is the winner!" : "O is the winner!";

        }
        return isWinner;
    }

    /**
     * Starts a new game
     *
     */
    public void startNewGame(){
        resetBoard();
        this.xTurn = true;
        this.numTurns = 0;
        this.turnDisplay.setText(R.string.x_turn);
    }

    /**
     * Resets board with initial values
     */
    private void resetBoard(){
        for(int i=0;i<9;i++){
            this.board[i].setTag("");
            this.board[i].setEnabled(true);
            this.board[i].setImageResource(0);
        }
    }

    /**
     * Disables all spaces on the board
     */
    private void disableBoard(){
        for(int i=0;i<9;i++){
            this.board[i].setEnabled(false);
        }
    }

    /**
     * Check resource id of certain space
     */
    private int getResourceId(int id){
        return this.activity.getApplicationContext().getResources().getIdentifier("space_" + id,"id",this.activity.getPackageName());
    }

}
