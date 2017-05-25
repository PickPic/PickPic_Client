package com.pickpic.Item;

import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by sekyo on 2017-05-25.
 */

public class SearchRecyclerViewItem {
    TextView textView;
    ImageButton imageButton;

    public TextView getTextView() {
        return textView;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }
}
