package renchaigao.com.zujuba.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import renchaigao.com.zujuba.Json.StoreInfo;

/**
 * Created by Administrator on 2018/8/2/002.
 */

public class PlaceListActivityAdapter extends RecyclerView.Adapter<PlaceListActivityAdapter.ViewHolder> {
    final static String TAG = "PlaceListActivityAdapter";
    private ArrayList<StoreInfo> mStore;
    private Context mContext;
    @Override
    public PlaceListActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PlaceListActivityAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
