package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;

/**
 * Created by Yichen Zhu on 2017/3/2.
 */

public class UserlistActivity extends AppCompatActivity{

// testing
    private ArrayList<Player> myPlayerList = new ArrayList();
    private ListView userList;
    //end testing


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
// testing
        myPlayerList.add(new Player(001,"Player1", "Player1", "123456789",10.0, Deck.ENGINEER));
        myPlayerList.add(new Player(002,"Player2", "Player2", "987654321",10.0, Deck.ENGINEER));
        //endtesting

        //database interface
        myPlayerList = (ArrayList)DatabaseHelper.getInstance().getPlayerDao().getPlayers();
        userList = (ListView)findViewById(R.id.userlistview);
        UserListAdapter a = new UserListAdapter(this,myPlayerList);
        userList.setAdapter(a);
        setupListViewListener();
    }

    // Attaches a long click listener to the listview
    private void setupListViewListener() {
        userList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {

                        Toast.makeText(UserlistActivity.this, "Switch to Individual Player!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(UserlistActivity.this, IndividualplayerActivity.class);
                        Player selplayer = myPlayerList.get(pos);
                        Bundle b = new Bundle();
                        b.putString("Name", selplayer.getName());
                        b.putString("UserName", selplayer.getUsername());
                        b.putString("Phone", selplayer.getPhoneNumber());
                        b.putString("Deck",selplayer.getDeck().toString());
                        i.putExtra("playerbd1",b);
                        startActivity(i);

                    }

                });
    }


}
