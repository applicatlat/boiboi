package com.leventalahan.android.reportcard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        //When I rotated screen the selected language was changing. I solved it by setLocale(); I checked it from stackflow
    }

    public void addCourse (View view){


            String saveme = getResources().getString(R.string.saved);
            TextView name = (TextView) findViewById(R.id.pasteName);
            TextView surname = (TextView) findViewById(R.id.pasteSurName);
            TextView table = (TextView) findViewById(R.id.table);
              TextView list = (TextView) findViewById(R.id.listOn);

            SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString("name", name.getText().toString());
            editor.putString("surname", surname.getText().toString());
            editor.putString("table", table.getText().toString());
        editor.putString("list", list.getText().toString());

        editor.apply();

            Toast.makeText(this, saveme, Toast.LENGTH_LONG).show();

        Button btn = (Button) findViewById(R.id.button);
        String loadIt = getString(R.string.load);
        btn.setText(loadIt);
        Button btn1 = (Button) findViewById(R.id.button1);

        if(btn.getText().equals(loadIt)){
            btn.setVisibility(View.INVISIBLE);
            btn1.setVisibility(View.VISIBLE);
        }


    }
    public void load1 (View view) {
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sharedPref.getString("name", "");
        String surname = sharedPref.getString("surname", "");
        String table = sharedPref.getString("table", "");
        String list = sharedPref.getString("list", "");

        TextView name1 = (TextView) findViewById(R.id.pasteName);
        TextView surname1 = (TextView) findViewById(R.id.pasteSurName);
        TextView table1 = (TextView) findViewById(R.id.table);
        TextView list1 = (TextView) findViewById(R.id.listOn);

        name1.setText(name);
        surname1.setText(surname);
        table1.setText(table);
        list1.setText(list);


        Button btn = (Button) findViewById(R.id.button);
        String saved = getString(R.string.addCourse);
        btn.setText(saved);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.VISIBLE);

    }

    public void reset (View view){
        LinearLayout rootView = (LinearLayout) findViewById(R.id.listReport);
        TextView name = (TextView) findViewById(R.id.pasteName);
        TextView surname = (TextView) findViewById(R.id.pasteSurName);
        TextView table = (TextView) findViewById(R.id.table);
        TextView list = (TextView) findViewById(R.id.listOn);

        name.setText(null);
        surname.setText(null);
        table.setText(null);
        list.setText(null);

    }

    public void execute (View view){
        EditText name = (EditText) findViewById(R.id.editName);
        EditText classYear1 = (EditText) findViewById(R.id.yearClassEdit);
        EditText surName = (EditText) findViewById(R.id.editSurname);
        EditText classGrade1 = (EditText) findViewById(R.id.classGrade);
        EditText className = (EditText) findViewById(R.id.editClass);

        String yearClass = classYear1.getText().toString();

        String classNameOn = className.getText().toString();

        String surnamePaste = surName.getText().toString();

        String gradePaste = classGrade1.getText().toString();

        String namePaste = name.getText().toString();

        String control = getResources().getString(R.string.control);
        String yes = getResources().getString(R.string.yes);

        String no = getResources().getString(R.string.no);
            String dialogBoxTitle = getResources().getString(R.string.dialogTitle);


            if ((yearClass.matches("")) || (surnamePaste.matches("")) || (classNameOn.matches(""))
                    || (gradePaste.matches("")) || (namePaste.matches("")))
        {
                AlertDialog.Builder yesNo = new AlertDialog.Builder(MainActivity.this);
                yesNo.setMessage(control).setCancelable(false)
                        .setNegativeButton(no, new DialogInterface.OnClickListener() {
                            @Override
                            //I figured it out by myself
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }

                        });


                AlertDialog alert = yesNo.create();
                alert.setTitle(dialogBoxTitle);   // CHECK THIS LATER
                alert.show();
                return;
            }




        String nameOn = getString(R.string.name);

        String surnameOn = getString(R.string.surname);

        String classNameFix = getString(R.string.className1);

        String classGrade = getString(R.string.classGrade1);

        String classYear = getString(R.string.classYear1);




        displayName(nameOn + " " + namePaste);

        displaySurname(surnameOn + " " + surnamePaste);

        displayTable(classNameFix + "  " + "|" +"  " + classYear + "  " + "|" +"  " +  classGrade );

        classGrade1.setText(null);
        classYear1.setText(null);
        className.setText(null);


        // Create an array of words
        ArrayList<String> words =new ArrayList<String>();
        int index = 0;

        words.add(  index + "- " + classNameOn + "  " + "|" +"  " +  yearClass + "  " + "|" +"  " + gradePaste + "\n" );



// words add one sıfırıncı oldugundan aşşadaki 0 yukardaki 1'i işaret ediyor .
        // find the rootview of the layout

        TextView list1 = (TextView) findViewById(R.id.listOn);


        //while loop code


    //set up counter variable

        while(index<words.size()) {
            //Create a new that displayed the word at
            //and add the View as a child to the Rootview
            TextView wordView = new TextView(this);
            list1.setText(words.get(index));

            index ++; // index = index +1;
        }

    }
    public void displayName(String namePasteTo) {
        TextView nameStudent = (TextView) findViewById(R.id.pasteName);
        nameStudent.setText(String.valueOf(namePasteTo));
    }

    public void displaySurname(String surnamePasteTo) {
        TextView pasteSurName = (TextView) findViewById(R.id.pasteSurName);
        pasteSurName.setText(String.valueOf(surnamePasteTo));
    }

    public void displayTable(String tablePaste) {
        TextView pasteTable = (TextView) findViewById(R.id.table);
        pasteTable.setText(String.valueOf(tablePaste));

    }
}
