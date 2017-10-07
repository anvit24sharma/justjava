package com.example.dell.justjava;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class indiviual_coffee extends AppCompatActivity {

   public static final String EXTRA_MESSAGE1 = "com.example.justjava.MESSAGE1";
    public static final String EXTRA_MESSAGE2 = "com.example.justjava.MESSAGE2";
    int c,w;
    boolean wc,cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indiviual_coffee);



        Bundle b = getIntent().getExtras();
        final int noOfCoffees = b.getInt("noofcoffee");
        final boolean[] whc = new boolean[noOfCoffees];
        final  boolean[] chc = new boolean[noOfCoffees];

        final ArrayList<String> coffee = new ArrayList<>();

        for (int i = 0; i < noOfCoffees; i++) {
            coffee.add("Coffee " + (i + 1))  ;
        }


        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coffee);

        final ListView listView = (ListView) findViewById(R.id.coffeeView);

        listView.setAdapter(itemsAdapter);



       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent,final View view, final int position, long id) {

                               String CoffeeName = coffee.get(position);
                               AlertDialog.Builder builder = new AlertDialog.Builder(indiviual_coffee.this);




                               String[] toppings = new String[]{
                                       "Whipped Cream",
                                       "Chocolate"
                               };

                               final boolean[] checkedtoppings = new boolean[]{
                                       wc = whc[position],
                                       cc = chc[position]
                               };


                               builder.setMultiChoiceItems(toppings, checkedtoppings, new DialogInterface.OnMultiChoiceClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                                       checkedtoppings[which] = isChecked;

                                   }
                               });
                                w=c=0;
                               // Specify the dialog is not cancelable
                               builder.setCancelable(false);

                               // Set a title for alert dialog
                               builder.setTitle("Pick Your Toppings For " + CoffeeName );

                               // Set the positive/yes button click listener
                               builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                                   @SuppressLint("SetTextI18n")
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {


                                       for (int i = 0; i < checkedtoppings.length; i++) {
                                           boolean checked = checkedtoppings[i];
                                           if (checked) {
                                               if (i == 0) {
                                                   whc[position] = true;
                                               } else if (i == 1) {
                                                   chc[position] = true;
                                               }
                                           } else {
                                               if (i == 0) {

                                                   whc[position] = false;

                                               } else if (i == 1) {

                                                   chc[position] = false;

                                               }

                                           }
                                       }

                                       for(int i=0;i<noOfCoffees;i++) {

                                           if(whc[i]){
                                               w++;}
                                           if(chc[i]){
                                               c++;}

                                           if (whc[position] && chc[position]) {
                                               ((TextView) view).setText("Choco Creameyy Coffee  ₹100");
                                               ((TextView) view).setTextColor(Color.parseColor("#D76248"));
                                               ((TextView) view).setTextSize(24);
                                           }
                                           else if (whc[position] && !chc[position]) {
                                               ((TextView) view).setText("Whipzz Coffee                      ₹70");
                                               ((TextView) view).setTextColor(Color.parseColor("#D76248"));
                                               ((TextView) view).setTextSize(24);
                                           }
                                           else if (!whc[position] && chc[position]) {
                                               ((TextView) view).setText("Chocolaty Coffee                  ₹80");
                                               ((TextView) view).setTextColor(Color.parseColor("#D76248"));
                                               ((TextView) view).setTextSize(24);
                                           }
                                           else {
                                               ((TextView) view).setText("Cold Coffee                          ₹50");
                                               ((TextView) view).setTextColor(Color.parseColor("#D76248"));
                                               ((TextView) view).setTextSize(24);
                                           }
                                       }
                                   }
                               });
               AlertDialog dialog = builder.create();
               dialog.show();
           }


       });
    }
  public void ordersummary(View v)
    {



        Bundle b1 = getIntent().getExtras();
        int noOfCoffees = b1.getInt("noofcoffee");
            int price;
            Intent intent = new Intent(this, order_summary.class);
            Bundle b = new Bundle();



        price = calculate(noOfCoffees);
                b.putInt("price", price );
                b.putInt("noofcoffee", noOfCoffees );
                b.putInt("whc", w );
                b.putInt("chc", c );
                intent.putExtras(b);


        Intent intent1 = getIntent();
        String message7 = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE5);
        String message8 = intent1.getStringExtra(MainActivity.EXTRA_MESSAGE6);
            intent.putExtra(EXTRA_MESSAGE1, message7);
            intent.putExtra(EXTRA_MESSAGE2, message8);



        startActivity(intent);
        }
    public int calculate(int num) {

        return( num *( 50 ) +w*20+c*30);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
