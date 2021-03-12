package com.usmba.chariaa.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import com.usmba.chariaa.MainActivity;
import com.usmba.chariaa.Models.Article.Article;
import com.usmba.chariaa.R;




public class Adapter_Articles extends RecyclerView.Adapter<Adapter_Articles.ArticleViewHolder> {

    List<Article> list;
    Context context;
    MainActivity mainActivity;

    public Adapter_Articles(Context context, List<Article> list, MainActivity Parent){
        this.context = context;
        this.list = list;
        this.mainActivity = Parent;

    }
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder adsViewHolder, int i) {
        final Article article = list.get(i);
        ArticleViewHolder holder = adsViewHolder;

        holder.txt_Article_Titre.setText(article.title);
        holder.txt_Article_Day.setText(article.getdate_day());
        holder.txt_Article_Month.setText(article.getdate_month());

        holder.cardview_Ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.DisplayArticle(article);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView txt_Article_Titre;
        TextView txt_Article_Day;
        TextView txt_Article_Month;
        CardView cardview_Ads;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_Article_Titre = itemView.findViewById(R.id.txt_Article_Titre);
            txt_Article_Day = itemView.findViewById(R.id.txt_Article_Day);
            txt_Article_Month = itemView.findViewById(R.id.txt_Artilcle_Month);
            cardview_Ads = itemView.findViewById(R.id.cardview_Ads);
        }
    }
}
