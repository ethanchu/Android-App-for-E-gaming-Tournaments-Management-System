package edu.gatech.seclass.tourneymanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;

public class PlayerModeUserlistActivity extends AppCompatActivity implements View.OnClickListener{

    // testing
    private ArrayList<Player> myPlayerList = new ArrayList();
    //end testing


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
// testing
        myPlayerList.add(new Player(001,"Player1", "Player1", "6467092083",10.0, Deck.ENGINEER));
        myPlayerList.add(new Player(002,"Player2", "Player2", "6467092083",10.0, Deck.ENGINEER));
        //endtesting
        ListView userList = (ListView)findViewById(R.id.userlistview);
        PlayerModeUserlistAdapter a = new PlayerModeUserlistAdapter(this,myPlayerList);
        userList.setAdapter(a);
    }

    public void onClick(View v) {
        //Replace "Checkbox" with your class
        TextView myv = (TextView) v; int position = -1; //this is cusotm to my implementatnoi
        if(myv.hasSelection()){ //here I check if the checkbox is checked (true = checked, false = unchecked)
            if(myv.getTag() != null){ //here I see if the tag is set, in my Adapter I set this to the position, you should too
                position = (Integer)myv.getTag(); //again I set the tag to the postion of the object in the list
            } //I just popup a message with the index for debugging
            Toast.makeText(getApplicationContext(), "you XX checked: " + Integer.toString(position), Toast.LENGTH_SHORT).show();
        }

    }

}
