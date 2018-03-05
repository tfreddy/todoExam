package cr.ac.unadeca.todoexam.DataBases;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Freddy on 3/4/2018.
 */

public class ToDoApp extends Application {
   @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

}
