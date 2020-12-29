package com.example.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    int quantity = 1;
    public void submitOrder(View view) {
        CheckBox check = (CheckBox)findViewById(R.id.hasWhippedCream);
        boolean addWhippedCream = check.isChecked();

        CheckBox check2 = (CheckBox)findViewById(R.id.hasChocolateCream);
        boolean addChocolateCream = check2.isChecked();

        int price = 5;
        if(addWhippedCream)
            price = price+1;
        if(addChocolateCream)
            price = price+2;

        EditText nameField =(EditText) findViewById(R.id.nameField);
        String name = nameField.getText().toString();
        String msg = "Name: "+name+"\n";
        msg +="Add Whipped Cream?"+addWhippedCream+"\n";
        msg +="Add Chocolate Cream?"+addChocolateCream+"\n";
        msg += "Quantity: " + quantity+"\n";
        msg +="Total : $"+ quantity*price +"\n"+"Thank You!";
        displayPriceMessage(msg);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Order Summary for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,msg);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPriceMessage(String msg) {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(msg);
    }

    public void Increment(View view){
        if(quantity==100) {
            Toast.makeText(this,"Quantity can not be more than 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        display(quantity);
    }
    public void Decrement(View view){
        if(quantity == 1){
            Toast.makeText(this,"Please Order Some Coffee",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -=1;
        display(quantity);
    }

}