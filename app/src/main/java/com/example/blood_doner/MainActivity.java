package com.example.blood_doner;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;
    ImageView request_button,helpline_button,plasma_button,organization_button,a_positive,a_negative,b_positive,b_negative,ab_positive,ab_negative;
    ImageView o_positive, o_negative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set view with id
        drawerLayout = findViewById(R.id.drawer_layout);
        request_button = findViewById(R.id.request_button);
        helpline_button = findViewById(R.id.helpline_button);
        plasma_button = findViewById(R.id.plasma_button);
        organization_button = findViewById(R.id.organization_button);

        a_positive = findViewById(R.id.a_positive_button);
        a_negative = findViewById(R.id.a_negative_button);
        b_positive = findViewById(R.id.b_positive_button);
        b_negative = findViewById(R.id.b_negative_button);
        ab_positive = findViewById(R.id.ab_positive_button);
        ab_negative = findViewById(R.id.ab_negative_button);
        o_positive = findViewById(R.id.o_positive_button);
        o_negative = findViewById(R.id.o_negative_button);


        //main functionality
        //setting click event for all buttons

        request_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Request.class));
            }

        });

        helpline_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Help_Line.class));
            }
        });

        plasma_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Plasma.class));
            }
        });

        organization_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), Organization.class));
            }
        });

        a_positive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), APositive.class));
            }
        });

        a_negative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ANegative.class));
            }
        });

        b_positive.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), BPositive.class));
            }

        });

        b_negative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), BNegative.class));
            }
        });

        ab_positive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ABPositive.class));
            }

        });

        ab_negative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ABNegative.class));
            }

        });

        o_positive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), OPositive.class));
            }
        });

        o_negative.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ONegative.class));
            }
        });

    }






    //end

    public void ClickMenu(View view){
        //open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //close drawer layout
        //checks condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //when drawer is open close the drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){
        //recreate activity
        recreate();

    }

    public void ClickDashboard(View view){
        //redirect activity to dashboard
        redirectActivity(this, Dashboard.class); //Dashboard.class
    }

    public void ClickAboutUs(View view){
        redirectActivity(this, AboutUs.class); //AboutUs.class
    }

    public void ClickLogout(View view){
        Logout(this);
    }
    //temporary
    public static void Logout(Activity activity) {
//        AlertDialog.Builder = new AlertDialog.Builder(activity);
//        builder.setTitle("Logout");
//        builder.setMessage("Are you sure you want to logout?");
//        builder.setPositiveButton("YES", new DialogInterface.OnClickListener(){
//            @Override
//                    public void onClick(DialogInterface dialog, int which){
//                activity.finishAffinity();
                System.exit(0);
//            }
//        });

//        builder.setNegativeButton("NO", new DialogInterface.OnClickListener(){
//            @Override
//            public void onClick(DialogInterface dialog, int which){
//                dialog.dismiss();
//
//            }
//
//        });
//        builder.show();

    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
}