package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("ALL")
public class add_new_value extends AppCompatActivity {
    String filestr;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_value);

        EditText editText=findViewById(R.id.bookaddname);
        EditText editText1=findViewById(R.id.authornameadd);
        EditText editText2=findViewById(R.id.addprice);
        Button button=findViewById(R.id.thumbnail);
        Button button1=findViewById(R.id.submit);



        String bookname=editText.getText().toString();
        String authorname=editText1.getText().toString();
        String price=editText2.getText().toString();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              image_picker();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    void image_picker()
    {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);

        if(path!=null)
        {ImageView imageView1=findViewById(R.id.thumbadd);
            Bitmap bitmap= BitmapFactory.decodeFile(path);
            imageView1.setImageBitmap(bitmap);
        }
        else
        {
            ImageView imageView1=findViewById(R.id.thumbadd);
            Bitmap bitmap= BitmapFactory.decodeFile(filestr);
            imageView1.setImageBitmap(bitmap);
        }
    }
    void upload(final String a)
    {
            class upload_data extends AsyncTask<Void,Void,String>{

                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        URL url=new URL(a);
                        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            }



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView=findViewById(R.id.thumbadd);
        if (resultCode == RESULT_OK && requestCode ==1) {
            Uri imageUri = data.getData();
            if(imageUri!=null)
            imageView.setImageURI(imageUri);
            else
                imageView=findViewById(R.drawable.pic2);
        }
    }}

