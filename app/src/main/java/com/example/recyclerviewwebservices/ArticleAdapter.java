package com.example.recyclerviewwebservices;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private final List<Article> mArticles;

    public ArticleAdapter(List<Article> articles){
        mArticles= articles;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);

        View articleView= inflater.inflate(R.layout.title_author,
                parent,
                false);



        ViewHolder viewHolder= new ViewHolder(articleView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {


        Article article= mArticles.get(position);

        TextView textViewTitle= holder.titleTextView;
        textViewTitle.setText(article.getTitle());

        TextView textViewAuthor= holder.authorTextView;
        textViewAuthor.setText(article.getAuthor());


    }

    @Override
    public int getItemCount() {
        Log.d("List size: " , "Here is your size"+ mArticles.size());
        return mArticles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleTextView;
        public TextView authorTextView;

        public ViewHolder(View itemView){
            super(itemView);

            titleTextView= itemView.findViewById(R.id.articleTitle);
            authorTextView= itemView.findViewById(R.id.author);



        }

    }
}
