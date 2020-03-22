package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.provider.MediaStore.Images.Media.getBitmap;

@SuppressWarnings("ALL")
public class add_new_value extends AppCompatActivity {
    String filestr;
    String path;
    String front;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_value);

        EditText editText=findViewById(R.id.bookaddname);
        EditText editText1=findViewById(R.id.authornameadd);
        EditText editText2=findViewById(R.id.addprice);
        Button button=findViewById(R.id.thumbnail);
        Button button1=findViewById(R.id.submit);
        ImageButton imageButton=findViewById(R.id.imageButton2);



        final String bookname=editText.getText().toString();
        final String authorname=editText1.getText().toString();
        final String price=editText2.getText().toString();






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              image_picker();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                upload(bookname,authorname,price,"",front);

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    String image_picker()
    {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
        Bitmap bitmap;
        if(path!=null)
        {ImageView imageView1=findViewById(R.id.thumbadd);
            bitmap= BitmapFactory.decodeFile(path);
            imageView1.setImageBitmap(bitmap);

        }
        else
        {
            ImageView imageView1=findViewById(R.id.thumbadd);
           bitmap= BitmapFactory.decodeFile(filestr);
            imageView1.setImageBitmap(bitmap);
        }
        return "";

    }
    void upload(String bookname, String authorname, String price, String description, final String front1)
    {
            class Upload_data extends AsyncTask<Void,Void,String>{
                EditText editText=findViewById(R.id.bookaddname);
                EditText editText1=findViewById(R.id.authornameadd);
                EditText editText2=findViewById(R.id.addprice);
                EditText editText3=findViewById(R.id.description);
                Button button=findViewById(R.id.thumbnail);
                Button button1=findViewById(R.id.submit);



                String bookname=editText.getText().toString();
                String authorname=editText1.getText().toString();
                String price=editText2.getText().toString();
                String description=editText3.getText().toString();


                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    System.out.println("started");
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    System.out.println("donebruh");
                }

                @Override
                protected String doInBackground(Void... voids) {
                    try {
                        String data= URLEncoder.encode("bookname","UTF-8")+"=\""+URLEncoder.encode(bookname,"UTF-8");
                        data+="\"&"+URLEncoder.encode("authorname","UTF-8")+"=\""+URLEncoder.encode(authorname,"UTF-8");
                        data+="\"&"+URLEncoder.encode("price","UTF-8")+"=\""+URLEncoder.encode(price,"UTF-8")+"\"&"+URLEncoder.encode("description","UTF-8")+"=\""+URLEncoder.encode(description,"UTF-8")+"\"&"+URLEncoder.encode("front","UTF-8")+"="+URLEncoder.encode(front,"UTF-8");
                        URL url=new URL("https://buyowned.000webhostapp.com/scrpt.php");
                        HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                        urlConnection.setDoOutput(true);
                        urlConnection.setDoInput(true);
                        urlConnection.setRequestMethod("POST");
                        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(urlConnection.getOutputStream());
                        System.out.println(data);
                        outputStreamWriter.write(data);outputStreamWriter.close();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String s=new String();
                        while((s=reader.readLine())!=null)
                        {
                            System.out.println(s);
                        }

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    return null;
                }
            }
            Upload_data upload_data=new Upload_data();
            upload_data.execute();


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
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            Bitmap bitmap= null;
            try {
                bitmap = getBitmap(getContentResolver(),data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap.compress(Bitmap.CompressFormat.PNG,30,byteArrayOutputStream);
            byte[] b=byteArrayOutputStream.toByteArray();
            front= Base64.encodeToString(b,Base64.DEFAULT);

        }
    }}

