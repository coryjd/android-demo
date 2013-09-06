package com.example.myfirstapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class DemoViewerActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("info", this.getClass().getName() + " onCreate");
        setContentView(R.layout.activity_demoviewer);
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
}
