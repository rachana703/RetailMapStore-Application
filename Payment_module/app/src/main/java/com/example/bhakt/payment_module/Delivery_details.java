package com.example.bhakt.payment_module;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Delivery_details extends AppCompatActivity {
    private ProgressDialog pDialog;
    JSONObject json;
    int wheretonext;
    JSONParser jParser = new JSONParser();
    Button add_address;
    EditText name_2;
    EditText phone_2;
    EditText pincode_2;
    EditText address1_2;
    EditText address2_2;
    EditText landmark_2;
    EditText city_2;
    EditText state_2;


    String name;
    String phone;
    String pincode;
    String address1;
    String address2;
    String landmark;
    String city;
    String state;

    private static String url="http://rmsanp.000webhostapp.com/delievery_dets.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);

        //drop dowm(spinner)
        /*Spinner spinner= findViewById(R.id.delivery_hour);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.delivery_hours,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/



        name_2 = (EditText) findViewById(R.id.name);
        phone_2 = (EditText) findViewById(R.id.phone);
        pincode_2 = (EditText) findViewById(R.id.pincode);
        address1_2 = (EditText) findViewById(R.id.address1);
        address2_2 = (EditText) findViewById(R.id.address2);
        landmark_2 = (EditText) findViewById(R.id.landmark);
        city_2 = (EditText) findViewById(R.id.city);
        state_2 = (EditText) findViewById(R.id.state);
        add_address = (Button) findViewById(R.id.add_address);




        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = name_2.getText().toString();
                phone = phone_2.getText().toString();
                pincode = pincode_2.getText().toString();
                address1 = address1_2.getText().toString();
                address2 = address2_2.getText().toString();
                landmark = landmark_2.getText().toString();
                city = city_2.getText().toString();
                state = state_2.getText().toString();


                if(name.length()==0)

                {
                    //NameEditText.requestFocus();
                    //NameEditText.setError("FIELD CANNOT BE EMPTY");
                    Toast.makeText(getApplicationContext(),"FIELD CANNOT BE EMPTY",Toast.LENGTH_SHORT).show();
                }

                else if(!name.matches("[a-zA-Z ]+"))
                {
                    //NameEditText.requestFocus();
                    // NameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    Toast.makeText(getApplicationContext(),"ENTER ONLY ALPHABETICAL CHARACTER",Toast.LENGTH_SHORT).show();
                }
                else if(phone.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Phone Can't be empty",Toast.LENGTH_SHORT).show();

                }
                else if(phone.length()!=10)
                {
                    Toast.makeText(getApplicationContext(),"Phone should have 10 digits",Toast.LENGTH_SHORT).show();

                }
                else if(pincode.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Pincode Can't be empty",Toast.LENGTH_SHORT).show();

                }
                else if(pincode.length()!=6)
                {
                    Toast.makeText(getApplicationContext(),"Pincode should have 6 digits",Toast.LENGTH_SHORT).show();

                }

                else if(address1.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Address 1 Can't be empty",Toast.LENGTH_SHORT).show();

                }
                else if(address2.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Address2 Can't be empty",Toast.LENGTH_SHORT).show();

                }
                else if(landmark.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Landmark Can't be empty",Toast.LENGTH_SHORT).show();

                }
                else if(city.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"City Can't be empty",Toast.LENGTH_SHORT).show();

                }
                else if(state.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"State Can't be empty",Toast.LENGTH_SHORT).show();

                }
                else{

                    new registeruser().execute();
                    Intent payment_modes=new Intent(Delivery_details.this,payment_modes.class);
                    startActivity(payment_modes);
                }
                // new registeruser().execute();
            }



        });
    }


    class registeruser extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Delivery_details.this);
            pDialog.setMessage("Adding "+name+" to the Database");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        protected String doInBackground(String... args) {

            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name",name));
                params.add(new BasicNameValuePair("phone",phone));
                params.add(new BasicNameValuePair("pincode",pincode));
                params.add(new BasicNameValuePair("address1",address1));
                params.add(new BasicNameValuePair("address2",address2));
                params.add(new BasicNameValuePair("landmark",landmark));
                params.add(new BasicNameValuePair("city",city));
                params.add(new BasicNameValuePair("state",state));


                json = jParser.makeHttpRequest(url,"POST",params);
                //Log.d("json:", json.toString());

                int status = json.getInt("status");//      getJSONObject("status");
                if(status == 1){
                    wheretonext = 1;


                }
                else if(status ==2){
                    wheretonext = 2;

                }

                else{
                    wheretonext = 0;
                }
                // check your log for json response

            }
            catch (JSONException e) {
                e.printStackTrace();
            }



            return null;
        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            if(wheretonext == 1){
                Log.d("Testing1","111");
            }
            else if(wheretonext == 2){
                Log.d("Testing2","222");


            }
        }

    }


}
