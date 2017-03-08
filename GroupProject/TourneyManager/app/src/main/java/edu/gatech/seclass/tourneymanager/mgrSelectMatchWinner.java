package edu.gatech.seclass.tourneymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;
import edu.gatech.seclass.tourneymanager.dao.constants.TournamentStatus;
import edu.gatech.seclass.tourneymanager.models.Tournament;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;

public class mgrSelectMatchWinner extends AppCompatActivity {
    private RadioButton MatchResultP1Wins;
    private RadioButton MatchResultP2Wins;
    private Button MatchResultSubmit;
    private int matchID;
    Match myMatch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_select_match_winner);

        matchID = getIntent().getExtras().getInt("matchID"); //get match ID from previous activity
        myMatch = DatabaseHelper.getInstance().getMatchDao().getMatch(matchID);
        MatchResultP1Wins = (RadioButton)findViewById(R.id.MatchResultP1Wins);
        MatchResultP2Wins = (RadioButton)findViewById(R.id.MatchResultP2Wins);
        MatchResultSubmit = (Button)findViewById(R.id.MatchResultSubmit);

        MatchResultP1Wins.setText(myMatch.getPlayer1().getName());
        MatchResultP2Wins.setText(myMatch.getPlayer2().getName());

    }

    public void setMatchWinner(View v){
        if(MatchResultP1Wins.isChecked()){
            //P1 wins
            DatabaseHelper.getInstance().getMatchDao().updateMatchStatus(
                    myMatch.getMatchId(),
                    MatchStatus.COMPLETED,
                    myMatch.getPlayer1().getPlayerId()
            );
            makeMatches();
            finish();
        }else if(MatchResultP2Wins.isChecked()){
            //P2 wins
            DatabaseHelper.getInstance().getMatchDao().updateMatchStatus(
                    myMatch.getMatchId(),
                    MatchStatus.COMPLETED,
                    myMatch.getPlayer2().getPlayerId()
            );

            makeMatches();
            finish();
        }else{
            //winner not selected
            Toast.makeText(getApplicationContext(), "you must select either player1 or player2 to win",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void makeMatches(){

        List<Match> matches = DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();
        Collections.reverse(matches);
        int numPlayers  = DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getPlayers().size();

        //Note an assumption is made that all matches in a given round must complete before the next round of matches begin

        //This ensures (in a 16 player tournament):
        //match 0-7 are first round matches
        //match 8-11 are quarterfinal matches
        //match 12-13 are semifinal matches
        //match 14 is the third place playoff
        //match 15 is the final.

        //we are scheduling quarterfinals based on match results, this only happens with 16 players

        Integer tournID =  DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getTournamentId();
        boolean roundComplete = true;
        if(numPlayers>8 && matches.size() == 8){
            for(int i = 0;i<8;i++){
                roundComplete = roundComplete && matches.get(i).getMatchStatus() == MatchStatus.COMPLETED;
            }
            if(roundComplete){
                for(int i = 0;i<4;i++){
                    DatabaseHelper.getInstance().getMatchDao().createMatch(
                            tournID,
                            matches.get(2*i).getWinner().getPlayerId(),
                            matches.get(2*i+1).getWinner().getPlayerId()
                    );
                }
            }
        }


        if(matches.size() == numPlayers-4){
            roundComplete = true;
            for(int i = numPlayers-8;i<numPlayers-4;i++){
                roundComplete = roundComplete && matches.get(i).getMatchStatus() == MatchStatus.COMPLETED;
            }
            if(roundComplete){
                for(int i = 0;i<2;i++){
                    DatabaseHelper.getInstance().getMatchDao().createMatch(tournID,
                            matches.get(2*i + numPlayers-8).getWinner().getPlayerId(),
                            matches.get(2*i+1 + numPlayers-8).getWinner().getPlayerId());
                }
            }
        }
        
        //schedule finals
        if(matches.size()==numPlayers-2){
            //if both semifinal matches are completed
            if(matches.get(numPlayers-4).getMatchStatus() == MatchStatus.COMPLETED && matches.get(numPlayers-3).getMatchStatus() == MatchStatus.COMPLETED){
                //create third place runoff match
                DatabaseHelper.getInstance().getMatchDao().createMatch(
                        DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getTournamentId(),
                        matches.get(numPlayers-4).getLoser().getPlayerId(),
                        matches.get(numPlayers-3).getLoser().getPlayerId()
                );
                //create final
                DatabaseHelper.getInstance().getMatchDao().createMatch(
                        DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getTournamentId(),
                        matches.get(numPlayers-4).getWinner().getPlayerId(),
                        matches.get(numPlayers-3).getWinner().getPlayerId()
                );
            }
        }
    }
}
