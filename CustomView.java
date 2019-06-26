package com.example.assignment3.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class CustomView extends View {

    public char piece;
    int endgame = 0;
    int newpiece = 0;
    private static final int SQUARE_SIZE = 107;
    private static final int SQUARE_DOWN = 109;
    //THREADING
    //private static final int THREAD_SLEEP = 100;
    private Rect mRectSquare;
    private Paint mPaintSquare;
    int left = 535; //initialize left side of square to start at 535
    int left2 = left;
    int top = 0;
    int top2 = top;
    int bottom;
    int nextrand = 1; //starting piece will always be cube
    int currentrand;
    int score = 0;
    int form = 1; // case for the 4 forms of shape, assume rotation is only in one direction
    int rotated = 0; // allow only 1 rotation per execution

    int board [][] = new int [10][13]; //initialization for the array (backend)

    /*public void Update(String s){
        TextView tv = (TextView)findViewById(R.id.textView1);
        tv.setText(s);
    }*/

    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        mRectSquare = new Rect();
        mPaintSquare = new Paint(Paint.ANTI_ALIAS_FLAG);

        for(int i = 0; i < 10; i++){ //initialize board to empty at start
            for (int j = 0; j < 13; j++){
                board[i][j] = 0;
            }
        }
    }

    public void Rotate() { //function to rotate piece
        //left = left - SQUARE_SIZE;
        //mRectSquare.left = mRectSquare.left - SQUARE_SIZE;
        //mRectSquare.right = mRectSquare.right - SQUARE_SIZE;
        switch (piece){
            case 'O':
                //trying mRectSquare.left instead of left
                /*
                board[(mRectSquare.left/107)+1][top/107] = 0;
                board[(mRectSquare.left/107)+1][(top/107)+1] = 0;
                board[(mRectSquare.left/107)+3][top/107] = 1;
                board[(mRectSquare.left/107)+3][(top/107)+1] = 1;

                if ((board[left/107][top/107] == 1) && (board[(left/107)+1][top/107] == 1) && (board[(left/107)][(top/107)+1] == 1) && (board[(left/107)+1][(top/107)+1] == 1)){
                board[(left / 107) + 1][(top / 107) + 1] = 0;
                board[(left / 107) + 2][(top / 107)] = 1;
                board[(left / 107)][top / 107] = 0;
                board[(left / 107) + 1][(top / 107) + 1] = 1;
                board[left / 107][(top / 107) + 1] = 0;
                board[(left / 107) + 2][(top / 107) + 1] = 1;
            }
                else if ((board[(left/107)+2][(top/107)] == 1) && (board[(left/107)+3][top/107] == 1) && (board[(left/107)+2][(top/107)+1] == 1) && (board[(left/107)+3][(top/107)+1] == 1)) { // do else if's for other cases
                    board[(left/107)+3][(top/107)] = 0;
                    board[(left/107)+2][(top/107)-1] = 1;
                    board[(left/107)+2][(top/107)+1] = 0;
                    board[(left/107)+3][(top/107)] = 1;
                    board[(left/107)+3][(top/107)+1] = 0;
                    board[(left/107)+3][(top/107)-1] = 1;
                }
                else if ((board[(left/107)+3][top/107] == 1) && (board[(left/107)+3][(top/107)-1] == 1) && (board[(left/107)+4][(top/107)-1] == 1) && (board[(left/107)+4][(top/107)] == 1)) {
                    board[(left/107)+3][(top/107)-1] = 0;
                    board[(left/107)+2][(top/107)] = 1;
                    board[(left/107)+4][(top/107)-1] = 0;
                    board[(left/107)+2][(top/107)-1] = 1;
                    board[(left/107)+4][(top/107)] = 0;
                    board[(left/107)+3][(top/107)-1] = 1;
                }*/
                //else {
                //    board[(left/107)][top/107] = 1;

                // do nothing
                break;
            case 'I': //base square is 3rd, no unit collision check implemented for rotation
                if((form == 1) && (rotated == 0)) { //base 3rd square --> top square
                    if ((board[(left / 107)][(top / 107) + 1] == 0) && (board[(left / 107)][(top / 107) + 2] == 0) && (board[(left / 107)][(top / 107) + 3] == 0)) {
                        mRectSquare.left = mRectSquare.left + (2 * SQUARE_SIZE);
                        mRectSquare.right = mRectSquare.right - (SQUARE_SIZE);
                        mRectSquare.bottom = mRectSquare.bottom + (3 * SQUARE_SIZE);
                        board[(left / 107)][(top / 107) + 1] = 2;
                        board[(left / 107)][(top / 107) + 2] = 2;
                        board[(left / 107)][(top / 107) + 3] = 2;
                        board[(left / 107) - 2][(top / 107)] = 0;
                        board[(left / 107) - 1][(top / 107)] = 0;
                        board[(left / 107) + 1][(top / 107)] = 0;
                        form = 2;
                        rotated++;
                    }
                }
                else if((form == 2) && (rotated == 0)) { //base top square --> 3rd square
                    if ((board[(left / 107) - 2][(top / 107)] == 0) && (board[(left / 107) - 1][(top / 107)] == 0) && (board[(left / 107) + 1][(top / 107)] == 0)) {
                        mRectSquare.left = mRectSquare.left - (2 * SQUARE_SIZE);
                        mRectSquare.right = mRectSquare.right + (SQUARE_SIZE);
                        mRectSquare.bottom = mRectSquare.bottom - (3 * SQUARE_SIZE);
                        board[(left / 107)][(top / 107) + 1] = 0;
                        board[(left / 107)][(top / 107) + 2] = 0;
                        board[(left / 107)][(top / 107) + 3] = 0;
                        board[(left / 107) - 2][(top / 107)] = 2;
                        board[(left / 107) - 1][(top / 107)] = 2;
                        board[(left / 107) + 1][(top / 107)] = 2;
                        form = 1;
                        rotated++;
                    }
                }
                rotated = 0;
                break;
            case 'T':  //base square is top mid
                if((form == 1) && (rotated == 0)){ //rotate right (Top --> Right)
                    if(board[(left/107)][(top/107)+2] == 0){
                        mRectSquare.left = mRectSquare.left + SQUARE_SIZE;
                        mRectSquare.bottom = mRectSquare.bottom + SQUARE_SIZE;
                        board[(left/107)][(top/107)+2] = 3;
                        board[(left/107)-1][(top/107)+1] = 0;
                        form = 2;
                        rotated++;
                    }
                }
                else if((form == 2) && (rotated == 0)) { //rotate right (Right --> Bottom)
                    if ((board[(left / 107)-1][(top / 107)+1] == 0)){
                        mRectSquare.left = mRectSquare.left - (SQUARE_SIZE);
                        mRectSquare.top = mRectSquare.top + (SQUARE_SIZE);
                        board[(left / 107)-1][(top / 107) + 1] = 3;
                        board[(left / 107)][(top / 107)] = 0;
                        form = 3;
                        rotated++;
                    }
                }
                else if((form == 3) && (rotated == 0)) { //rotate right (Bottom --> Left)
                    if ((board[(left / 107)][(top / 107)] == 0)){
                        mRectSquare.right = mRectSquare.right - (SQUARE_SIZE);
                        mRectSquare.top = mRectSquare.top - (SQUARE_SIZE);
                        board[(left / 107)][(top / 107)] = 3;
                        board[(left / 107) + 1][(top / 107) + 1] = 0;
                        form = 4;
                        rotated++;
                    }
                }
                else if((form == 4) && (rotated == 0)) { //rotate right (Left --> Top)
                    if ((board[(left / 107)+1][(top / 107)+1] == 0)){
                        mRectSquare.right = mRectSquare.right + (SQUARE_SIZE);
                        mRectSquare.bottom = mRectSquare.bottom - (SQUARE_SIZE);
                        board[(left / 107)+1][(top / 107) + 1] = 3;
                        board[(left / 107)][(top / 107) + 2] = 0;
                        form = 1;
                        rotated++;
                    }
                }
                rotated = 0;
                break;
            case 'S': //base square is top left
                System.out.println("Rotate for S is not supported!");
                break;
            case 'Z': //base square is top right
                System.out.println("Rotate for Z is not supported!");
                break;
            case 'J': //base square is top left
                System.out.println("Rotate for J is not supported!");
                break;
            case 'L': //base square is top right
                System.out.println("Rotate for L is not supported!");
                break;
        }
        postInvalidate();
    }
    public void moveLeft() { //button function to move left
        if(mRectSquare.left < 1){ // mRect is the shape's left, left = the base square
            System.out.println("Left bound reached!");
        }
        else {
            left = left - SQUARE_SIZE; //frontend - change object dimensions
            mRectSquare.left = mRectSquare.left - SQUARE_SIZE;
            mRectSquare.right = mRectSquare.right - SQUARE_SIZE;

            switch (piece){  //backend - change board array dimensions
                case 'O': //base square top right
                    if((board[(left/107)-1][top/107] == 0) && (board[(left/107)-1][(top/107)+1] == 0)) { // check unit collision
                        board[(left / 107) - 1][top / 107] = 1;
                        board[(left / 107) - 1][(top / 107) + 1] = 1;
                        board[(left / 107) + 1][top / 107] = 0;
                        board[(left / 107) + 1][(top / 107) + 1] = 0;
                    }
                    break;
                case 'I': //base square is 3rd
                    if(form == 1) {
                        if (board[(left / 107) - 2][top / 107] == 0) { // check unit collision
                            board[(left / 107) - 2][top / 107] = 2;
                            board[(left / 107) + 2][top / 107] = 0;
                        }
                    }
                    else if(form == 2){
                        if((board[(left/107)][(top/107)] == 0) && ((board[(left/107)][(top/107)+1] == 0)) && (board[(left/107)][(top/107)+2] == 0) && (board[(left/107)][(top/107)+3] == 0)){
                            board[(left/107)][(top/107)] = 2;
                            board[(left/107)][(top/107)+1] = 2;
                            board[(left/107)][(top/107)+2] = 2;
                            board[(left/107)][(top/107)+3] = 2;
                            board[(left/107)+1][(top/107)] = 0;
                            board[(left/107)+1][(top/107)+1] = 0;
                            board[(left/107)+1][(top/107)+2] = 0;
                            board[(left/107)+1][(top/107)+3] = 0;
                        }
                    }
                    break;
                case 'T':  //base square is top mid
                    if(form == 1) {
                        if ((board[left / 107][top / 107] == 0) && (board[(left / 107) - 1][(top / 107) + 1] == 0)) { // check unit collision
                            board[(left / 107) + 1][top / 107] = 0;
                            board[(left / 107) + 2][(top / 107) + 1] = 0;
                            board[left / 107][top / 107] = 3;
                            board[(left / 107) - 1][(top / 107) + 1] = 3;
                        }
                    }
                    else if(form == 2){
                        if ((board[left / 107][top / 107] == 0) && (board[(left / 107)][(top / 107) + 1] == 0) && (board[(left/107)][(top/107)+2] == 0)){ // check unit collision
                            board[(left / 107) +1][(top / 107)] = 0;
                            board[(left / 107) +2][(top / 107)+1] = 0;
                            board[(left / 107) +1][(top / 107)+2] = 0;
                            board[left / 107][top / 107] = 3;
                            board[(left / 107)][(top / 107) + 1] = 3;
                            board[(left / 107)][(top / 107) + 2] = 3;
                        }
                    }
                    else if(form == 3){
                        if ((board[(left / 107)-1][(top / 107)+1] == 0) && (board[(left / 107)][(top / 107) + 2] == 0)) { // check unit collision;
                            board[(left / 107) + 2][(top / 107) + 1] = 0;
                            board[(left / 107) + 1][(top / 107) + 2] = 0;
                            board[(left / 107)-1][(top / 107)+1] = 3;
                            board[(left / 107)][(top / 107) + 2] = 3;
                        }
                    }
                    else if(form == 4){
                        if ((board[left / 107][top / 107] == 0) && (board[(left / 107)-1][(top / 107) + 1] == 0) && (board[(left/107)][(top/107)+2] == 0)){ // check unit collision
                            board[(left / 107) +1][(top / 107)] = 0;
                            board[(left / 107) +1][(top / 107)+1] = 0;
                            board[(left / 107) +1][(top / 107)+2] = 0;
                            board[left / 107][top / 107] = 3;
                            board[(left / 107)-1][(top / 107) + 1] = 3;
                            board[(left / 107)][(top / 107) + 2] = 3;
                        }
                    }
                    break;
                case 'S': //base square is top left
                    if((board[(left/107)][top/107] == 0) && (board[(left/107)-1][(top/107)+1] == 0)) {
                        board[(left / 107)][top / 107] = 4;
                        board[(left / 107) - 1][(top / 107) + 1] = 4;
                        board[(left / 107) + 2][top / 107] = 0;
                        board[(left / 107) + 1][(top / 107) + 1] = 0;
                    }
                    break;
                case 'Z': //base square is top right
                    if((board[(left/107)-1][top/107] == 0) && (board[(left/107)][(top/107)+1] == 0)) {
                        board[(left / 107) - 1][top / 107] = 5;
                        board[(left / 107)][(top / 107) + 1] = 5;
                        board[(left / 107) + 1][top / 107] = 0;
                        board[(left / 107) + 2][(top / 107) + 1] = 0;
                    }
                    break;
                case 'J': //base square is top left
                    if((board[(left/107)-1][top/107] == 0) && (board[(left/107)-1][(top/107)+1] == 0)) {
                        board[(left / 107) - 1][top / 107] = 6;
                        board[(left / 107) - 1][(top / 107) + 1] = 6;
                        board[(left / 107)][top / 107] = 0;
                        board[(left / 107) + 2][(top / 107) + 1] = 0;
                    }
                    break;
                case 'L': //base square is top right
                    if((board[(left/107)+1][(top/107)] == 0) && (board[(left/107)-1][(top/107)+1] == 0)) {
                        board[(left / 107) + 1][(top / 107)] = 7;
                        board[(left / 107) - 1][(top / 107) + 1] = 7;
                        board[(left / 107) + 2][(top / 107)] = 0;
                        board[(left / 107) + 2][(top / 107) + 1] = 0;
                    }
                    break;
            }
        }
        postInvalidate(); //calls onDraw to update board
    }

    public void moveRight() { //button function to move right
        if(mRectSquare.right > 1000){  //movement is off slightly (642 right bound)
            System.out.println("Right bound reached!");
        }
        else {
            left = left + SQUARE_SIZE;
            mRectSquare.left = mRectSquare.left + SQUARE_SIZE;
            mRectSquare.right = mRectSquare.right + SQUARE_SIZE;
            switch (piece){
                case 'O': //reference square is top right
                    if((board[left/107][top/107] == 0) && (board[left/107][(top/107)+1] == 0)) { //check unit collision
                        board[(left / 107)][top / 107] = 1;
                        board[(left / 107)][(top / 107) + 1] = 1;
                        board[(left / 107) - 2][top / 107] = 0;
                        board[(left / 107) - 2][(top / 107) + 1] = 0;
                    }
                    break;
                case 'I': //reference square is 3rd
                    if(form == 1) {
                        if (board[(left / 107) + 1][top / 107] == 0) {  // check unit collision
                            board[(left / 107) - 3][top / 107] = 0;
                            board[(left / 107) + 1][top / 107] = 2;
                        }
                    }
                    else if(form == 2){
                        if((board[(left/107)][(top/107)] == 0) && ((board[(left/107)][(top/107)+1] == 0)) && (board[(left/107)][(top/107)+2] == 0) && (board[(left/107)][(top/107)+3] == 0)){
                            board[(left/107)][(top/107)] = 2;
                            board[(left/107)][(top/107)+1] = 2;
                            board[(left/107)][(top/107)+2] = 2;
                            board[(left/107)][(top/107)+3] = 2;
                            board[(left/107)-1][(top/107)] = 0;
                            board[(left/107)-1][(top/107)+1] = 0;
                            board[(left/107)-1][(top/107)+2] = 0;
                            board[(left/107)-1][(top/107)+3] = 0;
                        }
                    }
                    break;
                case 'T':
                    if(form == 1) {
                        if((board[left/107][top/107] == 0) && (board[(left/107)+1][(top/107)+1] == 0)) {  // check unit collision
                            board[(left / 107) - 1][top / 107] = 0;
                            board[(left / 107) - 2][(top / 107) + 1] = 0;
                            board[left / 107][top / 107] = 3;
                            board[(left / 107) + 1][(top / 107) + 1] = 3;
                        }
                    }
                    else if(form == 2){
                        if ((board[left / 107][top / 107] == 0) && (board[(left / 107)+1][(top / 107) + 1] == 0) && (board[(left/107)][(top/107)+2] == 0)){ // check unit collision
                            board[(left / 107)-1][(top / 107)] = 0;
                            board[(left / 107)-1][(top / 107)+1] = 0;
                            board[(left / 107)-1][(top / 107)+2] = 0;
                            board[(left / 107)][top / 107] = 3;
                            board[(left / 107)+1][(top / 107) + 1] = 3;
                            board[(left / 107)][(top / 107) + 2] = 3;
                        }
                    }
                    else if(form == 3){
                        if ((board[(left / 107)][(top / 107)+2] == 0) && (board[(left/107) + 1][(top/107)+1] == 0)) { // check unit collision;
                            board[(left / 107) - 2][(top / 107) + 1] = 0;
                            board[(left / 107) - 1][(top / 107) + 2] = 0;
                            board[(left / 107)+1][(top / 107) + 1] = 3;
                            board[(left / 107)][(top / 107) + 2] = 3;
                        }
                    }
                    else if(form == 4){
                        if ((board[left / 107][top / 107] == 0) && (board[(left / 107)][(top / 107) + 1] == 0) && (board[(left/107)][(top/107)+2] == 0)){ // check unit collision
                            board[(left / 107)-1][(top / 107)] = 0;
                            board[(left / 107) -2][(top / 107)+1] = 0;
                            board[(left / 107) -1][(top / 107)+2] = 0;
                            board[left / 107][top / 107] = 3;
                            board[(left / 107)][(top / 107) + 1] = 3;
                            board[(left / 107)][(top / 107) + 2] = 3;
                        }
                    }
                    break;
                case 'S':
                    if((board[(left/107)+1][top/107] == 0) && (board[(left/107)][(top/107)+1] == 0)) {
                        board[(left / 107) + 1][top / 107] = 4;
                        board[(left / 107)][(top / 107) + 1] = 4;
                        board[(left / 107) - 1][top / 107] = 0;
                        board[(left / 107) - 2][(top / 107) + 1] = 0;
                    }
                    break;
                case 'Z':
                    if((board[(left/107)][top/107] == 0) && (board[(left/107)+1][(top/107)+1] == 0)) {
                        board[(left / 107)][top / 107] = 5;
                        board[(left / 107) + 1][(top / 107) + 1] = 5;
                        board[(left / 107) - 2][top / 107] = 0;
                        board[(left / 107) - 1][(top / 107) + 1] = 0;
                    }
                    break;
                case 'J':
                    if((board[(left/107)-1][top/107] == 0) && (board[(left/107)+1][(top/107)+1] == 0)) {
                        board[(left / 107) - 1][top / 107] = 6;
                        board[(left / 107) + 1][(top / 107) + 1] = 6;
                        board[(left / 107) - 2][top / 107] = 0;
                        board[(left / 107) - 2][(top / 107) + 1] = 0;
                    }
                case 'L':
                    if((board[(left/107)+1][(top/107)] == 0) && (board[(left/107)+1][(top/107)+1] == 0)) {
                        board[(left / 107) + 1][(top / 107)] = 7;
                        board[(left / 107) + 1][(top / 107) + 1] = 7;
                        board[(left / 107)][(top / 107)] = 0;
                        board[(left / 107) - 2][(top / 107) + 1] = 0;
                    }
                    break;
            }
        }
        postInvalidate();
    }

    public void moveDown() { //button function to move down
        //System.out.println("BASE:  left: " + left + " top: " + top + " bottom: " + bottom + "  Mbottom: " + mRectSquare.bottom);
        //score += 100; FOR TESTING
        bottom = mRectSquare.bottom;
        if (bottom > 1400) {
            System.out.println("Piece cannot go down anymore! Drawing new piece...");
            newpiece = 0; //draw a new piece
            left = 535; //reset the location
            top = 0; //reset the location
            mRectSquare.bottom = top + SQUARE_SIZE;
        }
        else { //while not exceeding bottom of screen (1304dp by square movement)
            int count = 0;
            if((piece == 'Z')){
                for (int i = (left/107); i < (mRectSquare.right/107); i++){ // get how many EMPTY blocks are below the shape
                    if(board[i][mRectSquare.bottom/107] == 0){
                        count++;
                    }
                }
                if((mRectSquare.right/107) - (left/107) == count) { // if every block below shape is empty, do down function
                    top = top + SQUARE_DOWN;
                    bottom = mRectSquare.bottom + SQUARE_DOWN;
                    mRectSquare.top = mRectSquare.top + SQUARE_DOWN;
                    mRectSquare.bottom = mRectSquare.bottom + SQUARE_DOWN;
                }
                else{  // force the dimensions to reset if collision occurred before reaching the bottom of screen
                    newpiece = 0; //draw a new piece
                    left = 535; //reset the location
                    top = 0; //reset the location
                    mRectSquare.top = top;
                    mRectSquare.bottom = top + SQUARE_SIZE;
                }
            }
            else if(piece == 'S'){
                for (int i = (mRectSquare.left/107); i < ((mRectSquare.right/107)-1); i++){ // get how many EMPTY blocks are below the shape
                    if(board[i][mRectSquare.bottom/107] == 0){
                        count++;
                    }
                }
                if((board[(mRectSquare.right/107)-1][(mRectSquare.bottom/107)-1] == 0)){
                    count++;
                }

                if((mRectSquare.right/107) - (mRectSquare.left/107) == count) { // if every block below shape is empty, do down function
                    top = top + SQUARE_DOWN;
                    bottom = mRectSquare.bottom + SQUARE_DOWN;
                    mRectSquare.top = mRectSquare.top + SQUARE_DOWN;
                    mRectSquare.bottom = mRectSquare.bottom + SQUARE_DOWN;
                }
                else{  // force the dimensions to reset if collision occurred before reaching the bottom of screen
                    newpiece = 0; //draw a new piece
                    left = 535; //reset the location
                    top = 0; //reset the location
                    mRectSquare.top = top;
                    mRectSquare.bottom = top + SQUARE_SIZE;
                }
            }
            else if((piece == 'T') && (form != 1)){
                if(form == 2){
                    if((board[(mRectSquare.left/107)+1][(mRectSquare.bottom/107)-1] == 0)){
                        count++;
                    }
                    if((board[(mRectSquare.left/107)][(mRectSquare.bottom/107)] == 0)){
                        count++;
                    }
                }
                if(form == 3){
                    if((board[(mRectSquare.left/107)][(mRectSquare.bottom/107)-1] == 0)){
                        count++;
                    }
                    if((board[(mRectSquare.left/107)+2][(mRectSquare.bottom/107)-1] == 0)){
                        count++;
                    }
                    if((board[(mRectSquare.left/107)+1][(mRectSquare.bottom/107)] == 0)){
                        count++;
                    }
                }
                if(form == 4){
                    if((board[(mRectSquare.left/107)][(mRectSquare.bottom/107)-1] == 0)){
                        count++;
                    }
                    if((board[(mRectSquare.left/107)+1][(mRectSquare.bottom/107)] == 0)){
                        count++;
                    }
                }
                if((mRectSquare.right/107) - (mRectSquare.left/107) == count) { // if every block below shape is empty, do down function
                    top = top + SQUARE_DOWN;
                    bottom = mRectSquare.bottom + SQUARE_DOWN;
                    mRectSquare.top = mRectSquare.top + SQUARE_DOWN;
                    mRectSquare.bottom = mRectSquare.bottom + SQUARE_DOWN;
                }
                else{  // force the dimensions to reset if collision occurred before reaching the bottom of screen
                    newpiece = 0; //draw a new piece
                    left = 535; //reset the location
                    top = 0; //reset the location
                    mRectSquare.top = top;
                    mRectSquare.bottom = top + SQUARE_SIZE;
                }
            }
            else {  //DOESNT COUNT LAST BLOCK FOR FORM 2,3,4 OF T
                for (int i = (mRectSquare.left / 107); i < (mRectSquare.right / 107); i++) { // get how many EMPTY blocks are below the shape
                    if (board[i][mRectSquare.bottom / 107] == 0) {
                        count++;
                    }
                }
                if((mRectSquare.right/107) - (mRectSquare.left/107) == count) { // if every block below shape is empty, do down function
                    top = top + SQUARE_DOWN;
                    bottom = mRectSquare.bottom + SQUARE_DOWN;
                    mRectSquare.top = mRectSquare.top + SQUARE_DOWN;
                    mRectSquare.bottom = mRectSquare.bottom + SQUARE_DOWN;
                }
                else{  // force the dimensions to reset if collision occurred before reaching the bottom of screen
                    newpiece = 0; //draw a new piece
                    left = 535; //reset the location
                    top = 0; //reset the location
                    mRectSquare.top = top;
                    mRectSquare.bottom = top + SQUARE_SIZE;
                }
            }

            System.out.println("Empty blocks/REQ empty blocks: " + count + " " + ((mRectSquare.right/107) - (mRectSquare.left/107)));
            System.out.println("MT/MB/ML/MR: " + mRectSquare.top + " " + mRectSquare.bottom + " " + mRectSquare.left + " " + mRectSquare.right);

            if(newpiece != 0) {
                switch (piece) {
                    case 'O': //base square top right
                        if ((board[(left / 107) - 1][(top / 107) + 1] == 0) && (board[left / 107][(top / 107) + 1] == 0)) { // check unit collision
                            board[(left / 107) - 1][(top / 107) - 1] = 0;
                            board[(left / 107)][(top / 107) - 1] = 0;
                            board[(left / 107) - 1][(top / 107) + 1] = 1;
                            board[left / 107][(top / 107) + 1] = 1;
                        } else {
                            newpiece = 0; // piece reached a bottom barrier, draw a new piece
                        }
                        break;
                    case 'I':  //base square 3rd
                        if(form == 1){ //horizontal
                            if ((board[(left / 107) - 2][top / 107] == 0) && (board[(left / 107) - 1][top / 107] == 0) && (board[left / 107][top / 107] == 0) && (board[(left / 107) + 1][top / 107] == 0)) {
                            board[(left / 107) - 2][(top / 107) - 1] = 0;
                            board[(left / 107) - 1][(top / 107) - 1] = 0;
                            board[left / 107][(top / 107) - 1] = 0;
                            board[(left / 107) + 1][(top / 107) - 1] = 0;
                            board[(left / 107) - 2][top / 107] = 2;
                            board[(left / 107) - 1][top / 107] = 2;
                            board[left / 107][top / 107] = 2;
                            board[(left / 107) + 1][top / 107] = 2;
                            }
                        }
                        else if(form == 2){ //vertical, base top square
                            if((board[left/107][(top/107)+3] == 0)){
                                board[(left/107)][(top/107)-1] = 0;
                                board[(left/107)][(top/107)+3] = 2;
                            }
                        }
                        else {
                            newpiece = 0; // piece reached a bottom barrier, draw a new piece
                            form = 1; // reset form
                        }
                        break;
                    case 'T':
                        if(form == 1) {
                            if ((board[(left / 107) - 1][(top / 107) + 1] == 0) && (board[left / 107][(top / 107) + 1] == 0) && (board[(left / 107) + 1][(top / 107) + 1] == 0)) {
                                board[left / 107][(top / 107) - 1] = 0;
                                board[(left / 107) - 1][(top / 107)] = 0;
                                board[(left / 107) + 1][top / 107] = 0;
                                board[(left / 107) - 1][(top / 107) + 1] = 3;
                                board[left / 107][(top / 107) + 1] = 3;
                                board[(left / 107) + 1][(top / 107) + 1] = 3;
                            }
                        }
                        else if(form == 2){
                            if((board[(left/107)][(top/107)+2] == 0) && (board[(left/107)+1][(top/107)+1] == 0)){
                                board[(left/107)][(top/107)+2] = 3;
                                board[(left/107)+1][(top/107)+1] = 3;
                                board[(left/107)][(top/107)-1] = 0;
                                board[(left/107)+1][(top/107)] = 0;

                            }
                        }
                        else if(form == 3){
                            if ((board[(left / 107) - 1][(top / 107) + 1] == 0) && (board[left / 107][(top / 107) + 2] == 0) && (board[(left / 107) + 1][(top / 107)+1] == 0)) {
                                board[left / 107][(top / 107)] = 0;
                                board[(left / 107) - 1][(top / 107)] = 0;
                                board[(left / 107) + 1][top / 107] = 0;
                                board[(left / 107) - 1][(top / 107) + 1] = 3;
                                board[left / 107][(top / 107) + 2] = 3;
                                board[(left / 107) + 1][(top / 107) + 1] = 3;
                            }
                        }
                        else if(form == 4){
                            if((board[(left/107)][(top/107)+2] == 0) && (board[(left/107)-1][(top/107)+1] == 0)){
                                board[(left/107)][(top/107)+2] = 3;
                                board[(left/107)-1][(top/107)+1] = 3;
                                board[(left/107)][(top/107)-1] = 0;
                                board[(left/107)-1][(top/107)] = 0;
                            }
                        }
                        else {
                            newpiece = 0; // piece reached a bottom barrier, draw a new piece
                            form = 1;
                        }
                        break;
                    case 'S':
                        if((board[(left/107)-1][(top/107)+1] == 0) && (board[(left/107)][(top/107)+1] == 0) && (board[(left/107)+1][(top/107)] == 0)) {
                            board[(left / 107) - 1][(top / 107) + 1] = 4;
                            board[(left / 107)][(top / 107) + 1] = 4;
                            board[(left / 107) + 1][(top / 107)] = 4;
                            board[(left / 107) - 1][(top / 107)] = 0;
                            board[(left / 107)][(top / 107)-1] = 0;
                            board[(left / 107) + 1][(top / 107)-1] = 0;
                        } else {
                            newpiece = 0; // piece reached a bottom barrier, draw a new piece
                        }
                        break;
                    case 'Z':
                        if((board[(left/107)-1][(top/107)] == 0) && (board[(left/107)][(top/107)+1] == 0) && (board[(left/107)+1][(top/107)+1] == 0)) {
                            board[(left / 107) - 1][(top / 107)] = 5;
                            board[(left / 107)][(top / 107) + 1] = 5;
                            board[(left / 107) + 1][(top / 107) + 1] = 5;
                            board[(left / 107)][(top / 107) - 1] = 0;
                            board[(left / 107) - 1][(top / 107) - 1] = 0;
                            board[(left / 107) + 1][(top / 107)] = 0;
                        } else {
                            newpiece = 0; // piece reached a bottom barrier, draw a new piece
                        }
                        break;
                    case 'J':
                        if((board[(left/107)][(top/107)+1] == 0) && (board[(left/107)-1][(top/107)+1] == 0) && (board[(left/107)+1][(top/107)+1] == 0)) {
                            board[(left / 107)][(top / 107) + 1] = 6;
                            board[(left / 107) - 1][(top / 107) + 1] = 6;
                            board[(left / 107) + 1][(top / 107) + 1] = 6;
                            board[(left / 107)][(top / 107)] = 0;
                            board[(left / 107) - 1][(top / 107) - 1] = 0;
                            board[(left / 107) + 1][(top / 107)] = 0;
                        } else {
                            newpiece = 0; // piece reached a bottom barrier, draw a new piece
                        }
                        break;
                    case 'L':
                        if((board[(left/107)][(top/107)+1] == 0) && (board[(left/107)-1][(top/107)+1] == 0) && (board[(left/107)+1][(top/107)+1] == 0)) {
                            board[(left / 107)][(top / 107) + 1] = 7;
                            board[(left / 107) - 1][(top / 107) + 1] = 7;
                            board[(left / 107) + 1][(top / 107) + 1] = 7;
                            board[(left / 107)][(top / 107)] = 0;
                            board[(left / 107) - 1][(top / 107)] = 0;
                            board[(left / 107) + 1][(top / 107) - 1] = 0;
                        } else {
                            newpiece = 0; // piece reached a bottom barrier, draw a new piece
                        }
                        break;
                }
            }
        }

        //check if any row is filled
        if(newpiece == 0) { //if piece cannot go down anymore, then check
            int rowfill = 0;
            for (int i = 0; i < 13; i++) { //13 rows
                for (int j = 0; j < 10; j++) { //10 columns
                    if (board[j][i] != 0) {
                        rowfill++;
                        //System.out.println("Row/Filled: " + i + " " + rowfill);
                    }
                }
                //delete a row and shift everything down one row
                if (rowfill == 10) {
                    System.out.println("Deleting row " + i + "...");
                    for (int k = i; k > 0; k--) {
                        for (int l = 0; l < 10; l++) {
                            board[l][k] = board[l][k - 1];
                        }
                    }
                    for (int m = 0; m < 10; m++) {
                        board[m][0] = 0; //set the top row to empty if rows have been shifted down
                    }
                    score = score + 100;
                }
                rowfill = 0;
            }
        }
        postInvalidate();
    }

    protected void O(Canvas canvas) {
        left2 = left - SQUARE_SIZE;

        mRectSquare.left = left2; //draw square bottom left
        top2 = top + SQUARE_SIZE;
        mRectSquare.top = top2;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left / 107][mRectSquare.top/107] = 1; //store the block into array

        mRectSquare.left = left; //draw square bottom right
        top2 = top + SQUARE_SIZE; // accomodate for added block shift
        mRectSquare.top = top2;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left / 107][mRectSquare.top/107] = 1; //store the block into array

        mRectSquare.left = left2; //draw square left top
        mRectSquare.top = top;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left / 107][mRectSquare.top/107] = 1; //store the block into array

        mRectSquare.right = mRectSquare.left + (2*SQUARE_SIZE); //correction from left top square
        mRectSquare.bottom = mRectSquare.bottom + SQUARE_SIZE; //correction from left top square
    }

    protected void I (Canvas canvas) {
        left2 = left - SQUARE_SIZE;

        mRectSquare.left = left2; //draw lefter square
        mRectSquare.top = top;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 2;

        mRectSquare.left = mRectSquare.left - SQUARE_SIZE; //draw lefter x2 square
        mRectSquare.top = top;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 2;

        mRectSquare.left = mRectSquare.left + (3 * SQUARE_SIZE); //draw righter square
        mRectSquare.top = top;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 2;

        mRectSquare.left = mRectSquare.left - (3*SQUARE_SIZE); //correction from the rightmost square
    }

    protected void T (Canvas canvas){
        mRectSquare.left = mRectSquare.left - SQUARE_SIZE;
        mRectSquare.top = top;
        mRectSquare.right = left + (2*SQUARE_SIZE);
        mRectSquare.bottom = mRectSquare.top + (2*SQUARE_SIZE);

        board[mRectSquare.left/107][(mRectSquare.top/107)+1] = 3;
        board[(mRectSquare.left/107)+1][(mRectSquare.top/107)+1] = 3;
        board[(mRectSquare.left/107)+1][mRectSquare.top/107] = 3;
        board[(mRectSquare.left/107)+2][(mRectSquare.top/107)+1] = 3;
    }
    protected void S (Canvas canvas){
        mRectSquare.left = mRectSquare.left - SQUARE_SIZE; //Draw lower left square
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 4;

        mRectSquare.left = mRectSquare.left + SQUARE_SIZE; //Draw lower square
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 4;

        mRectSquare.left = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.top = top;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 4;

        mRectSquare.left = mRectSquare.left - (2*SQUARE_SIZE); //corrections from base square
        mRectSquare.bottom = mRectSquare.bottom + SQUARE_DOWN;
    }
    protected void Z (Canvas canvas){
        mRectSquare.left = mRectSquare.left - SQUARE_SIZE; //Left most square
        mRectSquare.top = top;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 5;

        mRectSquare.left = mRectSquare.left + SQUARE_SIZE; //Bottom left square
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 5;

        mRectSquare.left = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 5;

        mRectSquare.left = mRectSquare.left - (2*SQUARE_SIZE); // correction from base square on right
    }
    protected void J (Canvas canvas){
        mRectSquare.left = left - SQUARE_SIZE; //Bottom left square
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 6;

        mRectSquare.left = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 6;

        mRectSquare.left = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 6;

        mRectSquare.left = mRectSquare.left - (2*SQUARE_SIZE); // correction from the rightmost square
    }
    protected void L (Canvas canvas){
        mRectSquare.left = left + SQUARE_SIZE; //Bottom right square
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 7;

        mRectSquare.left = mRectSquare.left - SQUARE_SIZE;
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 7;

        mRectSquare.left = mRectSquare.left - SQUARE_SIZE;
        mRectSquare.top = top + SQUARE_SIZE;
        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
        board[mRectSquare.left/107][mRectSquare.top/107] = 7;

        mRectSquare.right = mRectSquare.right + (2*SQUARE_SIZE); // correction from the leftmost square
    }


    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("OnDraw: MT/MB/ML/MR: " + (mRectSquare.top/107) + "  " + (mRectSquare.bottom/107) + "  " + (mRectSquare.left/107) + " " + (mRectSquare.right/107));
        int tmpBot = 0; //handle contamination
        int tmpLeft = 0;
        int tmpTop = 0;
        int tmpRight = 0;
        tmpBot = mRectSquare.bottom;
        tmpLeft = mRectSquare.left;
        tmpTop = mRectSquare.top;
        tmpRight = mRectSquare.right;

        canvas.drawColor(Color.DKGRAY); //draw white canvas, fill with black squares showing white borders under
        for (int i = 0; i < 1100; i++) { //1070 bound
            for (int j = 0; j < 1400; j++) { //1391 bound
                mRectSquare.left = i;
                mRectSquare.top = j;
                mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                mPaintSquare.setColor(Color.BLACK);
                canvas.drawRect(mRectSquare, mPaintSquare);
                j = j + SQUARE_SIZE;
            }
            i = i + SQUARE_SIZE;
        }

        mRectSquare.bottom = tmpBot;
        mRectSquare.left = tmpLeft;
        mRectSquare.top = tmpTop;
        mRectSquare.right = tmpRight; //end handle contamination

        while(newpiece == 0 && endgame == 0) {
            //show the next random piece to appear   NEED TO DISPLAY ON SCREEN
            form = 1; //reset form if not reset already
            Random letter = new Random();
            int n = letter.nextInt(7) + 1;
            //n = 5; //hard code to test specific shape
            currentrand = nextrand; //initialized is O (cube)
            nextrand = n;
            switch(nextrand){
                case 1:
                    System.out.println("NEXT PIECE: O");
                    break;
                case 2:
                    System.out.println("NEXT PIECE: I");
                    break;
                case 3:
                    System.out.println("NEXT PIECE: T");
                    break;
                case 4:
                    System.out.println("NEXT PIECE: S");
                    break;
                case 5:
                    System.out.println("NEXT PIECE: Z");
                    break;
                case 6:
                    System.out.println("NEXT PIECE: J");
                    break;
                case 7:
                    System.out.println("NEXT PIECE: L");
                    break;
            }

            //check end of game
            if((currentrand == 1) || (currentrand == 3) || (currentrand == 4)){
                if((board[4][1] != 0) || (board[5][1] != 0)){
                    endgame++;
                }
                if(currentrand == 3){
                    if((board[6][1] != 0)){
                        endgame++;
                    }
                }
            }
            else if((currentrand == 2)){
                if((board[3][0] != 0) || (board[4][0] != 0) || (board[5][0] != 0) || (board[6][0] != 0)){
                    endgame++;
                }
            }
            else if((currentrand == 5)){
                if((board[5][1] != 0) || (board[6][1] != 0)){
                    endgame++;
                }
            }
            else if((currentrand == 6) || (currentrand == 7)){
                if((board[4][1] != 0) || (board[5][1] != 0) || (board[6][1] != 0)){
                    endgame++;
                }
            }

            if(endgame == 0) {
                switch (currentrand) { //draw the shapes initially, change index values, change index values in movement methods
                    case 1: //complete
                        piece = 'O';
                        board[4][0] = 1;
                        board[4][1] = 1;
                        board[5][0] = 1;
                        board[5][1] = 1;
                        mRectSquare.left = left; //draw base square for new piece ([5][0])
                        mRectSquare.top = top;
                        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                        O(canvas);
                        break;
                    case 2: //complete
                        piece = 'I';
                        board[3][0] = 2;
                        board[4][0] = 2;
                        board[5][0] = 2;
                        board[6][0] = 2;
                        mRectSquare.left = left; //draw base square for new piece ([5][0])
                        mRectSquare.top = top;
                        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                        I(canvas);
                        break;
                    case 3: //complete
                        piece = 'T';
                        board[5][0] = 3;
                        board[4][1] = 3;
                        board[5][1] = 3;
                        board[6][1] = 3;
                        mRectSquare.left = left; //draw base square for new piece ([5][0])
                        mRectSquare.top = top;
                        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                        T(canvas);
                        break;
                    case 4: //complete
                        piece = 'S';
                        board[5][0] = 4;
                        board[6][0] = 4;
                        board[5][1] = 4;
                        board[4][1] = 4;
                        mRectSquare.left = left; //draw base square for new piece ([5][0])
                        mRectSquare.top = top;
                        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                        S(canvas);
                        break;
                    case 5: //complete
                        piece = 'Z';
                        board[4][0] = 5;
                        board[5][0] = 5;
                        board[5][1] = 5;
                        board[6][1] = 5;
                        mRectSquare.left = left; //draw base square for new piece ([5][0])
                        mRectSquare.top = top;
                        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                        Z(canvas);
                        break;
                    case 6: //complete
                        piece = 'J';
                        board[4][0] = 6;
                        board[4][1] = 6;
                        board[5][1] = 6;
                        board[6][1] = 6;
                        mRectSquare.left = left - SQUARE_SIZE;
                        mRectSquare.top = top;
                        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                        J(canvas);
                        break;
                    case 7: //complete
                        piece = 'L';
                        board[6][0] = 7;
                        board[4][1] = 7;
                        board[5][1] = 7;
                        board[6][1] = 7;
                        mRectSquare.left = left + SQUARE_SIZE;
                        mRectSquare.top = top;
                        mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                        mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                        L(canvas);
                        break;
                }
                newpiece++;
            }
        }

        System.out.println("Array: MT/MB/ML/MR: " + (mRectSquare.top/107) + "  " + (mRectSquare.bottom/107) + "  " + (mRectSquare.left/107) + " " + (mRectSquare.right/107));
        // temp holders for values, values are contaminated upon painting to board
        tmpBot = mRectSquare.bottom;
        tmpLeft = mRectSquare.left;
        tmpTop = mRectSquare.top;
        tmpRight = mRectSquare.right;

        //draw any existing pieces to the board, retrieve data from the array
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 13; j++) {
                if (board[i][j] != 0) {
                    mRectSquare.left = i*107; //draw square
                    mRectSquare.top = j*107;
                    mRectSquare.right = mRectSquare.left + SQUARE_SIZE;
                    mRectSquare.bottom = mRectSquare.top + SQUARE_SIZE;
                    if(board[i][j] == 1) { // O is green
                        mPaintSquare.setColor(Color.GREEN);}
                    else if(board[i][j] == 2) {  // I is blue
                        mPaintSquare.setColor(Color.BLUE); }
                    else if(board[i][j] == 3) {  // T is yellow
                        mPaintSquare.setColor(Color.YELLOW); }
                    else if(board[i][j] == 4) {  // S is red
                        mPaintSquare.setColor(Color.RED); }
                    else if(board[i][j] == 5) {  // Z is magenta
                        mPaintSquare.setColor(Color.MAGENTA); }
                    else if(board[i][j] == 6) {  // J is cyan
                        mPaintSquare.setColor(Color.CYAN); }
                    else if(board[i][j] == 7) {  // L is light gray
                        mPaintSquare.setColor(Color.LTGRAY); }
                    canvas.drawRect(mRectSquare, mPaintSquare);
                }
            }
        }
        mRectSquare.bottom = tmpBot;
        mRectSquare.left = tmpLeft;
        mRectSquare.top = tmpTop;
        mRectSquare.right = tmpRight;

        //Draws score keeper
        Paint mpaint= new Paint();
        mpaint.setColor(Color.BLACK);
        mpaint.setStyle(Paint.Style.FILL);
        Paint paint2= new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(50);  //set text size
        String s = "Score: ";
        float w = paint2.measureText(s)/2;
        float textSize = paint2.getTextSize();
        paint2.setTextAlign(Paint.Align.CENTER);
        canvas.drawRect(100 - w, 100 - textSize, 100 + w, 100, mpaint);
        canvas.drawText(s + score, 150, 50 ,paint2);

        //Determine string to be printed for next piece
        String nextpiece = "";
        switch (nextrand) {
            case 1:
                nextpiece = "O";
                break;
            case 2:
                nextpiece = "I";
                break;
            case 3:
                nextpiece = "T";
                break;
            case 4:
                nextpiece = "S";
                break;
            case 5:
                nextpiece = "Z";
                break;
            case 6:
                nextpiece = "J";
                break;
            case 7:
                nextpiece = "L";
                break;
        }
        //Draws next piece onto canvas
        Paint mpaint2= new Paint();
        mpaint2.setColor(Color.BLACK);
        mpaint2.setStyle(Paint.Style.FILL);
        Paint paint3= new Paint();
        paint3.setColor(Color.WHITE);
        paint3.setTextSize(50);  //set text size
        String s2 = "Next piece: ";
        float w2 = paint3.measureText(s2)/2;
        float textSize2 = paint3.getTextSize();
        paint3.setTextAlign(Paint.Align.CENTER);
        canvas.drawRect(100 - w2, 100 - textSize2, 100 + w2, 100, mpaint2);
        canvas.drawText(s2 + nextpiece, 900, 50 ,paint3);

        if(endgame != 0){
            System.out.println("Board is full. Game over! Score: " + score);
        }
    }
}

        //___________________________THREADING___________________________________________________
        /*
        while(true){
            try{
                Thread.sleep(THREAD_SLEEP);
                canvas.drawColor(Color.BLACK); //draw black canvas
                canvas.drawRect(mRectSquare, mPaintSquare);
            } catch(InterruptedException e){
                System.out.println(String.format("interrupt %s", Thread.currentThread().getName()));
                interrupted = true;
            }
            finally{
                if(interrupted)
                    break;
            }
        }*/

    /*
    public void addShape(CustomView shape) { //CustomShape -> CustomView
        shapeList.add(shape);
        if (!threads.containsKey(shape.getName())) {
            Thread t = new Thread(new DrawThread(shape, this.getGraphics()),
                    shape.getName());
            threads.put(shape.getName(), t);
            t.start();
        }
        this.repaint();
    }

    public class DrawThread implements Runnable {
        private static final int THREAD_SLEEP = 100;
        private CustomShape shape;
        private Graphics2D g2d;
        private boolean interrupted = false;

        public DrawThread(CustomShape shape, Graphics g) {
            this.shape = shape;
            this.g2d = (Graphics2D)g;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(THREAD_SLEEP);
                    g2d.setColor(this.shape.getColor());
                    g2d.draw(this.shape.getShape());
                } catch (InterruptedException e) {
                    System.out.println(String.format("interrupt %s", Thread
                            .currentThread().getName()));
                    interrupted = true;
                } finally {
                    if (interrupted)
                        break;
                }
            }
        }
    }
    */
