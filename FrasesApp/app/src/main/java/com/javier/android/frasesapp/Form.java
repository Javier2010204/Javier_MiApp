package com.javier.android.frasesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Form extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        Button btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        if(v.getId() == R.id.btnGuardar){
            EditText quoteField = (EditText) findViewById(R.id.quoteField);
            EditText authorField = (EditText) findViewById(R.id.authorField);

            Intent backData = new Intent();
            backData.putExtra("body", quoteField.getText().toString());
            backData.putExtra("author", authorField.getText().toString());
            setResult(RESULT_OK, backData);
        }else{
            setResult(RESULT_CANCELED);
        }
        finish();
    }
}
