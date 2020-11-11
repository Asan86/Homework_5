package asan.example.homework_5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClicListener {

    private ArrayList<String> list;
    private int position;
    Adapter adapter;
    private static final int REQUEST_CODE_ADD = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recView);
        list = new ArrayList<>();
        adapter = new asan.example.homework_5.Adapter(list, this);
        recyclerView.setAdapter(adapter);

        EditText editText = findViewById(R.id.etText);
        Button button = findViewById(R.id.btnAdd);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString().trim();
                list.add(str);
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
    }

    @Override
    public void openActivity(int position) {
        this.position = position;
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("KEY", list.get(position));
        startActivityForResult(intent, REQUEST_CODE_ADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            String string = data.getStringExtra(MainActivity2.KEY);
            list.set(position, string);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "succes", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "failer", Toast.LENGTH_SHORT).show();
        }
    }
}