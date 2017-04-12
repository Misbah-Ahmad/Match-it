package com.example.fahimspc.gametest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class GameActivity extends AppCompatActivity {

    ArrayList<Integer> imgIds = new ArrayList<>();
    ScorePreference scorePreference;

    TextView timeTextView;
    TextView moveTextView;

    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;
    Button buttonFive;
    Button buttonSix;
    Button buttonSeven;
    Button buttonEight;
    Button buttonNine;

    int[][] buttonIds = new int[4][4];
    int[] vis = new int[12];
    int[][] image = new int[4][4];
    int moves;
    int emptyImage;


    CountDownTimer countDownTimer;
    long remaining;
    final  String MOVE_TEXT = "Moves: ";
    final  String TIME_TEXT = "Time Remaining: ";
    long totalTime = 100000;
    final long INTERVAL = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        try {
            this.getSupportActionBar().hide();
        } catch (Exception e){
            e.printStackTrace();
        }
        scorePreference = new ScorePreference(this);

        timeTextView = (TextView) findViewById(R.id.timeCounter);
        moveTextView = (TextView) findViewById(R.id.moveCounter);

        buttonOne = (Button) findViewById(R.id.buttonOne);
        buttonTwo = (Button) findViewById(R.id.buttonTwo);
        buttonThree = (Button) findViewById(R.id.buttonThree);
        buttonFour = (Button) findViewById(R.id.buttonFour);
        buttonFive = (Button) findViewById(R.id.buttonFive);
        buttonSix = (Button) findViewById(R.id.buttonSix);
        buttonSeven = (Button) findViewById(R.id.buttonSeven);
        buttonEight = (Button) findViewById(R.id.buttonEight);
        buttonNine = (Button) findViewById(R.id.buttonNine);


        moves = 0;
        String moveStr = MOVE_TEXT+moves;
        moveTextView.setText(moveStr);



        initializeIds();
        initializeVisited();
        shuffleImage();

        while(checkMatch()){
            shuffleImage();
        }

        timerCounter(totalTime, INTERVAL);
    }

    void initializeIds(){
        imgIds.add(R.drawable.sample_0);
        imgIds.add(R.drawable.sample_1);
        imgIds.add(R.drawable.sample_2);
        imgIds.add(R.drawable.sample_3);
        imgIds.add(R.drawable.sample_4);
        imgIds.add(R.drawable.sample_5);
        imgIds.add(R.drawable.sample_6);
        imgIds.add(R.drawable.sample_7);
        imgIds.add(R.drawable.sample_8);
        emptyImage = R.drawable.sample_2;

        buttonIds[0][0] = buttonOne.getId();
        buttonIds[0][1] = buttonTwo.getId();
        buttonIds[0][2] = buttonThree.getId();
        buttonIds[1][0] = buttonFour.getId();
        buttonIds[1][1] = buttonFive.getId();
        buttonIds[1][2] = buttonSix.getId();
        buttonIds[2][0] = buttonSeven.getId();
        buttonIds[2][1] = buttonEight.getId();
        buttonIds[2][2] = buttonNine.getId();


    }

    void initializeVisited(){
        for(int i=0; i<=9; i++){
            vis[i] = 0;
        }
    }

    int genRandom(){
        int ran = (int) (Math.random()*10);
        ran%=9;
        if(ran<=8 && ran>=0) return  ran;

        return  genRandom();
    }

    void shuffleImage(){

        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                Button button = (Button) findViewById(buttonIds[i][j]);

                int ran = genRandom();
                if (vis[ran] == 0) {
                    vis[ran] = 1;
                    button.setBackgroundResource(imgIds.get(ran));
                    image[i][j] = imgIds.get(ran);
                } else if (vis[ran] == 1) {
                    while (vis[ran] == 1) {
                        ++ran;
                        if (ran > 8) ran = 0;
                    }
                    button.setBackgroundResource(imgIds.get(ran));
                    vis[ran] = 1;
                    image[i][j] = imgIds.get(ran);
                }
            }
        }
    }


    //TIMER
    private void timerCounter(long time, final long interval){
        countDownTimer = new CountDownTimer(time, interval) {
            @Override
            public void onTick(long l)
            {
                remaining =l;
                String timeStr = TIME_TEXT+Long.toString(l/interval);
                timeTextView.setText(timeStr);
            }

            @Override
            public void onFinish() {
                if(checkMatch()){
                    customDialog(true);
                } else {
                    customDialog(false);
                }
            }
        };
        countDownTimer.start();
    }


    //Check if the puzzle is matched
    boolean checkMatch(){
        for(int i=0, k=0;i<3; i++) {
            for (int j = 0; j < 3; j++,k++) {
                if (image[i][j] != imgIds.get(k)) {
                    return false;
                }
            }
        }
        return  true;
    }

    int[] dx ={0,0, 1, -1};
    int[] dy = {1, -1, 0, 0};



    void move(View view){
    Log.d("CHECK", " fdslfj");
        int selectedId = view.getId();

        Button button = (Button) findViewById(selectedId);


        int x=0, y=0;
        outer:
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(buttonIds[i][j]==selectedId){
                    x=i;
                    y=j;
                    break outer;
                }
            }
        }
        int x1=0, y1=0;
        boolean canMove=false;
        for(int i=0; i<4; i++){
            x1 = x+dx[i];
            y1 = y+dy[i];
            if(x1<3 && x1>=0 && y1<3 && y1>=0 && image[x1][y1]==emptyImage){
                canMove=true;
                break;
            }
        }
        if(canMove){
            moves++;
            String moveStr = MOVE_TEXT+moves;
            moveTextView.setText(moveStr);
            button.setBackgroundResource(emptyImage);
            button = (Button) findViewById(buttonIds[x1][y1]);
            button.setBackgroundResource(image[x][y]);

            image[x1][y1] = image[x][y];
            image[x][y] = emptyImage;

            if(checkMatch()){
                countDownTimer.cancel();
                customDialog(true);
            }
        } else {
            showToast("Invalid Move");
        }

    }


    @Override
    public void onBackPressed() {
        exitDialog();
       // return;
    }

    void showToast(String msg){
        Toast.makeText(GameActivity.this, msg, Toast.LENGTH_SHORT).show();
    }




    private void exitDialog(){

        countDownTimer.cancel();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.exit_dialog_title);
        builder.setMessage(R.string.sure_button_text);

        builder.setPositiveButton(R.string.ok_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(GameActivity.this, MainActivity.class));
                finish();
            }
        });

        builder.setNegativeButton(R.string.cancel_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                timerCounter(remaining, INTERVAL);
            }
        });
        builder.create().show();

    }

    //Alert Dialogue For Game Finish
    private void customDialog(boolean win){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View view = LayoutInflater.from(this).inflate(R.layout.custom_dialogue, null);


        TextView textView = (TextView) view.findViewById(R.id.dialoguText);
        ImageView imageView = (ImageView) view.findViewById(R.id.dialoguImage);
        if(win) {

            //Check for Hgh Score
            boolean isSet= scorePreference.getBooleanPreferences(ScorePreference.IS_HIGH_SCORE_SET);
            int score = scorePreference.getIntegerPreferences(ScorePreference.HIGH_SCORE);
            if(!isSet || score < (500 - moves)){
                scorePreference.setIntegerPreferences(ScorePreference.HIGH_SCORE, 500 - moves);
                showToast("High Score!!!");
                if(!isSet) scorePreference.setBooleanPreferences(ScorePreference.IS_HIGH_SCORE_SET, true);

            }
            String winStr = "You\'ve Matched The Puzzle!!!\nYour Score is "+(500-moves);
            textView.setText(winStr);
            imageView.setImageResource(R.drawable.winner);
        } else {
            String finishStr = "Ops! Time is Over";
            textView.setText(finishStr);
            imageView.setImageResource(R.drawable.sorry);
        }

        builder.setView(view);

        builder.setPositiveButton(R.string.home_button_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(GameActivity.this, MainActivity.class));

            }
        });

        builder.setNegativeButton(R.string.play_again_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        builder.setCancelable(false);
        builder.create().show();
    }


}
