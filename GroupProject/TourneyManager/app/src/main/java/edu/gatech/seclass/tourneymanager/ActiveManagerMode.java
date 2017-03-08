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
import edu.gatech.seclass.tourneymanager.models.Tournament;

public class ActiveManagerMode extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_manger_mode);

    }


    public void mgr_active_view_match(View view){

        startActivity(new Intent(ActiveManagerMode.this, mgrControlMatches.class));

    }

    public void quitTournament(View v){
        //set tournament state to quit regardless of state of matches
        //do not create ...Result objects
        DatabaseHelper.getInstance().getTournamentDao().endTournamentEarly(DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getTournamentId());
        finish();
    }
    public void finishTournament(View v){
        Tournament activeTournament = DatabaseHelper.getInstance().getTournamentDao().getActiveTournament();
        List<Match> matches = activeTournament.getMatches();
        int numplayers = activeTournament.getPlayers().size();
        int numCompletedMatches = 0;
        for(int i = 0; i< matches.size();i++){
            if(matches.get(i).getMatchStatus() == MatchStatus.COMPLETED){
                numCompletedMatches++;
            }
        }
        //if all matches are completed and # matches = # numplayers (which is the case for single elimination + 3rd place playoff)
        //then we are good to close out the tournament
        if(numCompletedMatches == numplayers) {

            //First Calculate prizes and profits
            Double purse = (double)(numplayers * activeTournament.getEntryFee());
            Integer houseCut = (int)(purse / 100.0 * activeTournament.getHouseCut() + 0.5);

            Double player_purse = purse * (100 - activeTournament.getHouseCut()) / 100.0;

            //set prize values
            Integer FirstPrize = (int)(player_purse * 0.5 + 0.5);
            Integer SecondPrize = (int)(player_purse * 0.3 + 0.5);
            Integer ThirdPrize = (int)(player_purse * 0.2 + 0.5);


            //Second, Create tournament result and add to database
            DatabaseHelper.getInstance().getTournamentResultDao().createTournamentResult(
                    activeTournament.getTournamentId(),
                    matches.get(0).getWinner().getPlayerId(),
                    matches.get(0).getLoser().getPlayerId(),
                    matches.get(1).getWinner().getPlayerId(),
                    houseCut.doubleValue()
                    );

            //Third, create player results for 1st, 2nd, and 3rd and write to database
            //first prize
            DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(
                    activeTournament.getTournamentId(),
                    matches.get(0).getWinner().getPlayerId(),
                    FirstPrize.doubleValue()
            );
            //second prize
            DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(
                    activeTournament.getTournamentId(),
                    matches.get(0).getLoser().getPlayerId(),
                    SecondPrize.doubleValue()
            );
            //third prize
            DatabaseHelper.getInstance().getPlayerResultDao().createPlayerResult(
                    activeTournament.getTournamentId(),
                    matches.get(1).getWinner().getPlayerId(),
                    ThirdPrize.doubleValue()
            );

            //Fourth, End tournament
            DatabaseHelper.getInstance().getTournamentDao().endTournament(activeTournament.getTournamentId());
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "The tournament is not finished, complete all matches or quit",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
