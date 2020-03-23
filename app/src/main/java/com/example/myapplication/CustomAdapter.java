package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>  {
    private static ArrayList<Datamodel> dataSet;





    public void filterlist(ArrayList<Datamodel> filtered_dataSet)
    {
        dataSet=filtered_dataSet;
        notifyDataSetChanged();
    }

//    @Override
//    public Filter getFilter() {
//        return new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                String searchstring=constraint.toString();
//
//                if(searchstring.isEmpty())
//                    filtered_dataSet=dataSet;
//                else
//
//                    {
//                        ArrayList<Datamodel> filtered_list=new ArrayList<>();
//                        for ( Datamodel i:dataSet)
//                        {
//                            if(i.getName1().toLowerCase().contains(searchstring))
//                                filtered_list.add(i);
//                        }
//                    filtered_dataSet=filtered_list;
//
//
//                    }
//                FilterResults filterResults=new FilterResults();
//                filterResults.values=filtered_dataSet;
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                filtered_dataSet= (ArrayList<Datamodel>) results.values;
//                notifyDataSetChanged();
//            }
//        };
//    }

    //overriding the viewholder class
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
TextView name;
TextView price;
ImageView image1;
ImageView image2;

public MyViewHolder(View itemView)
{
super(itemView);
//this.name=(TextView)itemView.findViewById(R.id.bookname2);
//this.price=(TextView)itemView.findViewById(R.id.price2);
this.image1=itemView.findViewById(R.id.imageView4);

//this.image2=itemView.findViewById(R.id.thumbnail21);
    Button b=itemView.findViewById(R.id.details);
    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(v.getContext(),Details.class);
            i.putExtra("position",getAdapterPosition());
            v.getContext().startActivity(i);
        }
    });
}
    }

//defining the adaptor
 public CustomAdapter(ArrayList<Datamodel> arraylist)
 {
this.dataSet=arraylist;
 }

 @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
 {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookcard2final,viewGroup,false);
        int height=viewGroup.getMeasuredHeight()/3;
     view.setMinimumHeight(height);
        MyViewHolder m=new MyViewHolder(view);
        return m;
    }

    public void onBindViewHolder(final  MyViewHolder myViewHolder, final int listposition)
{
//TextView t1=myViewHolder.name;
//TextView t2=myViewHolder.price;
ImageView i=myViewHolder.image1;
//decode MIME object to image and set drawable

byte[] decoded= Base64.decode(dataSet.get(listposition).getImage(),Base64.DEFAULT);
    Bitmap decodedbyte= BitmapFactory.decodeByteArray(decoded,0,decoded.length);
    if(decodedbyte!=null)
    i.setImageBitmap(decodedbyte);
//t1.setText(dataSet.get(listposition).getName1());
//i.setImageResource(dataSet.get(listposition).getImage());
//t2.setText(dataSet.get(listposition).getPrice());
myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent i=new Intent(v.getContext(),Details.class);
        i.putExtra("position",listposition);
        v.getContext().startActivity(i);
    }
});


}

    @Override
    public int getItemCount() {
        return dataSet.size();}
    }



