package sg.edu.ro.c346.id16046530.c347_p12_problemstatement_wear;

import android.app.RemoteInput;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);


        CharSequence reply = null;
        Intent intent = getIntent();
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            reply = remoteInput.getCharSequence("status");
        }

        if (reply != null) {
            Toast.makeText(ReplyActivity.this, "You have indicated: " + reply, Toast.LENGTH_LONG).show();

            String completed = reply.toString();

            // if reply is equals to "Completed", remove the list from the database.
            if(completed.equalsIgnoreCase("Completed")){

                DBHelper dbh = new DBHelper(ReplyActivity.this);
                int size = dbh.getTasks().size();
                dbh.deleteTask(dbh.getTasks().get(size-1).getId());
                dbh.close();

            }
            finish();

        }
    }
}