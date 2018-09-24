package com.example.bhakt.payment_module;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class payment_modes extends AppCompatActivity {
Button pay_mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_modes);

        pay_mode=(Button)findViewById(R.id.paymode);

        pay_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup group= (RadioGroup)findViewById(R.id.payment_mode);
                int btnid= group.getCheckedRadioButtonId();
                Button btn=(Button)findViewById(btnid);
                String mode=(String) btn.getText();
                //Toast.makeText(payment_modes.this,""+btnid,Toast.LENGTH_SHORT).show();



                //if(btnid==2131230807){                      //for paytm id=2131230807
                if(mode.equals("       paytm")){

                    //Toast.makeText(payment_modes.this,"please select a mode"+btnid,Toast.LENGTH_SHORT).show();

                    Intent intent = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
                    if (intent != null) {
                        // We found the activity now start the activity
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } else {
                        // Bring user to the market or let them choose an app?
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setData(Uri.parse("market://details?id=" + "net.one97.paytm"));
                        startActivity(intent);
                    }


                }

                else if(mode.equals("          cards")){        //for card payment id=2131230758

                    Intent card_details=new Intent(payment_modes.this,card_details.class);
                    startActivity(card_details);
                }

                else if(mode.equals("        netbanking")){             // for netbanking=2131230795
                    Intent card_details1=new Intent(payment_modes.this,card_details.class);
                    startActivity(card_details1);

                }
            }
        });


    }
}
