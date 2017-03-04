package edu.gatech.seclass.tourneymanager;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Yichen Zhu on 2017/3/3.
 */

public class IndividualplayerActivity extends AppCompatActivity {

    private TextView Name;
    private TextView Username;
    private TextView Phonenumber;
    private TextView Deck;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteplayer);

        Name = (TextView) findViewById(R.id.name);
        Username = (TextView) findViewById(R.id.username);
        Phonenumber = (TextView) findViewById(R.id.phonenumber);
        Deck = (TextView) findViewById(R.id.deck);

        Intent i = getIntent();
        Bundle b = i.getBundleExtra("playerbd1");
        String name = b.getString("Name");
        String username = b.getString("UserName");
        String phone = b.getString("Phone");
        String deck = b.getString("Deck");

        Name.setText(name);
        Username.setText(username);
        Phonenumber.setText(phone);
        Deck.setText(deck);

    }
}
