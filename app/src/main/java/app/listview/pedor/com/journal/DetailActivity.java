package app.listview.pedor.com.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get info from intent
        Intent intent = getIntent();
        String title = (String) intent.getSerializableExtra("title");
        String content = (String) intent.getSerializableExtra("content");
        String mood = (String) intent.getSerializableExtra("mood");
        String timestamp = (String) intent.getSerializableExtra("timestamp");

        // Get views by id
        TextView titleView = this.findViewById(R.id.Title);
        TextView moodView = this.findViewById(R.id.Mood);
        TextView contentView = this.findViewById(R.id.Content);
        TextView timestampView = findViewById(R.id.Timestamp);

        // Set views with info
        titleView.setText(title);
        moodView.setText(mood);
        contentView.setText(content);
        timestampView.setText(timestamp);
    }
}
