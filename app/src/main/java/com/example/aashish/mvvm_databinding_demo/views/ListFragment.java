package com.example.aashish.mvvm_databinding_demo.views;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aashish on 10/20/16.
 */

public class ListFragment extends Fragment {

    private FragmentListBinding mBinding;
    private DemoItemListAdapter mAdapter;
    private List<DemoModel> mDemoModelList;
    private BaseViewModel.IOnEventOccuredCallbacks mViewModelEventsCallbacks;

    public ListFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);

        mBinding.btnAddModel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddModelFragment();
            }
        });

        initViewModelEventCallbacks();
        mBinding.rvItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new DemoItemListAdapter(getContext(), mDemoModelList, mViewModelEventsCallbacks);
        mBinding.rvItems.setAdapter(mAdapter);
        return mBinding.getRoot();
    }

    private void setData() {
        if (mDemoModelList == null || mDemoModelList.size() == 0) {
            mBinding.noItemsView.setVisibility(View.VISIBLE);
            mBinding.rvItems.setVisibility(View.GONE);
        } else {
            mBinding.noItemsView.setVisibility(View.GONE);
            mBinding.rvItems.setVisibility(View.VISIBLE);
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

    @Override
    public void onResume() {
        super.onResume();
        //mockData();
        if (mDemoModelList == null) {
            mDemoModelList = new ArrayList<>();
        }
        DemoModel demoModel = ((MainActivity) getActivity()).getDemoModel();
        if (demoModel != null && !demoModel.isEmpty()) {
            ((MainActivity) getActivity()).setData(null);
            mDemoModelList.add(demoModel);
            if (mAdapter != null) {
                mAdapter.setDemoModels(mDemoModelList);
            }
            setData();
        }
    }

    //Utilities methods start
    private void mockData() {
        mDemoModelList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            DemoModel demoModel = new DemoModel();
            demoModel.name = "Test Name";
            demoModel.description = "This is a test description of the mock data numbered => " + i;
            demoModel.age = "22";
            demoModel.gender = "Male";
            demoModel.address = "This is  test address\nof the mock data\nnumbered " + i + " for\ntesting purposes";
            mDemoModelList.add(demoModel);
        }
        setData();
        mAdapter.setDemoModels(mDemoModelList);
        mBinding.executePendingBindings();
    }

    private void openAddModelFragment(DemoModel model) {
        if (model == null) {
            model = new DemoModel();
        }
        ((MainActivity) getActivity()).replaceFragment(AddDemoModelFragment.getInstance(model), true);
    }

    private void openAddModelFragment() {
        DemoModel model = new DemoModel();
        ((MainActivity) getActivity()).replaceFragment(AddDemoModelFragment.getInstance(model), true);
    }
    //Utilities methods end
}
