package com.example.bas.baszwanenburg_pset4;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by Bas on 21/11/2017.
 */

public class TodoAdapter extends ResourceCursorAdapter {
    public TodoAdapter(Context context, Cursor cursor) {
        super(context, R.layout.row_todo, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("completed");
        int completed     = cursor.getInt(columnIndex);
        int columnIndex_title = cursor.getColumnIndex("title");
        String title          = cursor.getString(columnIndex_title);

        TextView textView = view.findViewById(R.id.textView);
        CheckBox checkBox = view.findViewById(R.id.checkBox);

        textView.setText(title);

        if (completed == 1) {
            checkBox.setChecked(true);}
        else {
            checkBox.setChecked(false);}
    }
}
