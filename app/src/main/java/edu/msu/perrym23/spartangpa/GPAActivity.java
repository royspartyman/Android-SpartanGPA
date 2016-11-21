package edu.msu.perrym23.spartangpa;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GPAActivity extends AppCompatActivity {

    public static String CLASSAMOUNT = "edu.msu.perrym23.spartangpa";
    private Integer classAmount = 0;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitleTV;

    @BindViews({R.id.class_one, R.id.class_two, R.id.class_three, R.id.class_four, R.id.class_five, R.id.class_six})
    List<EditText> classesED;

    @BindViews({R.id.credits_one, R.id.credits_two, R.id.credits_three, R.id.credits_four, R.id.credits_five, R.id.credits_six})
    List<EditText> creditsED;

    @BindViews({R.id.grade_one, R.id.grade_two, R.id.grade_three, R.id.grade_four, R.id.grade_five, R.id.grade_six})
    List<EditText> gradesED;

    @BindView(R.id.calculate)
    Button calculateButton;

    @BindView(R.id.cumulative_gpa)
    TextView cumulativeGpaTV;

    @BindView(R.id.semester_gpa)
    TextView semesterGpaTV;

    @BindView(R.id.prev_credits)
    EditText previousCreditsET;

    @BindView(R.id.prev_grade)
    EditText previousgradeET;

    @OnClick(R.id.calculate)
    public void onCalculateButtonClick() {

        float totalCredTimesGrade = 0;
        float totalCredits = 0;
        float prevCredits = 0;
        float prevGrade = 0;
        float lastGrade = 0;
        float megaCredits = 0;
        float megaGPA = 0;
        float semesterGpa = 0;
        float finalGrade = 0;
        int correctEnding = 0;
        String message = "";
        int gradeMissingCounter = 0;
        int creditMissingCounter = 0;
        int higherThanFour = 0;

        for (int i = 0; i < classAmount; i++) {
            if (Objects.equals(gradesED.get(i).getText().toString(), "")) {
                ++gradeMissingCounter;
            } else {
                if (!Objects.equals(gradesED.get(i).getText().toString(), ".")) {
                    if (Double.parseDouble(gradesED.get(i).getText().toString()) > 4) {
                        ++higherThanFour;
                    }
                }
            }
            if (Objects.equals(creditsED.get(i).getText().toString(), "")) {
                ++creditMissingCounter;
            }
            if (gradesED.get(i).getText().toString().endsWith(".5") || gradesED.get(i).getText().toString().endsWith(".0")) {
                ++correctEnding;
            }
        }

        if (higherThanFour > 0) {
            message = "Grade can not be higher than 4.0!";
            displayErrorMessasge(message);
        } else if (correctEnding == 0 && creditMissingCounter == classAmount && gradeMissingCounter == classAmount) {
            message = "Please enter your information!";
            displayErrorMessasge(message);
        } else if (correctEnding < classAmount && creditMissingCounter == 0 && gradeMissingCounter == 0) {
            message = "Please use increments of .5 for grades!";
            displayErrorMessasge(message);
        } else if (correctEnding <= classAmount && creditMissingCounter > 0 && gradeMissingCounter > 0) {
            message = "Please enter all credits and grades!";
            displayErrorMessasge(message);
        } else if (correctEnding <= classAmount && creditMissingCounter < gradeMissingCounter) {
            message = "Please enter all grades!";
            displayErrorMessasge(message);
        } else if (correctEnding <= classAmount && gradeMissingCounter < creditMissingCounter) {
            message = "Please enter all credits!";
            displayErrorMessasge(message);
        } else {
            if (!Objects.equals(previousCreditsET.getText().toString(), "")) {
                prevCredits = Float.valueOf(previousCreditsET.getText().toString());
            }

            if (!Objects.equals(previousgradeET.getText().toString(), "")) {
                prevGrade = Float.valueOf(previousgradeET.getText().toString());
            }

            for (int i = 0; i < classAmount; i++) {
                totalCredTimesGrade += (Float.valueOf(gradesED.get(i).getText().toString())) * (Float.valueOf(creditsED.get(i).getText().toString()));
                totalCredits += Float.valueOf(creditsED.get(i).getText().toString());
            }

            semesterGpa = (totalCredTimesGrade / totalCredits);
            String semesterGpaStr = String.format("%.2f", semesterGpa);
            semesterGpaTV.setText(semesterGpaStr);

        /* Calculate the previous grades into current semester*/
            lastGrade = prevCredits * prevGrade;
            megaCredits = totalCredits + prevCredits;
            megaGPA = totalCredTimesGrade + lastGrade;

            finalGrade = (megaGPA / megaCredits);
            String s = String.format("%.2f", finalGrade);
            cumulativeGpaTV.setText(s);
        }
    }

    @OnClick(R.id.class_one)
    public void onClassOneClick() {
        classesED.get(0).setCursorVisible(true);
    }

    @OnClick(R.id.class_two)
    public void onClassTwoClick() {
        classesED.get(1).setCursorVisible(true);
    }

    @OnClick(R.id.class_three)
    public void onClassThreeClick() {
        classesED.get(2).setCursorVisible(true);
    }

    @OnClick(R.id.class_four)
    public void onClassFourClick() {
        classesED.get(3).setCursorVisible(true);
    }

    @OnClick(R.id.class_five)
    public void onClassFiveClick() {
        classesED.get(4).setCursorVisible(true);
    }

    @OnClick(R.id.class_six)
    public void onClassSixClick() {
        classesED.get(5).setCursorVisible(true);
    }

    @OnClick(R.id.backButton)
    public void onBackButtonClick() {
        super.onBackPressed();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/spartans.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        setContentView(R.layout.activity_gpa_calculate);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        classAmount = intent.getIntExtra(CLASSAMOUNT, 0);

        if (classAmount > 1) {
            addClasses();
        }

        if (classAmount == 1) {
            toolbarTitleTV.setText(classAmount.toString() + " " + getString(R.string.courseName));
        } else {
            toolbarTitleTV.setText(classAmount.toString() + " " + getString(R.string.classes));
        }

    }

    private void addClasses() {
        for (int i = 0; i < classAmount; i++) {
            classesED.get(i).setVisibility(View.VISIBLE);
            creditsED.get(i).setVisibility(View.VISIBLE);
            gradesED.get(i).setVisibility(View.VISIBLE);
        }
    }

    public void displayErrorMessasge(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(GPAActivity.this).create();
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}



