package edu.gatech.seclass.tourneymanager;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class PlayerModeUserlistActivity extends AppCompatActivity{

    // testing
    private ArrayList<Player> myPlayerList = new ArrayList();
    private ListView userList;
    private TextView userListInstructions;
    //end testing


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);

        userListInstructions = (TextView)findViewById(R.id.userListInstructions);
        userListInstructions.setText("");

        //database interface
        myPlayerList = (ArrayList)DatabaseHelper.getInstance().getPlayerDao().getPlayers();
        userList = (ListView)findViewById(R.id.userlistview);
        UserListAdapter a = new UserListAdapter(this,myPlayerList);
        userList.setAdapter(a);

    }


}
