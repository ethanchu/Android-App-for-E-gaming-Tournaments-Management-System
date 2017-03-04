package edu.gatech.seclass.tourneymanager;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;
import edu.gatech.seclass.tourneymanager.models.TournamentResult;

/**
 * Created by Yichen Zhu on 2017/3/2.
 */

public class HouseProfitsActivity extends AppCompatActivity {

    // testing
    private ArrayList<TournamentResult> myTournamentList = new ArrayList();
    //end testing

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houseprofits);
        // testing
        myTournamentList.add(new TournamentResult(001,001, 100.0, null,null, null));
        myTournamentList.add(new TournamentResult(002,002, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(003,003, 100.0, null,null, null));
        myTournamentList.add(new TournamentResult(004,004, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(005,005, 100.0, null,null, null));
        myTournamentList.add(new TournamentResult(006,006, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(007,007, 100.0, null,null, null));
        myTournamentList.add(new TournamentResult(36,36, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(136,136, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(101,101, 100.0, null,null, null));
        myTournamentList.add(new TournamentResult(102,102, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(103,103, 100.0, null,null, null));
        myTournamentList.add(new TournamentResult(104,104, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(105,105, 100.0, null,null, null));
        myTournamentList.add(new TournamentResult(106,106, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(107,107, 100.0, null,null, null));
        myTournamentList.add(new TournamentResult(236,236, 110.0, null,null, null));
        myTournamentList.add(new TournamentResult(236,236, 110.0, null,null, null));
        //endtesting
        ListView TourList = (ListView)findViewById(R.id.houseid);
        HouseprofitListAdapter a = new HouseprofitListAdapter(this,myTournamentList);
        TourList.setAdapter(a);


    }
}
