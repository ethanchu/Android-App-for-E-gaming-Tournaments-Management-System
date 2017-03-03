package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class mgrSetupTournament extends AppCompatActivity implements View.OnClickListener{

    private CheckBox user_sel_row_selUser; //used for the checkbox within the user select list layout
    private TextView user_sel_row_userName; //used to display the username within the user select list layout
    private ListView mgrTourSelPlayers; //list within main to display the users
    private EditText mgrSetHouseCut; //user sets house cut
    private EditText mgrEntreeFee; //user sets entrance fee
    private TextView mgrPlayerListTitle; //used as something to anchor errors in the player list
    private ArrayList<Player> myPlayers;
    Boolean[] PlayerSelected;

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
        PlayerSelected = new Boolean[myPlayers.size()];

        //initialize players to not selected
        for(int i = 0; i<PlayerSelected.length; i++){
            PlayerSelected[i] = false;
        }


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


        //tourneyParams.putStringArray("playerUserNames",playerUserNames); // adds usernames to Bundle
        tourneyParams.putInt("HouseCut",Integer.parseInt(mgrSetHouseCut.getText().toString()));
        tourneyParams.putInt("EntranceFee",Integer.parseInt(mgrEntreeFee.getText().toString()));
        startActivity(new Intent(mgrSetupTournament.this,mgrReviewTournSetup.class).putExtras(tourneyParams));

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

        int playerCount = 0;

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
        //returns an array trimmed to the length of the selected players.

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


    @Override
    public void onClick(View v) {
        //Replace "Checkbox" with your class
        CheckBox myv = (CheckBox)v;

        int position = -1; //this is cusotm to my implementatnoi

        if(myv.isChecked()){ //here I check if the checkbox is checked (true = checked, false = unchecked)
            if(myv.getTag() != null){ //here I see if the tag is set, in my Adapter I set this to the position, you should too
                position = (Integer)myv.getTag(); //again I set the tag to the postion of the object in the list
            }
            //I just popup a message with the index for debugging
            Toast.makeText(getApplicationContext(), "you XX checked: " + Integer.toString(position),
                    Toast.LENGTH_SHORT).show();
        }
        //a more useful function is to keep track of which players are checked to pass to a future activity
        if(position>=0) {
            PlayerSelected[position] = myv.isChecked();
        }
    }

}

