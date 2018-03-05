package cr.ac.unadeca.todoexam.DataBases.models;



import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cr.ac.unadeca.todoexam.DataBases.ToDoDataBase;

/**
 * Created by Freddy on 3/4/2018.
 */
@Table(database = ToDoDataBase.class)
public class ToDoTable extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String nombre;

    @Column
    public String apellido;

    @Column
    public int cedula;

    @Column
    public int codigoEmple;

    @Column
    public String departamento;

    @Column
    public int tel;


    @Column
    public int estado;


}
