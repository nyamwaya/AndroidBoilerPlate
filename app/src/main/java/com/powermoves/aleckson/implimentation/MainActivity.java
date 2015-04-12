package com.powermoves.aleckson.implimentation;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.powermoves.aleckson.implimentation.Adapters.JSONAdpter;
import com.powermoves.aleckson.implimentation.Fragments.NavigationDrawerFragment;
import com.powermoves.aleckson.implimentation.Modeling.Games;
import com.powermoves.aleckson.implimentation.Networking.HTTPManager;
import com.powermoves.aleckson.implimentation.Parser.GamesJSONParser;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    //Toolbar
    private Toolbar mToolbar;
    private ViewPager mPager;


    private JSONAdpter adapter;
    private static String testURL = "https://www.kimonolabs.com/api/e7c6345o?apikey=QxPN6k3UB6aVCCjnmOzY5YdiTzugcpWw";
    private RecyclerView rView;
    private List<Games> gamesList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inflating toolbar
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //setup the NavigationDrawer
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

         rView = (RecyclerView) layout.findViewById(R.id.fra);
        rView.setHasFixedSize(true);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        rView.setLayoutManager(layout);

        fillRecyclerview task = new fillRecyclerview();
        task.execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */

    private class fillRecyclerview extends AsyncTask<Void, Void, List<Games>> {

        @Override
        protected List<Games> doInBackground(Void... params) {
            String json = HTTPManager.getData(testURL);
            gamesList = GamesJSONParser.parseFeed(json);
            return gamesList;
        }

        @Override
        protected void onPostExecute(List<Games> result){
            adapter =  new JSONAdpter(MainActivity.this, result);
            rView.setAdapter(adapter);
        }
    }
    public void onDrawerItemClicked(int index) {
        mPager.setCurrentItem(index);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

                // Setting the layout Manager
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
