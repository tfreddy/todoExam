package cr.ac.unadeca.todoexam.DataBases;

/**
 * Created by Freddy on 3/4/2018.
 */

import com.raizlabs.android.dbflow.annotation.Database;
@Database(name = ToDoDataBase.NAME, version = ToDoDataBase.VERSION)
public class ToDoDataBase {

    public static final String NAME = "todoExam";

    public static final int VERSION = 1;
}
