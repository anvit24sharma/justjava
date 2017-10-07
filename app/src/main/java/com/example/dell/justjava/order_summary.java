package com.example.dell.justjava;
import android.content.ComponentName;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class order_summary extends AppCompatActivity {

    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_summary);





        Intent intent = getIntent();
        String message1 = intent.getStringExtra(indiviual_coffee.EXTRA_MESSAGE1);
        String message2 = intent.getStringExtra(indiviual_coffee.EXTRA_MESSAGE2);

        Bundle b = getIntent().getExtras();
        int price = b.getInt("price");
        int noofcoffees = b.getInt("noofcoffee");
        int w = b.getInt("whc");
        int c = b.getInt("chc");

        // Capture the layout's TextView and set the string as its text

        TextView ordersummaryTextView = (TextView) findViewById(R.id.order_text_view);
        if (w > 0 && c>0) {
            ImageView imageview1 = (ImageView) findViewById(R.id.wccimage);
            imageview1.setVisibility(View.VISIBLE);
            ordersummaryTextView.setText("Name : " + message1
                    + "\nContact no." + message2
                    + "\n Total No. of Coffee: " + noofcoffees
                    + "\nTotal Price: ₹" + price
                    + "\nThank you!");
            message = ("Name : " + message1
                    + "\nContact no." + message2
                    + "\n Total No. of Coffee :" + noofcoffees
                    + "\nTotal Price: ₹" + price
                    + "\nThank you!");
        }
        else if(c>0 && w==0){
            ImageView imageview1 = (ImageView) findViewById(R.id.ccimage);
            imageview1.setVisibility(View.VISIBLE);
            ordersummaryTextView.setText("Name : " + message1
                    + "\nContact no." + message2 + "\n Total No. of Coffee: " + noofcoffees +
                    "\nTotal Price: ₹" + price
                    + "\nThank you!");
            message = "Name : " + message1
                    + "\nContact no." + message2
                    + "\n Total No. of Coffee " + noofcoffees
                    + "\nTotal Price: ₹" + price
                    + "\nThank you!";

        }
        else if(c==0 && w>0){
            ImageView imageview1 = (ImageView) findViewById(R.id.wcimage);
            imageview1.setVisibility(View.VISIBLE);
            ordersummaryTextView.setText("Name : " + message1
                    + "\nContact no." + message2
                    + "\n Total No. of Coffee :" + noofcoffees
                    + "\nTotal Price: ₹" + price
                    + "\nThank you!");
            message =  "Name : " + message1
                    + "\nContact no." + message2
                    + "\n Total No. of Coffee " + noofcoffees
                    + "\nTotal Price: ₹" + price
                    + "\nThank you!";

        }
        else if(c==0 && w==0){
            ImageView imageview1 = (ImageView) findViewById(R.id.coffeeimage);
            imageview1.setVisibility(View.VISIBLE);
            ordersummaryTextView.setText( "Name : " + message1
                    + "\nContact no." + message2
                    + "\n Total No. of Coffee: " + noofcoffees
                    + "\nTotal Price: ₹" + price
                    + "\nThank you!");
            message =  "Name : " + message1
                    + "\nContact no." + message2
                    + "\n Total No. of Coffee " + noofcoffees
                    + "\nTotal Price: ₹" + price
                    + "\nThank you!" ;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
    public void composeEmail(View view) {

        Intent emailIntent = new Intent();
        emailIntent.setAction(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"anvit24sharma@gmail.com"});
        emailIntent.putExtra("address","8577929691");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JUST JAVA COFFEE ORDER");
        emailIntent.setType("message/rfc822");

        PackageManager pm = getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");


        Intent openInChooser = Intent.createChooser(emailIntent, "ORDER FROM");

        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);

        List<LabeledIntent> intentList = new ArrayList<>();

        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            if (packageName.contains("mms") ) {

                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setType("text/plain");

                    intent.putExtra("address","8577929691");
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    intent.putExtra("mms_body", message);


                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
            }
        }

        LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);

        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        startActivity(openInChooser);
    }

}



