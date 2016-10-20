package com.example.aashish.mvvm_databinding_demo.views;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aashish.mvvm_databinding_demo.MainActivity;
import com.example.aashish.mvvm_databinding_demo.R;
import com.example.aashish.mvvm_databinding_demo.adapters.DemoItemListAdapter;
import com.example.aashish.mvvm_databinding_demo.databinding.FragmentListBinding;
import com.example.aashish.mvvm_databinding_demo.models.DemoModel;
import com.example.aashish.mvvm_databinding_demo.viewModels.BaseViewModel;
import com.example.aashish.mvvm_databinding_demo.viewModels.ItemCardViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by aashish on 10/20/16.
 */

public class ListFragment extends Fragment {

    private FragmentListBinding mBinding;
    private DemoItemListAdapter mAdapter;
    private List<DemoModel> mDemoModelList;
    private BaseViewModel.IOnEventOccuredCallbacks mViewModelEventsCallbacks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        initViewModelEventCallbacks();
        setAdapter();
        return mBinding.getRoot();
    }

    private void setAdapter() {
        if (mDemoModelList == null || mDemoModelList.size() == 0) {
            mBinding.noItemsView.setVisibility(View.VISIBLE);
            mBinding.rvItems.setVisibility(View.GONE);
        } else {
            mBinding.noItemsView.setVisibility(View.GONE);
            mBinding.rvItems.setVisibility(View.VISIBLE);
            mBinding.rvItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            mAdapter = new DemoItemListAdapter(getContext(), mDemoModelList, mViewModelEventsCallbacks);
            mBinding.rvItems.setAdapter(mAdapter);
        }
    }

    private void initViewModelEventCallbacks() {
        mViewModelEventsCallbacks = new BaseViewModel.IOnEventOccuredCallbacks() {
            @Override
            public void onEventOccured(int callerId, int eventId, BaseViewModel baseViewModelInstance) {
                if (callerId == DemoItemListAdapter.LIST_FRAGMENT_CALLER_ID) {
                    switch (eventId) {
                        case ItemCardViewModel.CARD_CLICKED:
                            DemoModel demoModel = ((ItemCardViewModel) baseViewModelInstance).binding.getDemoModel();
                            openAddModelFragment(demoModel);
                            break;
                    }
                }
            }
        };
    }

    private void openAddModelFragment(DemoModel model) {
        if (model == null) {
            model = new DemoModel();
        }
        ((MainActivity) getActivity()).replaceFragment(AddDemoModelFragment.getInstance(model), true);
    }
}
