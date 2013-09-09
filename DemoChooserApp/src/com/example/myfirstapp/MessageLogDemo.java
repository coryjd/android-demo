package com.example.myfirstapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MessageLogDemo extends Fragment
{
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MessageLogDemo.MESSAGE";
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("info", this.getClass().getName() + " onCreate");
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i("info", this.getClass().getName() + " onCreateView");
        
        final View view = inflater.inflate(R.layout.fragment_message_log, container, false);
        
        //Set up button control, as it doesn't work when registered from layout
        final View button = view.findViewById(R.id.messagelog_send_button);
        button.setOnClickListener(new View.OnClickListener() { 
            public void onClick(View v) {
                sendMessage(v);
            }
        });
        
        return view;
    }

    public void sendMessage(View view) 
    {
        Intent intent = new Intent(getActivity(), DisplayMessageActivity.class);
        EditText editText = (EditText) getActivity().findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
