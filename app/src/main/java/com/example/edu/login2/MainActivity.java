package com.example.edu.login2;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText editNombres, editCorreo;
    Button btnAgregar;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombres = (EditText)findViewById(R.id.editNombres);
        editCorreo = (EditText)findViewById(R.id.editCorreo);
        btnAgregar = (Button)findViewById(R.id.btnAgregarUsuario);

        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent btnAgregar = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(btnAgregar);
            }
        });
    }

    public Connection conexionBD(){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc.dtds:sqlserver://192.168.0.16;databaseName=Developeru;user=edu;password=");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return  conexion;
    }

    public void agregarUsuario(){
        try{
            PreparedStatement pat = conexionBD().prepareStatement("insert into usuarios values(?,?)");
            pat.setString(1,editNombres.getText().toString());
            pat.setString(2,editNombres.getText().toString());
            pat.executeUpdate();

            Toast.makeText(getApplicationContext(),"REGISTRO AGREGADO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
        }catch(SQLException e)  {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
}

