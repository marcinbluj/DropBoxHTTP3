package com.sda.bluj.marcin.dropboxhttp3;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<DropboxFile> listAdapter;

    private GetFilesListTask getFilesListTask;

    private ListView listView;

    private String path;

    private TextView currentPath;

    private String currentPathString = "https://www.dropbox.com/home";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentPath = (TextView) findViewById(R.id.current_path);
        listView = (ListView) findViewById(R.id.list);
        listAdapter = new ArrayAdapter<DropboxFile>(this, android.R.layout.simple_dropdown_item_1line);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DropboxFile file = (DropboxFile) parent.getItemAtPosition(position);

                if (file.getTag().equals("folder")) {
                    path = file.getPath();
                    getFiles(path);
                    currentPath.setText(String.valueOf(currentPathString+path));
                } else {
                    Toast.makeText(MainActivity.this, file.getName(), Toast.LENGTH_LONG).show();
                    downloadFile(file);
                }
            }
        });

        currentPath.setText(String.valueOf(currentPathString));
        getFiles("");
    }

    private void downloadFile(DropboxFile file) {
//        new DownloadFileTask(this).execute(file);
    }

    @Override
    public void onBackPressed() {
        if (path.equals("")) {
            super.onBackPressed();
        } else {
            int index = path.lastIndexOf("/", path.length());
            path = path.substring(0, index);
            getFiles(path);
            currentPath.setText(String.valueOf(currentPathString+path));
//            Log.i("TEST", String.valueOf(index));
//            Log.i("TEST", path);
        }

    }

    private void getFiles(String path) {
        listAdapter.clear();
        getFilesListTask = new GetFilesListTask(path);
        getFilesListTask.setMainActivity(this);
        getFilesListTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (getFilesListTask != null) {
            getFilesListTask.setMainActivity(null);
        }
    }

    public void setFiles(List<DropboxFile> files) {
        listAdapter.addAll(files);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        new AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton("Ok", null)
        .show();
    }
}
