package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    EditText workedHoursInput;
    EditText hourlyRateInput;
    TextView totalPayTextView, taxPayableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // we need to get the EditTexts and TextViews we defined in XML into our Java file in order to take input and display output to the user
        // we do it by specifying an Id in XML file and referencing the Id to fetch the elements in Java code
        // these EditTexts are used for taking input from the user
        workedHoursInput = findViewById(R.id.editTextWorkedHours);
        hourlyRateInput = findViewById(R.id.editTextHourlyRate);
        // these TextViews are used for displaying output to the user

        totalPayTextView = findViewById(R.id.editText_total_pay);
        taxPayableTextView = findViewById(R.id.editText_tax_payable);
    }

    // as we have specified this method in the onClick attribute of our button, this method will be called if the user clicks on the button
    public void calculateTaxAndPay(View view) {

            // after the button is clicked we fetch the data entered in the EditTexts using getText and toString methods
            // we then convert the string into double type using parseDouble method
            double HoursWorked = Double.parseDouble(workedHoursInput.getText().toString());
            double hourlyRate = Double.parseDouble(hourlyRateInput.getText().toString());
            // initializing methods for displaying in the output
            double pay = 0;
            double tax = 0;
            // calculating the output using the given formulas
            if (HoursWorked <= 40) {
                pay = HoursWorked * hourlyRate;

            } else {
                pay = (40 * hourlyRate)+ ((HoursWorked - 40) * hourlyRate*1.5);

            }
            tax = pay * 0.18;

            // displaying the output to the user by using setText method on TextViews
            // also used String format method in order to show only decimal places for double values

            totalPayTextView.setText(String.format("%.2f", pay));
            taxPayableTextView.setText(String.format("%.2f", tax));

    }

    // this method is used to inflate(render) the menu from the XML file
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // this method is used to implement functionality when any of the menu items are clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item_about_owner:
                // startActivity method is used to navigate from one activity to another
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}