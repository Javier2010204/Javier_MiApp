package com.javier.android.frasesapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import com.javier.android.frasesapp.QuotesDataSource.ColumnQuotes;

public class MainActivity extends ListActivity {

    public final static int ADD_REQUEST_CODE = 1;

    private QuotesDataSource dataSource;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.list_content);

        dataSource = new QuotesDataSource(this);

        adapter = new SimpleCursorAdapter(
          this,
                android.R.layout.two_line_list_item,
                dataSource.getAllQuotes(),
                new String[]{ColumnQuotes.BODY_QUOTES, QuotesDataSource.ColumnQuotes.AUTHOR_QUOTES},
                new int[]{android.R.id.text1, android.R.id.text2},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );

        setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch(id){
            case R.id.action_add:
                initForm();
                return true;
            case R.id.action_delete:
                //delete
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String body = data.getStringExtra("body");
                String author = data.getStringExtra("author");

                dataSource.saveQuotesRow(body, author);
                adapter.changeCursor(dataSource.getAllQuotes());
            }
        }
    }

    private void initForm(){
        Intent intent = new Intent(this, Form.class);
        startActivityForResult(intent, ADD_REQUEST_CODE);
    }
}
