package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;

public class ActiveManagerMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_manger_mode);
    }


    public void mgr_active_view_match(View view){
        //TODO DB Implement

        //end TODO
        startActivity(new Intent(ActiveManagerMode.this, mgrControlMatches.class));

    }

    public void quitTournament(View v){
        DatabaseHelper.getInstance().getTournamentDao().endTournamentEarly(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getTournamentId());
        finish();
    }
    public void finishTournament(View v){
        DatabaseHelper.getInstance().getTournamentDao().endTournament(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getTournamentId());
        finish();
    }
}
