package com.example.aashish.mvvm_databinding_demo.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aashish.mvvm_databinding_demo.R;
import com.example.aashish.mvvm_databinding_demo.databinding.LayoutCardItemBinding;
import com.example.aashish.mvvm_databinding_demo.models.DemoModel;
import com.example.aashish.mvvm_databinding_demo.viewModels.BaseViewModel;
import com.example.aashish.mvvm_databinding_demo.viewModels.ItemCardViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aashish on 10/20/16.
 */

public class DemoItemListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DemoModel> mDemoModels;
    private Context mContext;
    private BaseViewModel.IOnEventOccuredCallbacks mOnEventOccuredCallbacks;
    public static final int LIST_FRAGMENT_CALLER_ID = 0;

    public DemoItemListAdapter(Context context, List<DemoModel> demoModels, BaseViewModel.IOnEventOccuredCallbacks onEventOccuredCallbacks) {
        mContext = context;
        mDemoModels = demoModels;
        mOnEventOccuredCallbacks = onEventOccuredCallbacks;
    }

    public void setDemoModels(List<DemoModel> demoModels) {
        mDemoModels = demoModels;
        notifyDataSetChanged();
    }

    public void addDemoModel(DemoModel demoModel) {
        if (mDemoModels == null) {
            mDemoModels = new ArrayList<>();
        }
        mDemoModels.add(demoModel);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).binding.setDemoModel(mDemoModels.get(position));
        ((MyViewHolder) holder).binding.setViewModel(new ItemCardViewModel(mContext, LIST_FRAGMENT_CALLER_ID, mOnEventOccuredCallbacks, ((MyViewHolder) holder).binding));
        ((MyViewHolder) holder).binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (mDemoModels == null) {
            return 0;
        } else {
            return mDemoModels.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        protected LayoutCardItemBinding binding;

        public MyViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
