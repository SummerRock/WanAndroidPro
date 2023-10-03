package com.example.myapplication.main.ui.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.common.mainModule.ContextUtils;
import com.common.mainModule.RouterUtils;
import com.common.mainModule.TextTools;
import com.example.myapplication.R;
import com.example.myapplication.base.favorite.CollectDataRepository;
import com.example.myapplication.base.view.BaseViewBinder;
import com.example.myapplication.base.view.OnListItemClickListener;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

public class HomeItemViewBinder extends BaseViewBinder<HomeModelVo.DatasDTO, HomeItemViewBinder.CustomViewHolder> {

    public HomeItemViewBinder(@Nullable OnListItemClickListener listener) {
        super(listener);
    }

    @NonNull
    @Override
    protected CustomViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_list_item, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull CustomViewHolder holder, @NonNull HomeModelVo.DatasDTO datasDTO) {
        holder.favoriteToggleButton.setChecked(datasDTO.getCollect());
        holder.favoriteToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.favoriteToggleButton.isChecked()) {
                    addToFavorites();
                } else {
                    removeFromFavorites();
                }
            }

            private void removeFromFavorites() {
                CollectDataRepository.getInstance().queryUnCollectArticle(datasDTO.getId());
            }

            private void addToFavorites() {
                CollectDataRepository.getInstance().queryCollectArticle(datasDTO.getId());
            }
        });
        holder.title.setText(datasDTO.getTitle());
        holder.author.setText(TextTools.getStringWithDefaultValue(datasDTO.getAuthor(), "佚名"));
        holder.itemView.setOnClickListener(view -> RouterUtils.jumpScheme(datasDTO.getLink(), ContextUtils.getActivityFromView(holder.itemView)));
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView author;
        ToggleButton favoriteToggleButton;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.home_fg_item_title);
            author = itemView.findViewById(R.id.home_fg_item_author);
            favoriteToggleButton = itemView.findViewById(R.id.home_fg_item_collect_switch);
        }
    }
}
