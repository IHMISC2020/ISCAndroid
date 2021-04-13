package fr.iscgroup.isc.utils;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import fr.iscgroup.isc.model.cursor.CursorLocation;

/**
 * The type Json utils.
 *
 * @author emoqu on 25/01/2021
 * @project ISC
 */
public class JsonUtils {
    /**
     * Gets cursor location from a json string
     *
     * @param json the json
     * @return the cursor location
     */
    public static CursorLocation getCursorLocation(String json) {
        if(json == null)
            return null;

        Log.e("UTILS", "On a: "+ json);

        try {
            JSONObject obj = new JSONObject(json);
            System.out.println("On a: " + obj);
            return new CursorLocation(obj.getDouble(Configuration.X),
                    obj.getDouble(Configuration.Y));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static JSONObject toJsonObject(HashMap<String, String> toJsonify) {
        JSONObject json = new JSONObject();
        try {
            for(String key : toJsonify.keySet())
                json.put(key, toJsonify.get(key));

            return json;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
