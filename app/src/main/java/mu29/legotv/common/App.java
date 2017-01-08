package mu29.legotv.common;

import android.app.Application;

import mu29.legotv.common.flux.Dispatcher;

/**
 * InJung Chung
 */

public class App extends Application {

    private Dispatcher dispatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        dispatcher = new Dispatcher();
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

}
