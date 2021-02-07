package com.cvilia.bubble.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.cvilia.bubble.R;
import com.cvilia.bubble.config.Constants;
import com.cvilia.bubble.utils.MMKVUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * author:lzy
 * date:2021-02-01-00-25
 * describe:
 */
public class SearchCityAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SearchCityAdapter(@Nullable List<String> data) {
        super(R.layout.item_search_city, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String city) {
        String myCities = MMKVUtil.getString(Constants.MY_CITIES, null);
        TextView cityName = baseViewHolder.itemView.findViewById(R.id.cityNameTv);
        cityName.setText(city);
        ImageView addIv = baseViewHolder.itemView.findViewById(R.id.addCityIv);
        TextView addedTv = baseViewHolder.itemView.findViewById(R.id.addedTv);
        addIv.setVisibility(View.GONE);
//        if (!TextUtils.isEmpty(myCities)) {
//            if (myCities.contains(city)) {
//                addIv.setVisibility(View.GONE);
//                addedTv.setVisibility(View.VISIBLE);
//            } else {
//                addIv.setVisibility(View.VISIBLE);
//                addedTv.setVisibility(View.GONE);
//            }
//        }
//        addIv.setOnClickListener(v -> {
//            if (!TextUtils.isEmpty(myCities)) {
//                if (myCities.split(",").length < 5) {
//                    MMKVUtil.saveString(Constants.MY_CITIES, myCities + "," + city);
//                    addIv.setVisibility(View.GONE);
//                    addedTv.setVisibility(View.VISIBLE);
//                } else {
//                    //todo 如果已经有四个就不再保存，并弹窗提示
//                }
//            }
//        });
    }
}
