package com.example.kanykeinurmambetova.sqlitedatabase;

import android.app.job.JobParameters;
import android.app.AlertDialog;
import android.database.Cursor;
import android.widget.EditText;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create a new instance/database
        myDb = new DatabaseHelper(this);
        Button searchPosts = findViewById(R.id.Search_posts);
        final EditText title = findViewById(R.id.titleEdit);
        final LinearLayout jobPostlist = findViewById(R.id.jobList);

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerIndustry);
        // Create an ArrayAdapter using a string array and a default spinner layout
            ArrayAdapter<CharSequence> industryAdapter = ArrayAdapter.createFromResource(this,R.array.industry_array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply adapter to the spinner
        spinner.setAdapter(industryAdapter);

        final Spinner spinner2 = (Spinner) findViewById(R.id.spinnerCity);
        // Create an ArrayAdapter using a string array and a default spinner layout
        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,R.array.industry_array, android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appears
        industryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply adapter to the spinner
        spinner.setAdapter(cityAdapter);



        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        spinner2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ;
            }
        });

        searchPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedIndustry = spinner.getSelectedItem().toString();
                String selectedCity = spinner2.getSelectedItem().toString();
                String titleText = title.getText().toString();

                ArrayList<Jobposts> posts =  myDb.getAllApplications(titleText, selectedIndustry, selectedCity);

                for (int i = 0; i < posts.size(); i++) {
                    Jobposts sa = posts.get(i);
                    TextView t = new TextView(getApplicationContext());
                    t.setText(sa.toString());
                    final int update_id = sa.getId();
                    t.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            Intent intent = new Intent(getApplicationContext(), WhenClickItem.class);
                            intent.putExtra("id", update_id);
                            startActivity(intent);
                        }});
                    jobPostlist.addView(t);
                }
            }
        });






    }

}



