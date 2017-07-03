package com.example.memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateMemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_memo);

        final Intent intent = getIntent();
        final EditText editTitle = (EditText) findViewById(R.id.update_title);
        final EditText editContent = (EditText) findViewById(R.id.update_content);

        findViewById(R.id.update_button).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String updatedTitle = editTitle.getText().toString();
                        String updatedContent = editContent.getText().toString();
                        if (TextUtils.isEmpty(updatedTitle)) {
                            Toast.makeText(updateMemoActivity.this, "You must enter a title first", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(updatedContent)) {
                            Toast.makeText(updateMemoActivity.this, "You must enter a content first", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        int rvID = intent.getIntExtra("rvID", 1);
                        Memo memo = new Memo(updatedTitle, updatedContent, rvID);
                        MainActivity.db.update(memo);
                        MainActivity.memoList.clear();
                        MainActivity.memoList = MainActivity.db.getList();
                        MainActivity.rv.setAdapter(new RecycleAdapter(MainActivity.memoList));
                        finish();
                    }
                }
        );

        findViewById(R.id.cancel_update).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        findViewById(R.id.delete_button).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int rvID = intent.getIntExtra("rvID", 1);
                        MainActivity.db.delete(rvID);
                        MainActivity.memoList.clear();
                        MainActivity.memoList = MainActivity.db.getList();
                        MainActivity.rv.setAdapter(new RecycleAdapter(MainActivity.memoList));
                        finish();
                    }
                }
        );
    }
}
