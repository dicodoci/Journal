package app.listview.pedor.com.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView entriesList = findViewById(R.id.journalListView);
        entriesList.setOnItemClickListener(new EntryClickListener());
        entriesList.setOnItemLongClickListener(new EntryLongClickListener());
        FloatingActionButton AddButton = findViewById(R.id.newEntryButton);
        AddButton.setOnClickListener(new newEntryOnClickListener());

        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = EntryDatabase.selectAll(db);
        adapter = new EntryAdapter(this, R.layout.entry_row, cursor);
        entriesList.setAdapter(adapter);

    }

    private void updateData() {
        Cursor newCursor = EntryDatabase.selectAll(db);
        adapter.swapCursor(newCursor);
    }

    protected void onResume() {
        super.onResume();
        updateData();
    }

    private class newEntryOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intentInput = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intentInput);
        }
    }

    private class EntryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String mood = cursor.getString(cursor.getColumnIndex("mood"));
            String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("mood", mood);
            intent.putExtra("timestamp", timestamp);
            startActivity(intent);
        }
    }

    private class EntryLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            final long id = cursor.getLong(cursor.getColumnIndex("_id"));
            EntryDatabase.delete(id);
            updateData();
            return false;
        }
    }
}
