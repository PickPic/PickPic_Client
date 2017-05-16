package com.pickpic.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.pickpic.Backend.AutoTagGenerator;
import com.pickpic.Backend.LocalImageManager;
import com.pickpic.Item.DirectoryTabListViewItem;
import com.pickpic.Item.GridViewItem;
import com.pickpic.R;

import java.util.ArrayList;



public class TestActivity extends AppCompatActivity {
    Button getImageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getImageBtn = (Button)findViewById(R.id.get_image_btn);
        getImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                String path =  AutoTagGenerator.getFilePath(this,uri);
                AutoTagGenerator.autoTagGenerate(this,path);
                ImageView imageView = (ImageView) findViewById(R.id.imageview);
                imageView.setImageBitmap(LocalImageManager.getGridViewItem(this, path).getThumbnail());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

