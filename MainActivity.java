package com.example.ubundu.geoloc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Button btnShowLocation;
    double latitude ;
    double longitude ;
    double flatb=18.953190,flgb=72.848187;
    double flatc=18.951107,flgc=72.834042;
    double flatd=19.042082,flgd=72.820000;
    double flate=18.947162,flge=72.836231;
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    // GPSTracker class
    GPSTracker gps;
   Button b,img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
         b=(Button)findViewById(R.id.mp);
         img=(Button)findViewById(R.id.button2);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();
                    if(flgd < longitude &&longitude < flgb)
                    { if(flate<latitude && latitude<flatd)
                        {Toast.makeText(getBaseContext(),"Attendance marked",Toast.LENGTH_LONG).show();}
                      else{Toast.makeText(getBaseContext(),"Attendance not  marked get into geofencing area",Toast.LENGTH_LONG).show();}
                    }
                    else{Toast.makeText(getBaseContext(),"Attendance not  marked get into geofencing area",Toast.LENGTH_LONG).show();}
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't   get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i;
                i = new Intent(MainActivity.this,MapsActivity.class);
                i.putExtra("latitude",latitude);
                i.putExtra("longitude",longitude);
                startActivity(i);
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n=null;
                editor.putString("emailkey",n);
                editor.commit();
                Intent r=new Intent(MainActivity.this,Login.class);
                startActivity(r);

            }
        });
    }
    public void onBackPressed() {
        Intent k=new Intent(MainActivity.this,MainActivity.class);
        startActivity(k);
        finish();
    }
}