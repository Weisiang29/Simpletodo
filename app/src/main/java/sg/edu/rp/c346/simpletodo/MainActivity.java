package sg.edu.rp.c346.simpletodo;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    Button btnClear;
    Button btnDelete;
    EditText editTask;
    ListView lsTask;
    ArrayList<String> taskArray;
    ArrayAdapter adapter;
    Spinner spnTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd=findViewById(R.id.btnAdd);
        btnDelete=findViewById(R.id.btnDelete);
        taskArray =new ArrayList<String>();
        btnClear=findViewById(R.id.btnClear);
        editTask=findViewById(R.id.edittextTask);
        spnTask=findViewById(R.id.spinnerTask);
        lsTask=findViewById(R.id.taskList);
        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,taskArray);
        lsTask.setAdapter(adapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String output= editTask.getText().toString();
                taskArray.add(output);
                adapter.notifyDataSetChanged();
            }
        });
btnDelete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String indexString =editTask.getText().toString();
        int index=Integer.parseInt(indexString);
        if(taskArray.isEmpty()){
            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
        }
        else{
                if(index>taskArray.size()&&index<taskArray.size()){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }
                else{
                    taskArray.remove(index);
                    adapter.notifyDataSetChanged();
                }

        }


    }
});

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskArray.clear();
                adapter.notifyDataSetChanged();

            }
        });

        spnTask.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                switch(i){
                    case 0:{
                        editTask.setHint("Add a new task");
                            btnDelete.setEnabled(false);
                            btnAdd.setEnabled(true);
                        break;
                    }
                    case 1:{
                        editTask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}