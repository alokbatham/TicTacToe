package com.rsl.com.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class CustomView extends View {

    TextView tv1 = findViewById(R.id.winnerTextView);

    public float xPartitionRatio = 1 / 3f;
    public float yPartitionRatio = 1 / 3f;
    public boolean touching = false;
    public boolean player1 = false;
    public boolean player2 = true;
    public int boxNumber;
    public float touchCoordinateY;
    public float touchCoordinateX;
    public float oneBoxWidth;
    public float oneBoxHeight;
    public int winner=0;
    Paint paintThis = new Paint(Paint.ANTI_ALIAS_FLAG);

    Rect r1 = new Rect(), r2 = new Rect(), r3 = new Rect(), r4 = new Rect(), r5 = new Rect(), r6 = new Rect(), r7 =
        new Rect(), r8 = new Rect(), r9 = new Rect();
    boolean endGame = false;
    boolean[] isPlayer1Touch = new boolean[9];
    boolean[] isPlayer2Touch = new boolean[9];
    boolean[] checkBoxChecked = new boolean[9];

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public int checkRect(float touchCoordinateX, float touchCoordinateY) {
        if (r1.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 1; }
        if (r2.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 2; }
        if (r3.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 3; }
        if (r4.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 4; }
        if (r5.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 5; }
        if (r6.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 6; }
        if (r7.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 7; }
        if (r8.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 8; }
        if (r9.contains((int) touchCoordinateX, (int) touchCoordinateY)) { return 9; }
        return 0;
    }

    public void drawVerticalLines(Canvas canvas) {
        paintThis.setColor(Color.WHITE);
        paintThis.setStrokeWidth(10);
        canvas.drawLine(oneBoxWidth, 0f, oneBoxWidth, getHeight(), paintThis);
        canvas.drawLine(2 * oneBoxWidth, 0f, 2 * oneBoxWidth, getHeight(), paintThis);
        invalidate();
    }

    public void drawHorizontalLines(Canvas canvas) {
        paintThis.setColor(Color.WHITE);
        paintThis.setStrokeWidth(10);
        canvas.drawLine(0f, oneBoxHeight, getHeight(), oneBoxHeight, paintThis);
        canvas.drawLine(0f, 2 * oneBoxHeight, getHeight(), 2 * oneBoxHeight, paintThis);
        invalidate();
    }

    public void drawCircle(Canvas canvas) {
        paintThis.setColor(Color.WHITE);
        if (isPlayer1Touch[0]) {
            canvas.drawCircle(oneBoxWidth * 1 / 2, oneBoxHeight * 1 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        if (isPlayer1Touch[1]) {
            canvas.drawCircle(oneBoxWidth * 3 / 2, oneBoxHeight * 1 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        if (isPlayer1Touch[2]) {
            canvas.drawCircle(oneBoxWidth * 5 / 2, oneBoxHeight * 1 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        if (isPlayer1Touch[3]) {
            canvas.drawCircle(oneBoxWidth * 1 / 2, oneBoxHeight * 3 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        if (isPlayer1Touch[4]) {
            canvas.drawCircle(oneBoxWidth * 3 / 2, oneBoxHeight * 3 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        if (isPlayer1Touch[5]) {
            canvas.drawCircle(oneBoxWidth * 5 / 2, oneBoxHeight * 3 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        if (isPlayer1Touch[6]) {
            canvas.drawCircle(oneBoxWidth * 1 / 2, oneBoxHeight * 5 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        if (isPlayer1Touch[7]) {
            canvas.drawCircle(oneBoxWidth * 3 / 2, oneBoxHeight * 5 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        if (isPlayer1Touch[8]) {
            canvas.drawCircle(oneBoxWidth * 5 / 2, oneBoxHeight * 5 / 2, oneBoxWidth * 1 / 4, paintThis);
        }
        invalidate();
    }

    public void drawCross(Canvas canvas) {
        Log.d("touch", "drawCross: " + touchCoordinateX + " " + touchCoordinateY);

        paintThis.setColor(Color.WHITE);
        if (isPlayer2Touch[0]) {
            canvas.drawLine(oneBoxWidth * 1 / 3,
                            oneBoxHeight * 1 / 3,
                            oneBoxWidth * 2 / 3,
                            oneBoxHeight * 2 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 1 / 3,
                            oneBoxHeight * 2 / 3,
                            oneBoxWidth * 2 / 3,
                            oneBoxHeight * 1 / 3,
                            paintThis);
        }
        if (isPlayer2Touch[1]) {
            canvas.drawLine(oneBoxWidth * 4 / 3,
                            oneBoxHeight * 1 / 3,
                            oneBoxWidth * 5 / 3,
                            oneBoxHeight * 2 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 4 / 3,
                            oneBoxHeight * 2 / 3,
                            oneBoxWidth * 5 / 3,
                            oneBoxHeight * 1 / 3,
                            paintThis);
        }
        if (isPlayer2Touch[2]) {
            canvas.drawLine(oneBoxWidth * 7 / 3,
                            oneBoxHeight * 1 / 3,
                            oneBoxWidth * 8 / 3,
                            oneBoxHeight * 2 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 7 / 3,
                            oneBoxHeight * 2 / 3,
                            oneBoxWidth * 8 / 3,
                            oneBoxHeight * 1 / 3,
                            paintThis);
        }
        if (isPlayer2Touch[3]) {
            canvas.drawLine(oneBoxWidth * 1 / 3,
                            oneBoxHeight * 4 / 3,
                            oneBoxWidth * 2 / 3,
                            oneBoxHeight * 5 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 2 / 3,
                            oneBoxHeight * 4 / 3,
                            oneBoxWidth * 1 / 3,
                            oneBoxHeight * 5 / 3,
                            paintThis);
        }
        if (isPlayer2Touch[4]) {
            canvas.drawLine(oneBoxWidth * 4 / 3,
                            oneBoxHeight * 4 / 3,
                            oneBoxWidth * 5 / 3,
                            oneBoxHeight * 5 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 4 / 3,
                            oneBoxHeight * 5 / 3,
                            oneBoxWidth * 5 / 3,
                            oneBoxHeight * 4 / 3,
                            paintThis);
        }
        if (isPlayer2Touch[5]) {
            canvas.drawLine(oneBoxWidth * 7 / 3,
                            oneBoxHeight * 4 / 3,
                            oneBoxWidth * 8 / 3,
                            oneBoxHeight * 5 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 7 / 3,
                            oneBoxHeight * 5 / 3,
                            oneBoxWidth * 8 / 3,
                            oneBoxHeight * 4 / 3,
                            paintThis);
        }
        if (isPlayer2Touch[6]) {
            canvas.drawLine(oneBoxWidth * 1 / 3,
                            oneBoxHeight * 7 / 3,
                            oneBoxWidth * 2 / 3,
                            oneBoxHeight * 8 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 1 / 3,
                            oneBoxHeight * 8 / 3,
                            oneBoxWidth * 2 / 3,
                            oneBoxHeight * 7 / 3,
                            paintThis);
        }
        if (isPlayer2Touch[7]) {
            canvas.drawLine(oneBoxWidth * 4 / 3,
                            oneBoxHeight * 7 / 3,
                            oneBoxWidth * 5 / 3,
                            oneBoxHeight * 8 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 4 / 3,
                            oneBoxHeight * 8 / 3,
                            oneBoxWidth * 5 / 3,
                            oneBoxHeight * 7 / 3,
                            paintThis);
        }
        if (isPlayer2Touch[8]) {
            canvas.drawLine(oneBoxWidth * 7 / 3,
                            oneBoxHeight * 7 / 3,
                            oneBoxWidth * 8 / 3,
                            oneBoxHeight * 8 / 3,
                            paintThis);
            canvas.drawLine(oneBoxWidth * 7 / 3,
                            oneBoxHeight * 8 / 3,
                            oneBoxWidth * 8 / 3,
                            oneBoxHeight * 7 / 3,
                            paintThis);
        }
        invalidate();
    }

    public void drawHighLightedRectangle(Canvas canvas) {
        paintThis.setColor(Color.GREEN);
        switch (boxNumber) {
            case 1:
                canvas.drawRect(r1.left, r1.top, r1.right, r1.bottom, paintThis);
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            default:

                break;
        }
    }

    public boolean check(Canvas canvas) {

        if ((isPlayer1Touch[0] && isPlayer1Touch[1] && isPlayer1Touch[2]) || (isPlayer2Touch[0] && isPlayer2Touch[1] && isPlayer2Touch[2])) {
            canvas.drawLine(oneBoxWidth * 1 / 5,
                            oneBoxHeight * 1 / 2,
                            oneBoxWidth * 14 / 5,
                            oneBoxHeight * 1 / 2,
                            paintThis);
            return true;
        } else if ((isPlayer1Touch[3] && isPlayer1Touch[4] && isPlayer1Touch[5]) || (isPlayer2Touch[3] && isPlayer2Touch[4] && isPlayer2Touch[5])) {
            canvas.drawLine(oneBoxWidth * 1 / 5,
                            oneBoxHeight * 3 / 2,
                            oneBoxWidth * 14 / 5,
                            oneBoxHeight * 3 / 2,
                            paintThis);
            return true;
        } else if ((isPlayer1Touch[6] && isPlayer1Touch[7] && isPlayer1Touch[8]) || (isPlayer2Touch[6] && isPlayer2Touch[7] && isPlayer2Touch[8])) {
            canvas.drawLine(oneBoxWidth * 1 / 5,
                            oneBoxHeight * 5 / 2,
                            oneBoxWidth * 14 / 5,
                            oneBoxHeight * 5 / 2,
                            paintThis);
            return true;
        } else if ((isPlayer1Touch[0] && isPlayer1Touch[3] && isPlayer1Touch[6]) || (isPlayer2Touch[0] && isPlayer2Touch[3] && isPlayer2Touch[6])) {
            canvas.drawLine(oneBoxWidth * 1 / 2,
                            oneBoxHeight * 1 / 5,
                            oneBoxWidth * 1 / 2,
                            oneBoxHeight * 14 / 5,
                            paintThis);
            return true;
        } else if ((isPlayer1Touch[1] && isPlayer1Touch[4] && isPlayer1Touch[7]) || (isPlayer2Touch[1] && isPlayer2Touch[4] && isPlayer2Touch[7])) {
            canvas.drawLine(oneBoxWidth * 3 / 2,
                            oneBoxHeight * 1 / 5,
                            oneBoxWidth * 3 / 2,
                            oneBoxHeight * 14 / 5,
                            paintThis);
            return true;
        } else if ((isPlayer1Touch[2] && isPlayer1Touch[5] && isPlayer1Touch[8]) || (isPlayer2Touch[2] && isPlayer2Touch[5] && isPlayer2Touch[8])) {
            canvas.drawLine(oneBoxWidth * 5 / 2,
                            oneBoxHeight * 1 / 5,
                            oneBoxWidth * 5 / 2,
                            oneBoxHeight * 14 / 5,
                            paintThis);
            return true;
        } else if ((isPlayer1Touch[0] && isPlayer1Touch[4] && isPlayer1Touch[8]) || (isPlayer2Touch[0] && isPlayer2Touch[4] && isPlayer2Touch[8])) {
            canvas.drawLine(oneBoxWidth * 1 / 5,
                            oneBoxHeight * 1 / 5,
                            oneBoxWidth * 14 / 5,
                            oneBoxHeight * 14 / 5,
                            paintThis);
            return true;
        } else if ((isPlayer1Touch[2] && isPlayer1Touch[4] && isPlayer1Touch[6]) || (isPlayer2Touch[2] && isPlayer2Touch[4] && isPlayer2Touch[6])) {
            canvas.drawLine(oneBoxWidth * 1 / 5,
                            oneBoxHeight * 14 / 5,
                            oneBoxWidth * 14 / 5,
                            oneBoxHeight * 1 / 5,
                            paintThis);
            checkWinner();
            Log.d("wins", "check: " + winner);
            return true;
        } else {
            return false;
        }
    }

    public void checkWinner(){
        if(isPlayer1Touch[0] && isPlayer1Touch[1] && isPlayer1Touch[2]) winner = 1; winner =2;
        if(isPlayer1Touch[3] && isPlayer1Touch[4] && isPlayer1Touch[5]) winner = 1; winner =2;
        if(isPlayer1Touch[6] && isPlayer1Touch[7] && isPlayer1Touch[8]) winner = 1; winner =2;
        if(isPlayer1Touch[0] && isPlayer1Touch[3] && isPlayer1Touch[6]) winner = 1; winner =2;
        if(isPlayer1Touch[1] && isPlayer1Touch[4] && isPlayer1Touch[7]) winner = 1; winner =2;
        if(isPlayer1Touch[2] && isPlayer1Touch[5] && isPlayer1Touch[8]) winner = 1; winner =2;
        if(isPlayer1Touch[0] && isPlayer1Touch[4] && isPlayer1Touch[8]) winner = 1; winner =2;
        if(isPlayer1Touch[2] && isPlayer1Touch[4] && isPlayer1Touch[6]) winner = 1; winner =2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                    touching = true;
                    touchCoordinateX = x;
                    touchCoordinateY = y;
                    boxNumber = checkRect(touchCoordinateX, touchCoordinateY);
                    if (!checkBoxChecked[boxNumber - 1]) {
                        player1 = !player1;
                        player2 = !player2;
                    }
                    Log.d("touch", "onTouchEvent: Box" + boxNumber);
                    Log.d("touch co-ordinates: ",
                          "onTouchEvent: " + x + " " + y + r1.left + " r1 " + r1.right + " r1 ");
                    invalidate();

                break;

            case MotionEvent.ACTION_UP:
                touching = false;
                invalidate();
                break;

            default:
                return false;

        }
        return true;
        //        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawVerticalLines(canvas);
        drawHorizontalLines(canvas);
        initializeSquares();

        if (check(canvas)) {
            drawCross(canvas);
            drawCircle(canvas);
        } else if (this.touching && !checkBoxChecked[boxNumber - 1]) {
            checkBoxChecked[boxNumber - 1] = true;

            if (player1 && !isPlayer2Touch[boxNumber - 1]) {
                isPlayer1Touch[boxNumber - 1] = true;
                drawCircle(canvas); // main p1
                drawCross(canvas);
            } else if (!isPlayer1Touch[boxNumber - 1]) {
                isPlayer2Touch[boxNumber - 1] = true;
                drawCross(canvas); // main p2
                drawCircle(canvas);
            }

        } else {
            drawCircle(canvas);
            drawCross(canvas);
        }
        drawCircle(canvas);
        drawCross(canvas);
        invalidate();
    }

    private void initializeSquares() {
        int x = getWidth();
        int y = getHeight();
        oneBoxWidth = x * xPartitionRatio;
        oneBoxHeight = y * yPartitionRatio;
        Log.d("touch", "initializeSquares: " + y + " Height " + x + " Width ");
        float a1 = 0;
        float a2 = x * xPartitionRatio;
        float a3 = x * 2 * xPartitionRatio;
        float a4 = x;

        float b1 = 0;
        float b2 = y * yPartitionRatio;
        float b3 = y * 2 * yPartitionRatio;
        float b4 = y;

        Log.d("touch", "initializeSquares: " + a1 + " " + a2 + " " + a3);
        Log.d("touch", "initializeSquares: " + b1 + " " + b2 + " " + b3);

        r1.set((int) a1, (int) b1, (int) a2, (int) b2);
        r2.set((int) a2, (int) b1, (int) a3, (int) b2);
        r3.set((int) a3, (int) b1, (int) a4, (int) b2);
        r4.set((int) a1, (int) b2, (int) a2, (int) b3);
        r5.set((int) a2, (int) b2, (int) a3, (int) b3);
        r6.set((int) a3, (int) b2, (int) a4, (int) b3);
        r7.set((int) a1, (int) b3, (int) a2, (int) b4);
        r8.set((int) a2, (int) b3, (int) a3, (int) b4);
        r9.set((int) a3, (int) b3, (int) a4, (int) b4);

        Log.d("touch",
              "initializeSquares: " + r1.left + " left " + r1.top + " top " + r1.right + " right " + r1.bottom + " bottom ");
    }

   /* public void clear() {

        tv1 = findViewById(R.id.winnerTextView);
        touching = false;
        player1 = false;
        player2 = true;
        boxNumber=0;
        touchCoordinateY=0;
        touchCoordinateX=0;
        oneBoxWidth=0;
        oneBoxHeight=0;
        winner=0;
        paintThis = new Paint(Paint.ANTI_ALIAS_FLAG);

        r1 = new Rect();
        r2 = new Rect();
        r3 = new Rect();
        r4 = new Rect();
        r5 = new Rect();
        r6 = new Rect();
        r7 = new Rect();
        r8 = new Rect();
        r9 = new Rect();
        endGame = false;
        isPlayer1Touch = new boolean[9];
        isPlayer2Touch = new boolean[9];
        checkBoxChecked = new boolean[9];

        invalidate();
    }*/
}