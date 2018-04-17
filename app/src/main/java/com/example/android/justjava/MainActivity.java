/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
       // int numberOfCoffees=2;
       // display(numberOfCoffees);
        //displayPrice(numberOfCoffees*100);
      /*  display(quantity);
        displayPrice(quantity*5);*/

    /*  Log.v("MainActivity","The price is  : "+price);*/
    //  String priceMessage="Total:$"+price+"\nThank you!";
      //displayMessage(priceMessage);
       // calculatePrice(quantity);


        boolean whippedCream=toppings(findViewById(R.id.whipped_cream));
        boolean Chocolate=toppings(findViewById(R.id.chocolate));
        int price=calculatePrice(whippedCream,Chocolate);
        String orderSummary=createOrderSummary(price,whippedCream,Chocolate);

        TextView textView=(TextView)findViewById(R.id.edit_text_name);
        String subject="Just Java Order For "+textView.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
/*    *//**
     * This method displays the given price on the screen.
     *//*
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    public void increment(View view)
    {
        Context context=getApplicationContext();
        if(quantity<100)
            quantity++;
        else
            Toast.makeText(this,"You can't order more than 100",Toast.LENGTH_SHORT).show();;

        displayQuantity(quantity);
    }
    public void decrement(View view)
    {
        if(quantity>1)
            quantity--;
        else
            Toast.makeText(this,"You can't order less than 1",Toast.LENGTH_SHORT).show();;

        displayQuantity(quantity);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private int calculatePrice(boolean whippedcream , boolean chocolate) {
        int price = quantity*5 ;

        if(whippedcream)
            price+=1*quantity;
        if(chocolate)
            price+=2*quantity;

        return price;
    }
    private String createOrderSummary(int price,boolean whippedCream,boolean Chocolate){
        String summary;
        EditText name=(EditText)findViewById(R.id.edit_text_name);
        String nameoforderer= name.getText().toString();
            summary="Name : "+nameoforderer+" \nAdd Whipped Cream ? "+whippedCream+"\nAdd Chocolate ? "+Chocolate+"\nQuantity = "+quantity+"\nTotal =$"+price+"\nThank you!";
        return summary;
    }
    private boolean toppings(View view){
        CheckBox check=(CheckBox)view;
        boolean checkState=check.isChecked();
        return checkState;
    }

}