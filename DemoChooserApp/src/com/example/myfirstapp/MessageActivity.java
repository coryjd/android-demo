package com.example.myfirstapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MessageActivity extends Activity
{
    public final static String EXTRA_MESSAGE = "com.example/myfirstapp.MESSAGE";
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        switch(item.getItemId()){
        case R.id.action_search:
            //openSearch();
            DialogFragment newFragment = new SearchPlaceholderDialog();
            newFragment.show(getFragmentManager(), "search-placeholder");
            Log.i("info", "Opening search!");
            return true;
        case R.id.action_settings:
            //openSettings();
            Log.i("info", "Opening settings!");
            return true;
        default: return super.onOptionsItemSelected(item);
        }
    }
    
    public void sendMessage(View view) 
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
