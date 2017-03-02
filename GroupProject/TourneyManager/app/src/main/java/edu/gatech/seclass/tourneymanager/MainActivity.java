package edu.gatech.seclass.tourneymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import edu.gatech.seclass.tourneymanager.dao.PlayerDao;
import edu.gatech.seclass.tourneymanager.dao.constants.Deck;
import edu.gatech.seclass.tourneymanager.models.Player;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        PlayerDao dao = new PlayerDao(getApplicationContext());

        dao.createPlayer("name", "user1", "123-123-1234", Deck.BUZZ);
        dao.createPlayer("name2", "user", "123", Deck.BUZZ);

        List<Player> players = dao.getPlayers();

        for(Player player : players) {
            Log.v("main", player.getName());
        }
    }
}
