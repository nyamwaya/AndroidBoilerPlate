package com.powermoves.aleckson.implimentation.Parser;

import com.powermoves.aleckson.implimentation.Modeling.Games;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aleckson on 4/11/2015.
 */
public class GamesJSONParser {
    // URL to get contacts JSON


    public static List<Games> parseFeed(String content) {



            try {
                JSONObject jb = new JSONObject(content);
                JSONObject results = jb.getJSONObject("results");
                JSONArray jsonArray = results.getJSONArray("collection1");
                List<Games> gamesList = new ArrayList<>();

                // looping through All Contacts
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject c = jsonArray.getJSONObject(i);
                    Games games = new Games();

                    games.setTAG_DESCRIPTION(c.getString("description"));
                    games.setTAG_DAY(c.getString("day"));
                    games.setTAG_DATE(c.getString("date"));
                    games.setTAG_SHOW_TIME(c.getString("show_time"));
                    games.setTAG_NETWORK(c.getString("network"));
                    games.setTAG_DESCRIPTION(c.getString("description"));


                    // Game node is JSON Object
                    // JSONObject phone = games.setTAG_GAME(c.getJSONObject("games"));
                    // String title = phone.getString(TAG_GAME_TEXT);

                    // adding contact to co++ntact list
                    gamesList.add(games);
                }
                return gamesList;
            } catch (JSONException e) {
                e.printStackTrace();

                return null;
            }
        }
           // Log.e("ServiceHandler", "Couldn't get any data from the url");




    }
