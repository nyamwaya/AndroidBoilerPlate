package com.powermoves.aleckson.implimentation.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powermoves.aleckson.implimentation.Fragments.BballGamesFragment;
import com.powermoves.aleckson.implimentation.MainActivity;
import com.powermoves.aleckson.implimentation.Modeling.Games;
import com.powermoves.aleckson.implimentation.R;

import java.util.List;

/**
 * Created by aleckson on 4/12/2015.
 */
public class JSONAdpter extends RecyclerView.Adapter<JSONAdpter.ContactViewHolder> {

    private List<Games> gamesList;

    public JSONAdpter(MainActivity bballGamesFragment, List<Games> contactList) {
        this.gamesList = contactList;
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vEmail;

        public ContactViewHolder(View v) {
            super(v);
            vName =  (TextView) v.findViewById(R.id.name);
            vEmail = (TextView)  v.findViewById(R.id.email);
        }
    }
    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Games ci = gamesList.get(i);
        contactViewHolder.vName.setText(ci.getTAG_DESCRIPTION());
        contactViewHolder.vEmail.setText(ci.getTAG_NETWORK());


    }
    //select XML layout for each card
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_item, viewGroup, false);

        return new ContactViewHolder(itemView);
    }
}