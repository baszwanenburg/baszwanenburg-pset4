package com.example.bas.baszwanenburg_pset4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Button;
import android.database.Cursor;

public class MainActivity extends AppCompatActivity {
    private TodoAdapter adapter;
    private TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new OnItemCLickListener());
        listView.setOnItemLongClickListener(new OnItemLongClickListener());

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new AddButtonClickListener());

        db = TodoDatabase.getInstance(this);
        Cursor c = db.selectAll();
        adapter = new TodoAdapter(this, c);
        listView.setAdapter(adapter);
    }

    private void updateData() {
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
    }

    private class AddButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText editText = findViewById(R.id.editText);

            db.insert(editText.getText().toString(), 0);
            editText.setText("");

            updateData();
        }
    }

    private class OnItemCLickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            CheckBox checkBox = view.findViewById(R.id.checkBox);

            int completed = 1;
            if (checkBox.isChecked()) {
                completed = 0;}

            db.update(j, completed);
            updateData();
        }
    }

    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            db.delete(l);
            updateData();

            return true;
        }
    }
}