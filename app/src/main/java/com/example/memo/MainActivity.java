package com.example.memo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static RecyclerView rv;
    public static List<Memo> memoList;
    private LinearLayoutManager llm;
    public static DBHelper db;
    GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
    Call<List<Memo>> call = gitHubService.repoMemo();
    List<Memo> result;
    BackgroundTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoList = new ArrayList<>();
        rv = (RecyclerView) findViewById(R.id.list_todo);
        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        memoList = new ArrayList<>();
        db = new DBHelper(getApplicationContext(), "memo.db", null, 1);
        memoList = db.getList();
        rv.setAdapter(new RecycleAdapter(memoList));
        task = new BackgroundTask();
        /*try {
            result = call.execute().body();
        } catch (IOException e){}
        rv.setAdapter(new RecycleAdapter(result));*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog);
                final EditText taskEditText = (EditText) dialog.findViewById(R.id.task_title);
                final EditText contentEditText = (EditText) dialog.findViewById(R.id.task_content);
                dialog.setTitle("New memo");
                dialog.findViewById(R.id.add_button).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                String taskTitle = taskEditText.getText().toString();
                                String taskContent = contentEditText.getText().toString();
                                if (TextUtils.isEmpty(taskTitle)) {
                                    Toast.makeText(MainActivity.this, "You must enter a title first", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (TextUtils.isEmpty(taskContent)) {
                                    Toast.makeText(MainActivity.this, "You must enter a content first", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                db.insert(new Memo(taskTitle, taskContent));
                                memoList.clear();
                                memoList = db.getList();
                                rv.setAdapter(new RecycleAdapter(memoList));
                                dialog.dismiss();
                            }
                        }
                );
                dialog.findViewById(R.id.cancel_button).setOnClickListener(
                        new Button.OnClickListener() {
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        }
                );
                dialog.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void updateMemo(View v) {
        TextView idText = (TextView) v.findViewById(R.id.rv_id);
        String idString = idText.getText().toString();
        int rvID = Integer.parseInt(idString);
        Intent intent = new Intent(this, updateMemoActivity.class);
        intent.putExtra("rvID", rvID);
        startActivity(intent);
    }

    /*List<Memo> result = call.execute().body();
    call.enqueue(new Callback<List<Memo>>() {
        @Override
        public void onResponse(Call<List<Memo>> call, Response<List<Memo>> response) {
            if (response.isSuccess()) {

            }
        }

        @Override
        public void onFailure
    })*/
     /*Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://ec2-13-124-51-140.ap-northeast-2.compute.amazonaws.com:8000/memo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();*/
}