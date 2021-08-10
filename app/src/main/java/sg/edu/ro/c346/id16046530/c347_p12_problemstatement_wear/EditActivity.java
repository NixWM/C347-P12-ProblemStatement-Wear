package sg.edu.ro.c346.id16046530.c347_p12_problemstatement_wear;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    Button btnUpdate, btnDelete;
    EditText etName, etDesc, etTime;
    Task data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDes);

        Intent intent = getIntent();
        data = (Task) intent.getSerializableExtra("data");

        etName.setText(data.getTitle());
        etDesc.setText(data.getDescription());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteTask(data.getId());
                dbh.close();
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(etName.getText().toString());
                data.setDescription(etDesc.getText().toString());
                dbh.updateTask(data);
                dbh.close();
                finish();
            }
        });
    }
}