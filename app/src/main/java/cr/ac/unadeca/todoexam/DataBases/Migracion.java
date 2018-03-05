package cr.ac.unadeca.todoexam.DataBases;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

import cr.ac.unadeca.todoexam.DataBases.models.ToDoTable;

/**
 * Created by Freddy on 3/4/2018.
 */

@Migration(version = 1, database = ToDoDataBase.class)
public class Migracion  extends AlterTableMigration<ToDoTable>{

    public Migracion(Class<ToDoTable> table) {
        super(table);
    }

    @Override
    public void onPreMigrate(){
        super.onPreMigrate();
        addColumn(SQLiteType.INTEGER,"estado");
    }
}
