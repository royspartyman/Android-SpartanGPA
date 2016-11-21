package edu.msu.perrym23.spartangpa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ClassSelectionActivity extends AppCompatActivity {

    private Integer one  = 1;
    private Integer two = 2;
    private Integer three = 3;
    private Integer four = 4;
    private Integer five = 5;
    private Integer six = 6;

    @BindView(R.id.one_class)
    LinearLayout oneClassCV;

    @BindView(R.id.two_classes)
    LinearLayout twoClassesCV;

    @BindView(R.id.three_classes)
    LinearLayout threeClassesCV;

    @BindView(R.id.four_classes)
    LinearLayout fourClassesCV;

    @BindView(R.id.five_classes)
    LinearLayout fiveClassesCV;

    @BindView(R.id.six_classes)
    LinearLayout sixClassesCV;

    @OnClick(R.id.one_class)
    public void onOneClassClick(){
        createGpaIntent(one);
    }

    @OnClick(R.id.two_classes)
    public void onTwoClassesClick(){
        createGpaIntent(two);
    }

    @OnClick(R.id.three_classes)
    public void onThreeClassesClick(){
        createGpaIntent(three);
    }

    @OnClick(R.id.four_classes)
    public void onFourClassesClick(){
        createGpaIntent(four);
    }

    @OnClick(R.id.five_classes)
    public void onFiveClassesClick(){
        createGpaIntent(five);
    }

    @OnClick(R.id.six_classes)
    public void onSixClassesClick(){
        createGpaIntent(six);
    }

    public void createGpaIntent(Integer classAmount){
        Intent intent = new Intent(this, GPAActivity.class);
        intent.putExtra(GPAActivity.CLASSAMOUNT, classAmount);
        startActivity(intent);
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
        setContentView(R.layout.activity_class_selection);
        ButterKnife.bind(this);

    }

}
