package com.example.s198569_mappe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.s198569_mappe2.BOL.Person;
import com.example.s198569_mappe2.LIB.Constants;

import java.util.Date;

public class RegisterPerson extends AppCompatActivity {

    private EditText nameText, phoneText;
    private DatePicker bDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_person);

        nameText = (EditText) findViewById(R.id.addnewNameEdit);
        phoneText = (EditText) findViewById(R.id.addnewPhoneEdit);
        bDate = (DatePicker) findViewById(R.id.addnewDatePicker);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private boolean isValid(){
        boolean nameOK = false;
        boolean phoneOK = false;
        if(!nameText.getText().toString().matches(Constants.REGULAR_EXPRESSION_LETTERS_ONLY)){
            //nameText.requestFocus();
            nameText.setError(getString(R.string.regex_letters_only));
        }else
            nameOK = true;
        if(!phoneText.getText().toString().matches(Constants.REGULAR_EXPRESSION_NUMBERS_ONLY)){
            //phoneText.requestFocus();
            phoneText.setError(getString(R.string.regex_numbers_only));
        }else
            phoneOK = true;
        return (nameOK && phoneOK);
    }

    private void showLog(){
        Log.i(Constants.TAG_INPUT, nameText.getText().toString());
        Log.i(Constants.TAG_INPUT, phoneText.getText().toString());
        String dateInput = bDate.getDayOfMonth() + "." + bDate.getMonth() + "." + bDate.getYear();
        Log.i(Constants.TAG_INPUT, dateInput);
    }

    private Person getInputData(){
        String name = nameText.getText().toString();
        String pNumber = phoneText.getText().toString();
        long unixDate = bDate.getCalendarView().getDate();
        Date birthdayDate = new Date(unixDate);

        return new Person(name, pNumber, birthdayDate);
    }

    public void addMessage(View view){
        if(isValid()) {
            showLog();
            Intent addNewMessage = new Intent(RegisterPerson.this, RegisterMessage.class);
            addNewMessage.putExtra(Constants.TAG_PERSON, getInputData());
            addNewMessage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            RegisterPerson.this.startActivity(addNewMessage);
        }
    }


}
