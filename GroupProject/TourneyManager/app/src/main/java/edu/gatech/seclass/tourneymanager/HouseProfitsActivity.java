package edu.gatech.seclass.tourneymanager;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

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

        // testing
//        DatabaseHelper.getInstance().getTournamentResultDao().createTournamentResult(1,1,2,3,1.0);
//        DatabaseHelper.getInstance().getTournamentResultDao().createTournamentResult(2,1,2,3,2.0);
        //endtesting

        myTournamentList = (ArrayList) DatabaseHelper.getInstance().getTournamentResultDao().getTournamentResults();
        ListView TourList = (ListView)findViewById(R.id.houseid);
        HouseprofitListAdapter a = new HouseprofitListAdapter(this,myTournamentList);
        TourList.setAdapter(a);


    }
}
