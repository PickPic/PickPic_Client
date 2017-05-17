package com.pickpic.Backend;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by sekyo on 2017-05-11.
 */

public class Syncronizer {


    public static void synchronize(Context context){

        TagDBManager tagDBManager = new TagDBManager(context);
        ArrayList<String> localImages = LocalImageManager.getAllImagePath(context);
        ArrayList<String> TagDB = null; // TagDB에서 모든 이미지 목록 가져오기

        int i = 0;
        int j = 0;

        while(i < localImages.size() || j < TagDB.size()){
            if(localImages.get(i).equals(TagDB.get(j))){
                i++;
                j++;
            } else {
                tagDBManager.removeImage(localImages.get(i));
                j++;
            }
            if(j == TagDB.size()){
                AutoTagGenerator.autoTagGenerate(context, localImages.get(i));
            }
        }
    }
}
