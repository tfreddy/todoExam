package cr.ac.unadeca.todoexam.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.List;

import cr.ac.unadeca.todoexam.DataBases.models.ToDoTable;
import cr.ac.unadeca.todoexam.R;
import cr.ac.unadeca.todoexam.subclases.TodoViewHolder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista;
    private static Context QuickContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuickContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividad= new Intent(getApplicationContext(),FormularioActivityExam.class);
                actividad.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(actividad);

            }
        });
        lista=findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));

        List<ToDoTable>info=SQLite.select().from(ToDoTable.class).queryList();
        lista.setAdapter(new ToDoAdapter(info));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            List<ToDoTable> info= SQLite.select().from(ToDoTable.class).queryList();
            lista.setAdapter(new ToDoAdapter(info));
        }

        return super.onOptionsItemSelected(item);
    }

    public static class ToDoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
        private final List<ToDoTable> listTodoTable;
        private final LayoutInflater inflater;

        public ToDoAdapter(List<ToDoTable> listTodoTables) {
            this.inflater = LayoutInflater.from(QuickContext);
            this.listTodoTable = listTodoTables;
        }

        @Override
        public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//cuando se crea la vista establecemos el disenio a usar, enlace con un disenio
            View view = inflater.inflate(R.layout.objeto, parent, false);
            return new TodoViewHolder(view);
        }

        public void animateTo(List<ToDoTable> models) {//animar la informacion
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<ToDoTable> newModels) {
            for (int i = listTodoTable.size() - 1; i >= 0; i--) {
                final ToDoTable model = listTodoTable.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<ToDoTable> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final ToDoTable model = newModels.get(i);
                if (!listTodoTable.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<ToDoTable> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final ToDoTable model = newModels.get(toPosition);
                final int fromPosition = listTodoTable.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public ToDoTable removeItem(int position) {
            final ToDoTable model = listTodoTable.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, ToDoTable model) {
            listTodoTable.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final ToDoTable model = listTodoTable.remove(fromPosition);
            listTodoTable.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onBindViewHolder(final TodoViewHolder holder, final int position) {//bind=enlazar
            final ToDoTable current = listTodoTable.get(position);
            holder.html.setHtml(ActividadaString(current),
                    new HtmlResImageGetter(holder.html));
            holder.html.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(current.estado < 2){
                        current.estado=2;

                    }else {
                        current. estado=1;
                    }
                    notifyDataSetChanged();
                }
            });
            holder.borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current.delete();
                    removeItem(position);
                    notifyDataSetChanged();
                }
            });
        }



        private String ActividadaString(ToDoTable todo){

            String color = "#000000";
            if(todo.estado == 2) {
                color = "#1d9100";
            }

            String html = "<a><big><b> <font color=\""+ color +"\">" + todo.nombre + " " + todo.apellido + "</b></big>";
            html+= "<br>" + "<p>" + todo.cedula + ", " + todo.codigoEmple + ", " +todo.departamento +
                      ", " + todo.tel + "</p>" + "</a>";

            return html;
        }


        @Override
        public int getItemCount() {
            return listTodoTable.size();
        }
    }








}


