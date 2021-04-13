package fr.iscgroup.isc;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import fr.iscgroup.isc.model.cursor.CursorLocation;
import fr.iscgroup.isc.utils.JsonUtils;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("fr.iscgroup.isc", appContext.getPackageName());
    }

    @Test
    public void json() {
        String json = "{\"X\":0.5,\"Y\":0.5";
        CursorLocation loc = new CursorLocation(0, 0);
        loc = JsonUtils.getCursorLocation(json);
        assertEquals(0.5, loc.getX());
    }
}