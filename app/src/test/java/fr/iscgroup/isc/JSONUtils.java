package fr.iscgroup.isc;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import fr.iscgroup.isc.model.cursor.CursorLocation;
import fr.iscgroup.isc.utils.Configuration;
import fr.iscgroup.isc.utils.JsonUtils;

import static org.junit.Assert.assertEquals;

/**
 * @author emoqu on 15/02/2021
 * @project ISC
 */
public class JSONUtils {

    @Test
    public void testJson(){
        String json = "{\"X\": 0.5,\"Y\": 0.5}";
        CursorLocation loc = new CursorLocation(0, 0);
        loc = JsonUtils.getCursorLocation(json);
        assertEquals(new CursorLocation(0.5,0.5), loc);
    }
}
