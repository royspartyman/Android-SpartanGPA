package edu.msu.perrym23.spartangpa.tabs;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.msu.perrym23.spartangpa.R;

/**
 * Created by royspartyman on 12/27/16.
 */

public class CalculatorFragment extends Fragment {

    private Integer classAmount = 0;

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

    @BindView(R.id.hide_class)
    ImageButton hideClassIB;

    @BindView(R.id.add_class)
    ImageButton addClassIB;

    @OnClick(R.id.add_class)
    public void onAddClassClick() {
        classAmount +=1;
        if(classAmount < 6){
            addClass(classAmount - 1);
        }
        if (classAmount == 2){
            hideClassIB.setVisibility(View.VISIBLE);
        }
        else if(classAmount == 6){
            addClassIB.setVisibility(View.GONE);
            addClass(classAmount - 1);
        }
    }

    @OnClick(R.id.hide_class)
    public void onHideClassClick() {
        classAmount -= 1;
        if (classAmount == 1){
            hideClassIB.setVisibility(View.GONE);
        }
        else if (classAmount == 5){
            addClassIB.setVisibility(View.VISIBLE);
        }
        hideClass(classAmount);
    }

    @OnClick(R.id.calculate)
    public void onCalculateButtonClick() {

        float totalCredTimesGrade = 0;
        float totalCredits = 0;
        float prevCredits = 0;
        float prevGrade = 0;
        float lastGrade;
        float megaCredits;
        float megaGPA;
        float semesterGpa;
        float finalGrade;
        int correctEnding = 0;
        String message;
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
            if (gradesED.get(i).getText().toString().endsWith(".5") || gradesED.get(i).getText().toString().endsWith(".0") || gradesED.get(i).getText().toString().length() == 1) {
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

    public CalculatorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        ButterKnife.bind(this, view);
        classAmount = 1;
        return view;
    }

    private void addClass(int i) {
            classesED.get(i).setVisibility(View.VISIBLE);
            creditsED.get(i).setVisibility(View.VISIBLE);
            gradesED.get(i).setVisibility(View.VISIBLE);
    }

    private void hideClass(int i) {
            classesED.get(i).setVisibility(View.GONE);
            creditsED.get(i).setVisibility(View.GONE);
            gradesED.get(i).setVisibility(View.GONE);
    }

    public void displayErrorMessasge(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
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
