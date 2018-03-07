package app.listview.pedor.com.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// Activity that allows user to create and add a journal entry
public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Set OnClickListener on submit button
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new SubmitOnClickListener());
    }

    // Adds entry to database
    public void addEntry(String title, String mood, String content) {
        JournalEntry entry = new JournalEntry(title, content, mood);
        EntryDatabase.getInstance(this).insert(entry);
    }

    // OnClickListener for submit button
    private class SubmitOnClickListener implements View.OnClickListener {

        // Set filled in info to database and go back to the main activity
        @Override
        public void onClick(View view) {
            EditText titleEditText = findViewById(R.id.titleInput);
            EditText moodEditText = findViewById(R.id.moodInput);
            EditText contentEditText = findViewById(R.id.contentInput);

            String title = titleEditText.getText().toString();
            String mood = moodEditText.getText().toString();
            String content = contentEditText.getText().toString();

            addEntry(title, mood, content);

            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
