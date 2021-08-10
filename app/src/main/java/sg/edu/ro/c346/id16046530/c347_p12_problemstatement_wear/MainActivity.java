package sg.edu.ro.c346.id16046530.c347_p12_problemstatement_wear;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView lvReminders;
    Button btnAdd;
    ArrayAdapter<Task> aa;
    ArrayList<Task> al;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvReminders = (ListView) findViewById(R.id.lvReminders);
        btnAdd = (Button) findViewById(R.id.btnUpdate);
        dbh = new DBHelper(MainActivity.this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        al = new ArrayList<Task>();
        if (!dbh.getTasks().isEmpty()) {
            al = dbh.getTasks();
        }

        aa = new ArrayAdapter<Task>(getApplicationContext(), android.R.layout.simple_list_item_1, al);
        aa.notifyDataSetChanged();
        lvReminders.setAdapter(aa);


        // intent to EditActivity class.
        lvReminders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Task selectedTask = al.get(position);
                Intent i = new Intent(getBaseContext(), EditActivity.class);
                i.putExtra("data", selectedTask);
                startActivity(i);

            }
        });
    }
}