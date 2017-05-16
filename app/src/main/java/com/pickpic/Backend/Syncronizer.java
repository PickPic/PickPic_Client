package com.pickpic.Backend;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by sekyo on 2017-05-11.
 */

public class Syncronizer {


    public static void synchronize(Context context){
        ArrayList<String> localStorage = LocalImageManager.getAllImagePath(context);
        ArrayList<String> localDB = null;
        TagDBManager tagDBManager = new TagDBManager(context);
        int i = 0;
        int j = 0;

        while(i < localStorage.size() || j < localDB.size()){
            if(localStorage.get(i).equals(localDB.get(j))){
                i++;
                j++;
            } else {
                //DB에서 localDB.get(j)에 해당하는 항목 지움
                j++;
            }
            if(j == localDB.size()){
                AutoTagGenerator.autoTagGenerate(context, localStorage.get(i));
            }
        }
    }
}
