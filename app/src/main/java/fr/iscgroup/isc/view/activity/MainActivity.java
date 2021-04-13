package fr.iscgroup.isc.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

import fr.iscgroup.isc.R;
import fr.iscgroup.isc.factory.Factory;
import fr.iscgroup.isc.factory.connexion.ConnexionFactory;
import fr.iscgroup.isc.state.CursorState;
import fr.iscgroup.isc.state.factory.CursorStateFactory;
import fr.iscgroup.isc.utils.Configuration;
import fr.iscgroup.isc.view.graphics.tasks.ServerConnectionTask;
import fr.iscgroup.isc.view.listener.BasicClickListener;
import fr.iscgroup.isc.view.listener.CursorClickListener;

public class MainActivity extends AppCompatActivity {
    private ConnexionFactory connexion;
    private Timer connect = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view elements
        Button basic_btn = this.findViewById(R.id.basic_btn);
        Button captor_btn = this.findViewById(R.id.captor_btn);

        findViewById(R.id.background);

        // Connexion to the server
        this.connect.schedule(new ServerConnectionTask(this), 1, Configuration.TIMEOUT);

        //this.connexion = Factory.getConnexionFactory(this);
        //this.connexion.connect(Configuration.IP, Configuration.PORT, Configuration.TIMEOUT);

        // Setup the buttons listener
        captor_btn.setOnClickListener(new CursorClickListener(this, findViewById(R.id.background)));
        basic_btn.setOnClickListener(new BasicClickListener(this, findViewById(R.id.background)));
    }
}