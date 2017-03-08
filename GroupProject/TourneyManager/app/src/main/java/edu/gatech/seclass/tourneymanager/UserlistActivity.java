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


    private ArrayList<Player> myPlayerList = new ArrayList();
    private ListView userList;
    private UserListAdapter a; //<<AGIFFT3, declared this here so the onActivityResult has access to it
    private TextView userListInstructions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        //database interface
        myPlayerList = (ArrayList)DatabaseHelper.getInstance().getPlayerDao().getPlayers();
        userList = (ListView)findViewById(R.id.userlistview);
        userListInstructions = (TextView)findViewById(R.id.userListInstructions);
        userListInstructions.setText("Click on a player to view details");
        a = new UserListAdapter(this,myPlayerList); //<<AGIFFT3, removed delcration here, just assignment, declared under class
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

                        //Toast.makeText(UserlistActivity.this, "Switch to Individual Player!", Toast.LENGTH_SHORT).show(); <<AGIFFT3, removing misc. Toasts
                        Intent i = new Intent(UserlistActivity.this, IndividualplayerActivity.class);
                        Player selplayer = myPlayerList.get(pos);
                        Bundle b = new Bundle();
                        b.putInt("curplayerid", selplayer.getPlayerId());
                        i.putExtra("playerbd1",b);
                        //startActivity(i); //<<AGIFFT3, commented out and calling for result, this will trigger "onActivityResult" when the child activity returns
                        startActivityForResult(i,0);

                    }

                });
    }
    //<<AGIFFT3, added this method that is called when the child activity (IndividualplayerActivity) returns
    //this will update the LisView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //update matches from database, something may have changed
        myPlayerList = (ArrayList)DatabaseHelper.getInstance().getPlayerDao().getPlayers();
        //notify adapter that status has changed
        a.refresh(myPlayerList);

    }


}
