package sg.edu.rp.c346.s19024292.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText task;
    Button add, clear, delete;
    ListView TaskList;
    ArrayList<String> TaskArray;
    ArrayAdapter<String> adapter;
    Spinner spinnerChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = findViewById(R.id.editText);
        add = findViewById(R.id.button);
        clear = findViewById(R.id.button2);
        TaskList = findViewById(R.id.listView);
        TaskArray = new ArrayList<String>();
        spinnerChoice = findViewById(R.id.spinner);
        delete = findViewById(R.id.button3);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TaskArray);

        TaskList.setAdapter(adapter);

        spinnerChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        task.getText().clear();
                        task.setHint("Type in a new task here");
                        delete.setEnabled(false);
                        break;
                    case 1 :
                        task.getText().clear();
                        task.setHint("Type in the index of the task to be removed");
                        delete.setEnabled(true);
                        add.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addTask = task.getText().toString();
                TaskArray.add(addTask);
                adapter.notifyDataSetChanged();
                task.getText().clear();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = Integer.parseInt(task.getText().toString());
                if (TaskArray.isEmpty()){
                    Toast.makeText(getApplicationContext(), "You do not have any task to remove", Toast.LENGTH_SHORT).show();
                }
                else if (index > TaskArray.size()){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
                else {
                    TaskArray.remove(index - 1);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.getText().clear();
                TaskArray.clear();
                adapter.notifyDataSetChanged();
                task.getText().clear();
            }
        });
    }
}
