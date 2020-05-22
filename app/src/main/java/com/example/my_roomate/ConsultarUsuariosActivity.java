package com.example.my_roomate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.widget.EditText;
import android.view.View;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.Toast;
import com.example.my_roomate.OpenHelper.SQLiteOpenHelper;
import com.example.my_roomate.Utils.utils;

public class ConsultarUsuariosActivity extends AppCompatActivity {
    EditText campoId,campoCorreo,campoContraseña,campoApellido;
    SQLiteOpenHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);
        conn= new  SQLiteOpenHelper (getApplicationContext(),"roomate-app",null,1);

        campoId= (EditText) findViewById(R.id.documentoId);
        campoCorreo= (EditText) findViewById(R.id.campoNombreConsulta);
        campoContraseña= (EditText) findViewById(R.id.campoApellido);
        campoApellido= (EditText) findViewById(R.id.campoTelefonoConsulta);
    }
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnConsultar:
             consultar();
              
                break;
        }

    }

    private void consultar() {
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={campoId.getText().toString()};
        String[] campos={utils.email_user,utils.password_user,utils.password_user};
        try {
            Cursor cursor = db.query(utils.table_user,campos,utils.names_user+"=?",parametros,null,null,null,null);
            cursor.moveToFirst();
            campoCorreo.setText(cursor.getString(0));
            campoContraseña.setText(cursor.getString(1));
            campoApellido.setText(cursor.getString(2));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }


    }

    private void limpiar() {
        campoCorreo.setText("");
        campoContraseña.setText("");
        campoApellido.setText("");
    }
}
