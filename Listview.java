package com.example.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Listview extends AppCompatActivity {



    private static CustomAdapter adapter;
    private static RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Datamodel> arrayList;
    static View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layoutfinal);

        //DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        //drawerLayout.closeDrawers();



        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if(menuItem.getItemId()==R.id.logout)
        {
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(),Loginpage.class));
            finish();
        }
        return false;
    }
});
        ProgressBar progressbar=findViewById(R.id.progressBar);
        // setting up dropdown menu for the gridlayout activity
        FloatingActionButton floatingActionButton=findViewById(R.id.floatingActionButton);

        //ImageView imageView=findViewById(R.id.imageView2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),add_new_value.class);
                startActivity(i);

            }

        });

        EditText searchbar=findViewById(R.id.searchbar);
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());



        jsonfetcher( "https://buyowned.000webhostapp.com/phpscript.php",progressbar);

        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });


    }

    private  void filter(String string)
    {
            int i=0;
            ArrayList<Datamodel> Filtered_data = new ArrayList<>();
        for(String s: MyData.name)
        {
            if(s.toLowerCase().contains(string.toLowerCase()))
               Filtered_data.add(new Datamodel(MyData.name.get(i),MyData.price.get(i),MyData.image.get(i),MyData.authorname.get(i),MyData.description.get(i)));
                i++;
        }
        adapter.filterlist(Filtered_data);
    }


    private void jsonfetcher(final String urlWebService, final View progress)
    {
class jsonfetch extends AsyncTask<Void,Void,String>
        {String json;

            StringBuilder s=new StringBuilder();
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress.setVisibility(View.VISIBLE);
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {

                    URL url=new URL(urlWebService);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();

                    BufferedReader bread=new BufferedReader(new InputStreamReader(con.getInputStream()));

                    while ((json=bread.readLine())!=null)
                    {
                        s.append(json+"\n");

                    }
                    json=s.toString().trim();
//                    Toast.makeText(getApplicationContext(),json,Toast.LENGTH_LONG).show();

                    return s.toString().trim();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Loadintomydata(json);
                ArrayList<Datamodel> data=new ArrayList<>();

                for(int i=0;i<MyData.name.size();i++)
                {
                    data.add(new Datamodel(MyData.name.get(i),MyData.price.get(i),MyData.image.get(i),MyData.authorname.get(i),MyData.description.get(i)));
                }
                adapter=new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
                progress.setVisibility(View.GONE);
            }
        }

        jsonfetch JSONfetch=new jsonfetch();
        JSONfetch.execute();
    };
    private void Loadintomydata(String json)
    {
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(json);


        String[] data=new String[jsonArray.length()];
        for(int i=0;i<jsonArray.length();i++)
        {
            JSONObject obj=jsonArray.getJSONObject(i);
            MyData.p++;
            MyData.name.add(obj.getString("name"));
            MyData.price.add(obj.getString("price"));
            System.out.println(MyData.name.get(i));
            MyData.image.add(obj.getString("front"));
            MyData.description.add((obj.getString("description")));
            MyData.authorname.add((obj.getString("authorname")));

        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

