package com.example.myfirstapp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import android.app.Fragment;
import android.util.Log;

/*
 * Static structure for storing the available demos. Could be made dynamic in the future.
 */
public class DemoList
{
    public final static String EXTRAS_DEMO_NAME = "com.example.myfirstapp.DemoChooser.demo_name";
    
    private final static Map<String, Class<? extends Fragment>> demoMap = new HashMap<String, Class<? extends Fragment>>();
    
    static {
        demoMap.put("MessageLogDemo", MessageLogDemo.class);
        demoMap.put("MessageLogDemo2", MessageLogDemo.class);
    }
    
    public static String[] getDemoTitles()
    {
        return demoMap.keySet().toArray(new String[demoMap.keySet().size()]);
    }
    
    public static Fragment getDemo(String demoName)
    {
        Class<? extends Fragment> klass = demoMap.get(demoName);
        Fragment f = null;
        //TODO: Wow, this is ugly, clean this up
        try
        {
            Constructor<? extends Fragment> constructor = klass.getConstructor();
            f =  constructor.newInstance();
        } 
        catch (NoSuchMethodException e)
        {
            Log.e("DemoChooser", "Unable to create demo: " + demoName, e);
        }
        catch (IllegalArgumentException e)
        {
            Log.e("DemoChooser", "Unable to create demo: " + demoName, e);
        } 
        catch (java.lang.InstantiationException e)
        {
            Log.e("DemoChooser", "Unable to create demo: " + demoName, e);
        }
        catch (IllegalAccessException e)
        {
            Log.e("DemoChooser", "Unable to create demo: " + demoName, e);
        }
        catch (InvocationTargetException e)
        {
            Log.e("DemoChooser", "Unable to create demo: " + demoName, e);
        }
        
        return f;
    }
}
