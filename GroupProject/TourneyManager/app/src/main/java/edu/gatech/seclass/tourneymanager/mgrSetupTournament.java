package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;

import java.util.ArrayList;
import java.util.Arrays;


public class mgrSetupTournament extends AppCompatActivity{

    private CheckBox user_sel_row_selUser; //used for the checkbox within the user select list layout
    private TextView user_sel_row_userName; //used to display the username within the user select list layout
    private ListView mgrTourSelPlayers; //list within main to display the users
    private EditText mgrSetHouseCut; //user sets house cut
    private EditText mgrEntreeFee; //user sets entrance fee
    private TextView mgrPlayerListTitle; //used as something to anchor errors in the player list
    private ArrayList<Player> myPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mgr_setup_tournament);

        user_sel_row_selUser = (CheckBox)findViewById(R.id.selUser_check);
        user_sel_row_userName = (TextView)findViewById(R.id.selUser_userName);
        mgrTourSelPlayers = (ListView)findViewById(R.id.mgrTourSelPlayers);
        mgrSetHouseCut = (EditText)findViewById(R.id.mgrSetHouseCut);
        mgrEntreeFee = (EditText)findViewById(R.id.mgrEntreeFee);
        mgrPlayerListTitle = (TextView)findViewById(R.id.mgrPlayerListTitle);
        myPlayers = getPlayersFromdB();

        PlayerSelListAdapter adapter = new PlayerSelListAdapter(this, myPlayers);
        mgrTourSelPlayers.setAdapter(adapter);

    }

    //this is executed when the "Review Tournament Details" button is pressed
    //it first validates the data and if the validation returns true proceeds to the mgrReviewTournSetup Activity
    public void reviewTournament(View view){

        if(!verifyFields()){
            return; //return if fields are not valid
        }

        //this bundle will hold the data to be passed to the tournament reviewer and eventually into the Tournament object
        //it will pass
        // - an array of Strings that are the player username
        // - an int of the fee
        // - an int of the house percentage
        Bundle tourneyParams = new Bundle();

        //gather usernames of selected players
        int[] playeridc = getSelectedPlayerIdc();
        ArrayList<Integer> playerUserID = new ArrayList<Integer>();
        for(int i = 0; i<playeridc.length; i++){
            playerUserID.add(myPlayers.get(playeridc[i]).getPlayerId());
        }

        tourneyParams.putIntegerArrayList("playerUserID",playerUserID);
        tourneyParams.putInt("HouseCut",Integer.parseInt(mgrSetHouseCut.getText().toString()));
        tourneyParams.putInt("EntranceFee",Integer.parseInt(mgrEntreeFee.getText().toString()));
        startActivityForResult(new Intent(mgrSetupTournament.this,mgrReviewTournSetup.class).putExtras(tourneyParams),0);

    }
    //this is used to determine if the reviewActivity started the Tournament or not
    //if it did start the Tournament this will return as well.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK){
            finish(); //finish this activity as well since a Tournament was created and we cannot create a second tournament while one is active.
        }

    }


    //this method verifies all data entries and will throw an errors for violating fields
    //returns true if all fields are valid
    //returns false otherwise
    public boolean verifyFields() {

        boolean validFields = true;

        //if entrance fee is not empty
        //or if entrance fee is not positive
        if (mgrEntreeFee.getText().length() == 0 || Integer.parseInt(mgrEntreeFee.getText().toString()) <= 0) {
            validFields = false;
            mgrEntreeFee.setError("Entrance Fee must be >0");
        }

        //if house percentage is empty
        //or if house percentage is not between 0 and 100
        if (mgrSetHouseCut.getText().length() == 0 || Integer.parseInt(mgrSetHouseCut.getText().toString()) < 0 || Integer.parseInt(mgrSetHouseCut.getText().toString()) > 100) {
            validFields = false;
            mgrSetHouseCut.setError("house % must be between 0-100%");
        }

        //iterate over list elements counting number of playes checked
        View player_row;
        int playerCount = getSelectedPlayerIdc().length;

        if (playerCount != 8 && playerCount != 16) {
            validFields = false;
            Toast.makeText(getApplicationContext(), "There must be either 8 or 16 players, you selected " + Integer.toString(playerCount),
                    Toast.LENGTH_SHORT).show();

        }

        return validFields;

    }

    @NonNull
    private int[] getSelectedPlayerIdc(){
        View player_row;
        CheckBox player_check;
        Boolean[] PlayerSelected;
        int playerCount = 0;
        PlayerSelected = ((PlayerSelListAdapter)mgrTourSelPlayers.getAdapter()).getCheckboxStatus();
        int[] SelectedPlayerIdc = new int[PlayerSelected.length];

        //BUG: This will not work if the number of players is significantly greater than those can be rendered
        //Only rows with objects on the screen are counted
        //fix: have onclick event each time a button is toggled and track a bool list of checkbox states
        // mgrTourSelPlayers.getCount()


        for (int i = 0; i <PlayerSelected.length; i++) {

            if (PlayerSelected[i]) {
                SelectedPlayerIdc[playerCount] = i;
                playerCount++;
            }
        }


        return Arrays.copyOfRange(SelectedPlayerIdc,0,playerCount);

    }
    @NonNull
    private ArrayList<Player> getPlayersFromdB(){
        ArrayList<Player> allPlayers = new ArrayList<Player>();


        //use this as temporary to fill player list
        for(int i = 0; i<20; i++){
            allPlayers.add(new Player(i,
                            "player name " + Integer.toString(i),
                            "username" + Integer.toString(i),
                            "000-000-0000",
                            i * 3.0,
                            Deck.forValue(0)
                    )
            );
        }
        //use this when PlayerDao is ready
        //allPlayers = PlayerDao.getPlayers();
        //i use more primitive, fixed length Arrays, conver this to array here.
        return allPlayers;
    }
}

