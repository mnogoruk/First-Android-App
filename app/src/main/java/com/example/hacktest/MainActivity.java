package com.example.hacktest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    public static final String KEY_INDEX = "index";

    private Question[] mQuestions = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceBundle) {

        super.onSaveInstanceState(savedInstanceBundle);
        savedInstanceBundle.putInt(KEY_INDEX, mCurrentIndex);
    }

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_buton);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_id);
        mNextButton = (Button) findViewById(R.id.next_button);

        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                updateQuestion();
            }
        });


    }

    private void checkAnswer(boolean pressedTrue){
        boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
        int messageresId;
        if (pressedTrue == answerIsTrue){
            messageresId = R.string.correct_toast;
        } else{
            messageresId = R.string.incorrect_toast;
        }
        Toast.makeText(MainActivity.this,
                messageresId,
                Toast.LENGTH_SHORT).show();
    }

    private void updateQuestion(){
        int question = mQuestions[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
}
