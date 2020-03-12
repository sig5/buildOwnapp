package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindetailpage);
        int p=getIntent().getIntExtra("position",0);
        TextView bookname=findViewById(R.id.bookname);
        TextView bookauthor=findViewById(R.id.bookauthor);
        TextView bookdescription=findViewById(R.id.bookdescription);
        TextView bookprice=findViewById(R.id.bookprice);
        TextView bookgenre=findViewById(R.id.bookgenre);
        ImageView frontcover=findViewById(R.id.frontcover);
        bookname.setText(MyData.name.get(p));
        bookauthor.setText(MyData.authorname.get(p));
        bookprice.setText(MyData.price.get(p));
        bookdescription.setText(MyData.description.get(p));

        byte[] decode= Base64.decode(MyData.image.get(p),Base64.DEFAULT);

        Bitmap decoded= BitmapFactory.decodeByteArray(decode,0,decode.length);
        if(decoded!=null)
        frontcover.setImageBitmap(decoded);
        else {
           frontcover.setImageDrawable(getDrawable(R.drawable.pic2));
        }
    }
}
