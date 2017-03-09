package edu.gatech.seclass.tourneymanager;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import edu.gatech.seclass.tourneymanager.R;
import edu.gatech.seclass.tourneymanager.dao.DatabaseHelper;
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

        myTournamentList = (ArrayList) DatabaseHelper.getInstance().getTournamentResultDao().getTournamentResults();
        Collections.sort(myTournamentList);
        ListView TourList = (ListView)findViewById(R.id.houseid);
        HouseprofitListAdapter a = new HouseprofitListAdapter(this,myTournamentList);
        TourList.setAdapter(a);


    }
}
