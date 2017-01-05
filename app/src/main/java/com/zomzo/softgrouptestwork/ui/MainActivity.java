package com.zomzo.softgrouptestwork.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ScrollingTabContainerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zomzo.softgrouptestwork.R;
import com.zomzo.softgrouptestwork.domain.RGBHolder;
import com.zomzo.softgrouptestwork.domain.RGBPresenter;

public class    MainActivity extends AppCompatActivity implements RGBDataViewer, View.OnClickListener {
    private final int numOfRows = 10;
    private final String LOG_TAG ="MY_LOG";
    private final String R_TAG = "R";
    private final String G_TAG = "G";
    private final String B_TAG = "B";
    private RGBPresenter presenter;
    TableLayout vTableLayout;
    Button vRefresh;
    int tableChildStart, tableChildCount;
    private int[] r,g,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vRefresh = (Button) findViewById(R.id.activity_main_button);
        vRefresh.setOnClickListener(this);
        vTableLayout = (TableLayout) findViewById(R.id.activity_main_table);
        vTableLayout.setStretchAllColumns(true);
        vTableLayout.setShrinkAllColumns(true);
        presenter = new RGBPresenterImpl(this);
        if (savedInstanceState==null){
            presenter.getRGB();
        }
    }



    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(R_TAG,r);
        outState.putIntArray(G_TAG,g);
        outState.putIntArray(B_TAG,b);
        Log.d(LOG_TAG, "onSaveInstanceState");
    }
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        r = savedInstanceState.getIntArray(R_TAG);
        g = savedInstanceState.getIntArray(G_TAG);
        b = savedInstanceState.getIntArray(B_TAG);
        presenter.createHolder(r,g,b);
        Log.d(LOG_TAG, "onRestoreInstanceState");
    }
    @Override
    public void displayRGB(RGBHolder holder) {
        r = holder.getR();
        g = holder.getG();
        b = holder.getB();
        tableChildStart = vTableLayout.getChildCount();
        for (int current = 0; current < holder.getLength(); current++){
            TableRow tr = new TableRow(this);
            tr.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView labelR = new TextView(this);
            labelR.setText(r[current]+"");
            labelR.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView labelG = new TextView(this);
            labelG.setText(g[current]+"");
            labelG.setGravity(Gravity.CENTER_HORIZONTAL);
            TextView labelB = new TextView(this);
            labelB.setText(b[current]+"");
            labelB.setGravity(Gravity.CENTER_HORIZONTAL);
            tr.addView(labelR);
            tr.addView(labelG);
            tr.addView(labelB);
            tr.setBackgroundColor(Color.rgb(r[current],g[current],b[current]));
            vTableLayout.addView(tr);
        }
        tableChildCount = vTableLayout.getChildCount()-tableChildStart;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_button:
                vTableLayout.removeViews(tableChildStart,tableChildCount);
                presenter.getRGB();
                break;
        }
    }
}
