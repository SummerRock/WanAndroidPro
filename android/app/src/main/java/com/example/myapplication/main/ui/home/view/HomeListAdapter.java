package com.example.myapplication.main.ui.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
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
import com.example.myapplication.base.view.BaseListAdapter;
import com.example.myapplication.main.ui.home.model.HomeModelVo;

import java.util.List;

public class HomeListAdapter extends BaseListAdapter<HomeListAdapter.ViewHolder, HomeModelVo.DatasDTO> {

    public HomeListAdapter(@Nullable List<HomeModelVo.DatasDTO> list) {
        super(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeModelVo.DatasDTO datasDTO = data.get(position);
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

    // 自定义ViewHolder类
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;

        ToggleButton favoriteToggleButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.home_fg_item_title);
            author = itemView.findViewById(R.id.home_fg_item_author);
            favoriteToggleButton = itemView.findViewById(R.id.home_fg_item_collect_switch);
        }
    }
}
