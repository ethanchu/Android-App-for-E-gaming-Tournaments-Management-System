package edu.gatech.seclass.tourneymanager;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Toast;


/**
 * Created by Yichen Zhu on 2017/3/1.
 */

public class InactivemanagermodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inactivemanagermode);
    }


    public void addplayer(View view){
        //TODO DB Implement

        //end TODO
        Toast.makeText(InactivemanagermodeActivity.this, "Add Player!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(InactivemanagermodeActivity.this, AddPlayerActivity.class));

    }

    public void viewhouseprofits(View view){
        //TODO DB Implement

        //end TODO
        Toast.makeText(InactivemanagermodeActivity.this, "View House Profits!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(InactivemanagermodeActivity.this, HouseProfitsActivity.class));

    }

    public void viewuserlist(View view){
        //TODO DB Implement

        //end TODO
        Toast.makeText(InactivemanagermodeActivity.this, "View Playerlist!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(InactivemanagermodeActivity.this, UserlistActivity.class));

    }

    public void createtournament(View view){
        //TODO DB Implement

        //end TODO
        Toast.makeText(InactivemanagermodeActivity.this, "Create Tournament!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(InactivemanagermodeActivity.this, mgrSetupTournament.class));

    }
}
