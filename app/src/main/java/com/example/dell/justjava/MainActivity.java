package com.example.dell.justjava;

        import android.os.Build;
        import android.os.Bundle;
        import android.support.annotation.RequiresApi;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE5 = "com.example.justjava.MESSAGE5";
    public static final String EXTRA_MESSAGE6 = "com.example.justjava.MESSAGE6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    int noOfcoffees;
    public void add(View view) {
        noOfcoffees++;
        display(noOfcoffees);
    }
    public void sub(View view) {
        noOfcoffees--;
        if (noOfcoffees >= 0)
            display(noOfcoffees);
        else {

            Toast.makeText(getApplicationContext(), "INVALID CHOICE!", Toast.LENGTH_SHORT).show();
            noOfcoffees++;
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void choosetoppings(View view) {
        if(noOfcoffees>0) {
            EditText editTextname = (EditText) findViewById(R.id.name);
            EditText editTextphone_no = (EditText) findViewById(R.id.phone_no);
            if (editTextphone_no.length() < 10 || editTextname.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Invalid Name or Number", Toast.LENGTH_LONG).show();}
            else{

                Intent intent1 = new Intent(this, indiviual_coffee.class);
                Bundle b = new Bundle();
                b.putInt("noofcoffee", noOfcoffees);
                intent1.putExtras(b);

                String message1 = editTextname.getText().toString();
                String message2 = editTextphone_no.getText().toString();
                intent1.putExtra(EXTRA_MESSAGE5, message1);
                intent1.putExtra(EXTRA_MESSAGE6, message2);
                startActivity(intent1);

            }
        }
        else{
            Toast.makeText(getApplicationContext(), "No Coffee odered :-(", Toast.LENGTH_LONG).show();
        }


    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }
}
