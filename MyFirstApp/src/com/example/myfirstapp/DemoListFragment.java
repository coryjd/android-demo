package com.example.myfirstapp;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DemoListFragment extends ListFragment
{
    private static final String[] temp = {"Cory James", "Marissa Waldman", "Quinn Brielle", "Reese Eleanor"};
    
    boolean mDualPane = false;
    int mCurCheckPosition = 0;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) 
    {
        super.onActivityCreated(savedInstanceState);
        
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, temp));
        
        //View detailsFrame = getActivity().findViewById(-1);
        //mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        
        if(savedInstanceState != null) 
        {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
        
        if(mDualPane)
        {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
        }
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Log.i("info", "Demo selected: id: " + id + " pos: " + position);
        showDetails(position);
    }
    
    private void showDetails(int index) 
    {
        mCurCheckPosition = index;
        if(mDualPane)
        {
            //TODO
        }
        else
        {
            Intent intent = new Intent();
            intent.setClass(getActivity(), MessageActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}
