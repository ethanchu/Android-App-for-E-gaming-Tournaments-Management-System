package edu.gatech.seclass.tourneymanager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.models.Match;

/**
 * Created by atg23 on 3/3/2017.
 */

public class mgrMatchListAdapter  extends ArrayAdapter<Match> implements AdapterView.OnItemClickListener{

    // * Created by atg23 on 3/1/2017.

    //TODO: 1 Rename this class for your use case

        //TODO: 2 Change the class within the < > to the class you will be displaying
        private int selectedrow;
        private ArrayList<Match> data;
        //====================
        private Context context; //leave this
        //TODO: 3 Rename this constructor to the same as your class
        mgrMatchListAdapter(Activity context,
                               ArrayList<Match> data) //TODO 4 Change the class within the <> to match what you used above
        {
            super(context,-1,data); //leave this
            this.data = data;
            this.context = context;

            selectedrow = 0;


        }
        @Override
        public int getCount()
        {
            return data.size();
        } //Leave this

        @Override
        //TODO: 5 Change the return type of this method to the class you are displaying
        public Match getItem(int position)
        {
            return data.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) { //leave this constructor


            //get the player this item is referring to
            //TODO: 6 Change the type of "p" below to the class you are using
            Match p = getItem(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //leave this the same

            //TODO: 7 Replace "R.layout.user_view_detail_row" with "R.layout.{your custom row layout file}
            View rowView = inflater.inflate(R.layout.mgr_matches_row,
                    parent, false);


            //edit this list to match your needs
            //TODO: 8 modify the declarations below to get handles for each of the elements in your ROW layout
            TextView mgrMatchListPlayer1 = (TextView)rowView.findViewById(R.id.mgrMatchListPlayer1);
            TextView mgrMatchListPlayer2 = (TextView) rowView.findViewById(R.id.mgrMatchListPlayer2);
            TextView mgrMatchListState = (TextView) rowView.findViewById(R.id.mgrMatchListState);



            //TODO: 9 modify the logic here to set the values for each element in your row you want to change
            mgrMatchListPlayer1.setText(p.getPlayer1().getName()); //this sets the TextViewto the player name of this instance
            mgrMatchListPlayer2.setText(p.getPlayer2().getName());   //this sets the TextView to the player phone number of this instance
            mgrMatchListState.setText(p.getMatchStatus().getDisplay());  //this sets the TextView to the player username of this instance
            if(position != selectedrow) {
                rowView.setBackgroundColor(Color.WHITE);
            }else{
                rowView.setBackgroundColor(Color.CYAN);
            }



            return rowView;
        }


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectedrow = position;

            notifyDataSetChanged();
        }

        public int getSelectedrow(){
            return selectedrow;
        }


}
