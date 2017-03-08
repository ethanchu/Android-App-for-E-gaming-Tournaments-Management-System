package edu.gatech.seclass.tourneymanager;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.seclass.tourneymanager.models.Player;

/**
 * Created by Yichen Zhu on 2017/3/2.
 */

//TODO: 1 Rename this class for your use case
public class UserListAdapter extends ArrayAdapter<Player> {
    //TODO: 2 Change the class within the < > to the class you will be displaying
    private ArrayList<Player> data;
    //====================

    private Context context; //leave this
    //TODO: 3 Rename this constructor to the same as your class
    UserListAdapter(Activity context,
                            ArrayList<Player> data) //TODO 4 Change the class within the <> to match what you used above
    {
        super(context,-1,data); //leave this
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount()
    {
        return data.size();
    } //Leave this

    @Override
    //TODO: 5 Change the return type of this method to the class you are displaying
    public Player getItem(int position)
    {
        return data.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //leave this constructor


        //get the player this item is referring to
        //TODO: 6 Change the type of "p" below to the class you are using
        Player p = getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //leave this the same

        //TODO: 7 Replace "R.layout.user_view_detail_row" with "R.layout.{your custom row layout file}
        View rowView = inflater.inflate(R.layout.userlist_row,
                parent, false);


        //edit this list to match your needs
        //TODO: 8 modify the declarations below to get handles for each of the elements in your ROW layout
        TextView username = (TextView)rowView.findViewById(R.id.username);
        TextView userwin = (TextView) rowView.findViewById(R.id.totalwin);


        //TODO: 9 modify the logic here to set the values for each element in your row you want to change
        username.setText(p.getName()); //this sets the TextView to the username of this instance

        userwin.setText(p.getWinnings().toString());   //this sets the TextView to the player totalwinnings of this instance


        return rowView;
    }
    //<<AGIFFT3, added a refresh method here that updates the dataset used in the list and tells the
    //list to refresh the current rows.  This is used if a player is deleted in a child activity
    public void refresh(ArrayList<Player> data){
        this.data = data;
        notifyDataSetChanged();
    }

}
