package com.example.myfirstapp;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DemoListFragment extends ListFragment
{
    private boolean mDualPane;
    
    private int currentDemo;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("info", this.getClass().getName() + " onCreate");
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) 
    {
        super.onActivityCreated(savedInstanceState);
        Log.i("info", this.getClass().getName() + " onActivityCreated");
        
        Log.i("info", this.getClass().getName() + " Creating ListAdapter with " + DemoList.getDemoTitles().length + " items.");
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, DemoList.getDemoTitles()));
        
        View detailsFrame = getActivity().findViewById(R.id.demoview);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        
        Log.i("info", this.getClass().getName() + " mDualPane = " + mDualPane);
        
        if(savedInstanceState != null) 
        {
            currentDemo = savedInstanceState.getInt("curChoice");
        }
        
        if(mDualPane)
        {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(currentDemo);
        }
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", currentDemo);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Log.i("info", "Demo selected: id: " + id + " pos: " + position);
        Log.i("info", "Item at position: " + l.getItemAtPosition(position));
        
        if(!mDualPane || currentDemo != position)
        {
            showDetails(position);
        }
    }
    
    private void showDetails(int index)
    {
        String demoName = (String) getListView().getItemAtPosition(index);
        
        Log.i("info", "Showing demo: " + demoName);
        
        currentDemo = index;
        
        if(mDualPane)
        {
            getListView().setItemChecked(index, true);
            
            Fragment demoFragment = DemoList.getDemo(demoName);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setBreadCrumbTitle("Demo:" + demoName);
            ft.setBreadCrumbShortTitle("Demo:" + demoName);
            ft.replace(R.id.demoview, demoFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
        else
        {
            Intent intent = new Intent();
            intent.setClass(getActivity(), DemoViewerActivity.class);
            intent.putExtra(DemoList.EXTRAS_DEMO_NAME, demoName);
            startActivity(intent);
        }
    }
}
