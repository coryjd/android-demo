package com.example.myfirstapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class DemoViewerActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("info", this.getClass().getName() + " onCreate");
        
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            finish();
            return;
        }
        
        if(savedInstanceState == null)
        {
            Log.i("info", this.getClass().getName() + " Creating Demo Fragment");
            
            //TODO: Get correct demo fragment to start
            Fragment demoFragment = new MessageLogDemo();
            
            getFragmentManager().beginTransaction().add(android.R.id.content, demoFragment).commit();
        }
        else
        {
            Log.i("info", this.getClass().getName() + " savedInstanceState not null?");
        }
    }

}
