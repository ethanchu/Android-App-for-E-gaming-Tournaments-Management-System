package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;
import edu.gatech.seclass.tourneymanager.models.Match;

public class ActiveManagerMode extends AppCompatActivity {
    private TextView tournStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_manger_mode);

        tournStatus = (TextView)findViewById(R.id.mgrActiveTournStatus);
        tournStatus.setText("ID:" +
                Integer.toString(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getTournamentId()) +
                ", #matches:" +
                Integer.toString(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches().size()) +
                ""
        );
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
        List<Match> matches = DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();
        int numplayers = DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getPlayers().size();
        int numCompletedMatches = 0;
        for(int i = 0; i< matches.size();i++){
            if(matches.get(i).getMatchStatus() == MatchStatus.COMPLETED){
                numCompletedMatches++;
            }
        }
        if(numCompletedMatches == numplayers) {
            DatabaseHelper.getInstance().getTournamentDao().endTournament(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getTournamentId());
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "The tournament is not finished, complete all matches or quit",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
