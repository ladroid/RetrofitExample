package com.example.lado.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    CheckBox checkId;
    CheckBox checkAll;
    TextView output;
    EditText enter;
    Button accept;
    private static String URL = "http://YOUR_HOST:3000/";
    private int Id;
    //private Message m;
    private List<String> s;
    private ViewGroup parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkId = (CheckBox) findViewById(R.id.checkID);
        checkAll = (CheckBox) findViewById(R.id.checkAll);
        output = (TextView) findViewById(R.id.Output);
        enter = (EditText) findViewById(R.id.enter);
        accept = (Button) findViewById(R.id.accept);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkId.isChecked()) {
                    startingId();
                }
                else {
                    Toast.makeText(MainActivity.this, "You don't choose anything ",
                            Toast.LENGTH_SHORT).show();
                }

                if (checkAll.isChecked()) {
                    startingAll();
                }
                else {
                    Toast.makeText(MainActivity.this, "You don't choose anything ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void startingId() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MessageAPI messageAPI = retrofit.create(MessageAPI.class);

        Id = Integer.parseInt(enter.getText().toString());

        Call<Message> messageCall = messageAPI.getUserById(Id);
        messageCall.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message post = response.body();
                if(response.isSuccessful()) {
                    output.setText(post.getFirstName() + " " + post.getEmail());
                }
                else
                {
                    Log.d("CODE1", "CODE IS " + response.code());
                    Toast.makeText(MainActivity.this, "CODE IS " +
                            String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("FAIL1", "Fail" + t);
                Toast.makeText(MainActivity.this, "FAIL",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startingAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MessageAPI messageAPI = retrofit.create(MessageAPI.class);

       Call<Message> call = messageAPI.message();

       call.enqueue(new Callback<Message>() {
           @Override
           public void onResponse(Call<Message> call, Response<Message> response) {
               Message m = response.body();
               if(response.isSuccessful()) {
                   s.add(m.getEmail());
                   for(int i =0; i<s.size(); i++)
                   {
                       Log.e("WARN", s.get(i));
                       output.setText(s.get(i));
                   }
               }
               else {
                   Log.d("CODE2", "CODE IS " + response.code());
                   Toast.makeText(MainActivity.this, "CODE IS " +
                           String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<Message> call, Throwable t) {
               Log.d("FAIL2", "Fail" + t);
               Toast.makeText(MainActivity.this, "FAIL",
                       Toast.LENGTH_SHORT).show();
           }
       });
    }
}
