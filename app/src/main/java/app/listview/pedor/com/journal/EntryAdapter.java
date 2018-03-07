package app.listview.pedor.com.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

// Adapter for the entry list in the main activity
public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, int layout, Cursor c) {
        super(context, layout, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
        String mood = cursor.getString(cursor.getColumnIndex("mood"));

        TextView titleView = view.findViewById(R.id.title);
        TextView timestampView = view.findViewById(R.id.timestamp);
        TextView moodView = view.findViewById(R.id.mood);

        titleView.setText(title);
        timestampView.setText(timestamp);
        moodView.setText(mood);
    }
}
