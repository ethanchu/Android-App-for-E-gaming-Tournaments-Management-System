package edu.gatech.seclass.tourneymanager;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Toast;

        import java.util.ArrayList;

        import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;


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

        //Toast.makeText(InactivemanagermodeActivity.this, "Add Player!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(InactivemanagermodeActivity.this, AddPlayerActivity.class));

    }

    public void viewhouseprofits(View view){

        //Toast.makeText(InactivemanagermodeActivity.this, "View House Profits!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(InactivemanagermodeActivity.this, HouseProfitsActivity.class));

    }

    public void viewuserlist(View view){

        //Toast.makeText(InactivemanagermodeActivity.this, "View Playerlist!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(InactivemanagermodeActivity.this, UserlistActivity.class));

    }

    public void createtournament(View view){

        //Toast.makeText(InactivemanagermodeActivity.this, "Create Tournament!", Toast.LENGTH_SHORT).show();
        startActivityForResult(new Intent(InactivemanagermodeActivity.this, mgrSetupTournament.class),0);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //if create tournament returned with result ok, then we started a tournament, return to main activity
       if(resultCode == Activity.RESULT_OK){
           finish();
       }
    }
}
