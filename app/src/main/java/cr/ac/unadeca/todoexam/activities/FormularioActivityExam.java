package cr.ac.unadeca.todoexam.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cr.ac.unadeca.todoexam.DataBases.models.ToDoTable;
import cr.ac.unadeca.todoexam.R;


public class FormularioActivityExam extends AppCompatActivity {

    private TextView lblNombre;
    private TextView lblApellido;
    private TextView lblCedula;
    private TextView lblCodigoDeEmple;
    private TextView lblDepartamento;
    private TextView lblTel;

    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtCedula;
    private EditText txtCodigoDeEmple;
    private EditText txtDepartamento;
    private EditText txtTel;

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_exam);

        lblNombre = findViewById(R.id.lblNombre);
        lblApellido = findViewById(R.id.lblApellido);
        lblCedula = findViewById(R.id.lblCedula);
        lblCodigoDeEmple = findViewById(R.id.lblCodigoDeEmple);
        lblDepartamento = findViewById(R.id.lblDepartamento);
        lblTel = findViewById(R.id.lblTel);

        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCedula = findViewById(R.id.txtCedula);
        txtCodigoDeEmple = findViewById(R.id.txtCodigoDeEmple);
        txtDepartamento = findViewById(R.id.txtDepartamento);
        txtTel = findViewById(R.id.txtTel);

        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

    }
    private boolean validacion(){
        boolean send = true;
        if(txtNombre.getText().toString().isEmpty()){
            send= false;
        }
        if(txtApellido.getText().toString().isEmpty()){
            send= false;
        }
        if(txtCedula.getText().toString().isEmpty()){
            send= false;
        }
        if(txtCodigoDeEmple.getText().toString().isEmpty()){
            send= false;
        }
        if(txtDepartamento.getText().toString().isEmpty()){
            send= false;
        }
        if(txtTel.getText().toString().isEmpty()){
            send= false;
        }
        return  send;

    }
    private void guardar(){
        if (validacion()){
            ToDoTable registro = new ToDoTable();
            registro.nombre = txtNombre.getText().toString();
            registro.apellido= txtApellido.getText().toString();
            registro.cedula=Integer.parseInt(txtCedula.getText().toString());
            registro.codigoEmple=Integer.parseInt(txtCodigoDeEmple.getText().toString());
            registro.departamento=txtDepartamento.getText().toString();
            registro.tel=Integer.parseInt(txtTel.getText().toString());

            registro.save();
            finish();

        }else{
            Toast.makeText(this, getResources().getString(R.string.error_valid), Toast.LENGTH_SHORT).show();//llamada estatica llamar un objeto sin hacer uno nuevo
        }
    }
}
