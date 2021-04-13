package fr.iscgroup.isc.view.graphics.tasks;

import java.util.TimerTask;

/**
 * @author emoqu on 02/02/2021
 * @project ISC
 */
public class PointerUpdateTask extends TimerTask {
    private Runnable toDo;

    public PointerUpdateTask(Runnable toDo) {
        this.toDo = toDo;
    }

    @Override
    public void run() {
        this.toDo.run();
    }
}
