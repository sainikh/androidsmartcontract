package com.example.nftmint;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class upload extends AppCompatActivity {
    TextInputEditText image_url,image_desc,image_name;
    Button b1,b2;
    String file_dir = "myfiles";
    String file_name = "sai.json";
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload2);
        b1 = (Button)findViewById(R.id.create_file);
        b2 = (Button)findViewById(R.id.download_file);

        file = new File(getExternalFilesDir(file_dir), file_name);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_url = (TextInputEditText) findViewById(R.id.url);
                image_desc = (TextInputEditText) findViewById(R.id.desc);
                image_name = (TextInputEditText) findViewById(R.id.name);


                String img_url = image_url.getText().toString();
                String img_desc = image_desc.getText().toString();
                String img_name = image_name.getText().toString();


                file.delete();

                 if (!file.exists()) {
                    try {
                        file.createNewFile();
                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bf = new BufferedWriter(fw);
                      /*  bf.write("{" +
                                "'name': '" + img_name + "'," +
                                " 'image' : 'ipfs://"+ img_url +"' " +
                                "'description' : '" + img_desc + "'" +
                                "}");*/
                             bf.write("{" +
                                "\"name\": \"" + img_name + "\"," +
                                " \"image\" : \"ipfs://"+ img_url +"\" ," +
                                "\"description\" : \"" + img_desc + "\"" +
                                "}");
                        bf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    b2.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View view)
        {
            DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
            downloadManager.addCompletedDownload(file.getName(), file.getName(), true, "application/json",file.getAbsolutePath(),file.length(),true);
      /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // You can add more columns.. Complete list of columns can be found at
                // https://developer.android.com/reference/android/provider/MediaStore.Downloads
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.Downloads.TITLE, file.getName());
                contentValues.put(MediaStore.Downloads.DISPLAY_NAME, file.getName());
                contentValues.put(MediaStore.Downloads.MIME_TYPE,"application/json");
                contentValues.put(MediaStore.Downloads.SIZE, file.length());

                // If you downloaded to a specific folder inside "Downloads" folder
                contentValues.put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS + File.separator + "Temp");

                // Insert into the database
                ContentResolver database = getContentResolver();
                database.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
                Toast.makeText(getApplicationContext(),""+file.getName()+" "+file.length()+" ",Toast.LENGTH_SHORT).show();
            } else {
                DownloadManager downloadManager = (DownloadManager) upload.this.getSystemService(Context.DOWNLOAD_SERVICE);
                if (downloadManager != null) {
                    downloadManager.addCompletedDownload(file.getName(), file.getName(), true,
                            "application/json", file.getAbsolutePath(),file.length(), true);
                }
            }
*/
        }
    });

    }


}