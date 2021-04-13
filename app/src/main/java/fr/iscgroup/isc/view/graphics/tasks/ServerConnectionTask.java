package fr.iscgroup.isc.view.graphics.tasks;

import android.content.Context;

import java.util.TimerTask;

import fr.iscgroup.isc.factory.Factory;
import fr.iscgroup.isc.utils.Configuration;

/**
 * @author emoqu on 15/02/2021
 * @project ISC
 */
public class ServerConnectionTask extends TimerTask {
    private Context context;

    public ServerConnectionTask(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        if(!Factory.getConnexionFactory(this.context).isConnect())
            Factory.getConnexionFactory(this.context).connect(
                    Configuration.IP, Configuration.PORT, Configuration.TIMEOUT);
    }
}
