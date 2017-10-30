package com.tijojose27.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    //SETTING INTIAL VIEWS
    private EditText mEditTextName;
    private TextView mTextViewQ1;
    private TextView mTextViewQ2;
    private TextView mTextViewQ4;
    private TextView mTextViewQ5;
    private EditText mEditTextA4;


    private CheckBox mCheckBoxA1;
    private CheckBox mCheckBoxA2;
    private CheckBox mCheckBoxA3;
    private CheckBox mCheckBoxA4;

    private RadioButton mRadioButtonA1_A;
    private RadioButton mRadioButtonA1_B;
    private RadioButton mRadioButtonA1_C;
    private RadioButton mRadioButtonA1_D;

    private RadioButton mRadioButtonA2_A;
    private RadioButton mRadioButtonA2_B;
    private RadioButton mRadioButtonA2_C;
    private RadioButton mRadioButtonA2_D;

    private Button mResetButton;
    private Button mScoreButton;

    private RadioGroup mRadioGroup;
    private RadioGroup m2RadioGroup;

    String mName="";
    int score =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //GETTING VIEW ID
        mEditTextName = (EditText) findViewById(R.id.name_Edit_View);
        mTextViewQ1 = (TextView) findViewById(R.id.question1_Text_View);
        mTextViewQ2 = (TextView) findViewById(R.id.question_2_Text_View);
        mTextViewQ4 = (TextView) findViewById(R.id.question4_Text_View);
        mTextViewQ5 = (TextView) findViewById(R.id.question5_Text_View);
        mEditTextA4 = (EditText) findViewById(R.id.a4_Edit_Text);

        mCheckBoxA1 = (CheckBox) findViewById(R.id.checkbox1);
        mCheckBoxA2= (CheckBox) findViewById(R.id.checkbox2);
        mCheckBoxA3= (CheckBox) findViewById(R.id.checkbox3);
        mCheckBoxA4= (CheckBox) findViewById(R.id.checkbox4);

        mRadioButtonA1_A = (RadioButton) findViewById(R.id.a1_Radio_Button);
        mRadioButtonA1_B = (RadioButton) findViewById(R.id.a2_Radio_Button);
        mRadioButtonA1_C = (RadioButton) findViewById(R.id.a3_Radio_Button);
        mRadioButtonA1_D = (RadioButton) findViewById(R.id.a4_Radio_Button);

        mRadioButtonA2_A = (RadioButton) findViewById(R.id.a2a_Radio_Button);
        mRadioButtonA2_B = (RadioButton) findViewById(R.id.a2b_Radio_Button);
        mRadioButtonA2_C = (RadioButton) findViewById(R.id.a2c_Radio_Button);
        mRadioButtonA2_D = (RadioButton) findViewById(R.id.a2d_Radio_Button);


        mResetButton= (Button) findViewById(R.id.reset_Button);
        mScoreButton = (Button) findViewById(R.id.get_score_Button);

        mRadioGroup= (RadioGroup) findViewById(R.id.Radio_Group);
        m2RadioGroup = (RadioGroup) findViewById(R.id.Radio_Group2);

        //updating UI to display questions and ANSWERS
        updateUI();

        //ONCLICK TO GET SCORE
        mScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getScore();
            }
        });

        //ONCLICK TO RESET SCORE
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetScore();
            }
        });

    }

    //UPDATE UI METHOD TO SET TEXT VALUES TO THE STRING VALUE
    private void updateUI(){
        mTextViewQ1.setText(R.string.q1);
        mTextViewQ2.setText(R.string.q2);
        mTextViewQ4.setText(R.string.q4);
        mTextViewQ5.setText(R.string.q5);

        mRadioButtonA1_A.setText(R.string.a1a);
        mRadioButtonA1_B.setText(R.string.a1b);
        mRadioButtonA1_C.setText(R.string.a1da_ans);
        mRadioButtonA1_D.setText(R.string.a1c);

        mRadioButtonA2_A.setText(R.string.a5a_a);
        mRadioButtonA2_B.setText(R.string.a5b);
        mRadioButtonA2_C.setText(R.string.a5c);
        mRadioButtonA2_D.setText(R.string.a5d);

        mCheckBoxA1.setText(R.string.a2a_a);
        mCheckBoxA2.setText(R.string.a2b);
        mCheckBoxA3.setText(R.string.a2c);
        mCheckBoxA4.setText(R.string.a2d_a);
    }

    //METHOD TO CALCULATE THE SCORE
    private void getScore(){
        //RESETING SCORE
        score=0;

        //GETTING NAME FROM APP
        mName = mEditTextName.getText().toString();

        //GETTING SELECTED RADIO_BUTTONS
        int selectedId = mRadioGroup.getCheckedRadioButtonId();
        if(selectedId!=-1){
            RadioButton radioButton = (RadioButton)findViewById(selectedId);
            if(radioButton.getId() == mRadioButtonA1_C.getId()){
                score++;
            }
        }
        //GETTING THE SELECTED CHECKBOXES AND ASSIGNING SCORE
        if(mCheckBoxA1.isChecked() && mCheckBoxA4.isChecked() &&
                !mCheckBoxA3.isChecked() && !mCheckBoxA2.isChecked()){
            score++;
        }

        //GETTING VALUE FOR THE EDIT TEXT AND COMPARING TO ANSWER
        String A4 = mEditTextA4.getText().toString().toLowerCase();
        if(A4.equals("double")){
            score++;
        }

        //GETTING ANSWER FOR THE QUESTION 5 FROM RADIO BUTTONS
        int selectedId2 = m2RadioGroup.getCheckedRadioButtonId();
        if(selectedId2!=-1){
            RadioButton radioButton = (RadioButton) findViewById(selectedId2);
            if(radioButton.getId()==mRadioButtonA2_A.getId()){
                score++;
            }
        }

        //GETTING SCORE AND MAKING TOAST
        Toast.makeText(QuizActivity.this, mName+" your score is "+score,Toast.LENGTH_SHORT).show();
    }

    //METHOD TO RESET THE APP
    private void resetScore(){
        score=0;
        mName="";
        mEditTextName.setText(mName);
        mCheckBoxA1.setChecked(false);
        mCheckBoxA2.setChecked(false);
        mCheckBoxA3.setChecked(false);
        mCheckBoxA4.setChecked(false);
        mRadioGroup.clearCheck();
        mEditTextA4.setText("");
        m2RadioGroup.clearCheck();
    }
}
