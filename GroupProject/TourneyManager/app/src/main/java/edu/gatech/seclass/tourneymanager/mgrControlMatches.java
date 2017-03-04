package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.dao.constants.MatchStatus;
import edu.gatech.seclass.tourneymanager.models.Match;
import edu.gatech.seclass.tourneymanager.models.Player;


public class mgrControlMatches extends AppCompatActivity {
    private ListView mgrMatchList; //list within main to display the users
    private ArrayList<Match> myMatches;
    private PlayerMatchListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_control_matches);


        mgrMatchList = (ListView)findViewById(R.id.mgrTourSelPlayers);

        myMatches = (ArrayList)DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();

        adapter = new PlayerMatchListAdapter(this, myMatches);
        mgrMatchList.setAdapter(adapter);
    }

    public void StartMatchClick(View v){
        int position = adapter.getSelectedrow();
        if(position>=0){
            Match myMatch = myMatches.get(position);
            if(myMatch.getMatchStatus() == MatchStatus.NOTSTARTED){
                //start match
                DatabaseHelper.getInstance().getMatchDao().updateMatchStatus(myMatch.getMatchId(),MatchStatus.STARTED,null);
                myMatches = (ArrayList)DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();
                //tells data to refresh
            }else{
                Toast.makeText(getApplicationContext(), "Match is not not in Notstarted state",
                        Toast.LENGTH_SHORT).show();
            }
            //expensive, re-loads all matches from dB, but ensures its up to date

            adapter.notifyDataSetChanged();
        }
    }

    public void EndMatchClick(View v){
        int position = adapter.getSelectedrow();
        if(position>=0){
            Match myMatch = myMatches.get(position);
            if(myMatch.getMatchStatus() == MatchStatus.STARTED){
                //end match
                Bundle matchParams = new Bundle();

                matchParams.putInt("matchID",myMatch.getMatchId());
                //pass match ID to below.
                startActivityForResult(new Intent(mgrControlMatches.this,mgrSelectMatchWinner.class).putExtras(matchParams),0);

            }else{
                Toast.makeText(getApplicationContext(), "Match is not in Notstarted state",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }

    //this is used to determine if the reviewActivity started the Tournament or not
    //if it did start the Tournament this will return as well.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK){
            //update matches from database, something has changed
            myMatches = (ArrayList)DatabaseHelper.getInstance().getTournamentDao().getActiveTournament().getMatches();
            //notify adapter that status has changed
            adapter.notifyDataSetChanged();
            finish(); //finish this activity as well since a Tournament was created and we cannot create a second tournament while one is active.
        }

    }


// * Created by atg23 on 3/1/2017.

    //TODO: 1 Rename this class for your use case
    public class PlayerMatchListAdapter extends ArrayAdapter<Match> implements AdapterView.OnItemClickListener {
        //TODO: 2 Change the class within the < > to the class you will be displaying
        private ArrayList<Match> data;
        private int selectedrow = -1;
        //====================

        private Context context; //leave this
        //TODO: 3 Rename this constructor to the same as your class
        PlayerMatchListAdapter(Activity context,
                                ArrayList<Match> data) //TODO 4 Change the class within the <> to match what you used above
        {
            super(context,-1,data); //leave this
            this.data = data;
            this.context = context;


        }
        @Override
        public int getCount()
        {
            return data.size();
        } //Leave this

        @Override
        //TODO: 5 Change the return type of this method to the class you are displaying
        public Match getItem(int position)
        {
            return data.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { //leave this constructor


            //get the player this item is referring to
            //TODO: 6 Change the type of "p" below to the class you are using
            Match match = getItem(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //leave this the same

            //TODO: 7 Replace "R.layout.user_view_detail_row" with "R.layout.{your custom row layout file}
            View rowView = inflater.inflate(R.layout.mgr_matches_row,
                    parent, false);


            //edit this list to match your needs
            //TODO: 8 modify the declarations below to get handles for each of the elements in your ROW layout
            TextView mgrMatchListPlayer1 = (TextView)rowView.findViewById(R.id.mgrMatchListPlayer1);
            TextView mgrMatchListPlayer2 = (TextView) rowView.findViewById(R.id.mgrMatchListPlayer2);
            TextView mgrMatchListState = (TextView) rowView.findViewById(R.id.mgrMatchListState);



            //TODO: 9 modify the logic here to set the values for each element in your row you want to change
            mgrMatchListPlayer1.setText(match.getPlayer1().getName()); //this sets the TextViewto the player name of this instance
            mgrMatchListPlayer2.setText(match.getPlayer2().getName());   //this sets the TextView to the player phone number of this instance
            mgrMatchListState.setText(match.getMatchStatus().getDisplay());  //this sets the TextView to the player username of this instance

            //this code does some manipulation to the data to determine what to display to show you dont have to do a straight passthrough.
            if(match.getMatchStatus() == MatchStatus.COMPLETED){
                if(match.getWinner().equals(match.getPlayer1())){
                    mgrMatchListPlayer1.setBackgroundColor(Color.GREEN);
                }else if(match.getWinner().equals(match.getPlayer2())){
                    mgrMatchListPlayer2.setBackgroundColor(Color.GREEN);
                }
            }
            if(selectedrow == position){
                rowView.setBackgroundColor(Color.BLUE);
            }else{
                rowView.setBackgroundColor(Color.WHITE);
            }

            return rowView;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            view.setBackgroundColor(Color.BLUE);
        }
        public int getSelectedrow(){
            return selectedrow;
        }
    }

}
