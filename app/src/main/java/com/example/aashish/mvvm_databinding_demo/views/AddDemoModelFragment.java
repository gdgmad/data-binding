package com.example.aashish.mvvm_databinding_demo.views;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aashish.mvvm_databinding_demo.MainActivity;
import com.example.aashish.mvvm_databinding_demo.R;
import com.example.aashish.mvvm_databinding_demo.databinding.FragmentEditModelBinding;
import com.example.aashish.mvvm_databinding_demo.models.DemoModel;
import com.example.aashish.mvvm_databinding_demo.viewModels.AddModelViewModel;
import com.example.aashish.mvvm_databinding_demo.viewModels.BaseViewModel;

/**
 * Created by aashish on 10/20/16.
 */

public class AddDemoModelFragment extends Fragment {

    private FragmentEditModelBinding mBinding;
    private DemoModel mDemoModel;
    private final int ADD_MODEL_CALLER_ID = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static AddDemoModelFragment getInstance(DemoModel demoModel) {
        AddDemoModelFragment addDemoModelFragment = new AddDemoModelFragment();
        addDemoModelFragment.mDemoModel = demoModel;
        return addDemoModelFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_model, container, false);
        mBinding.setDemoModel(mDemoModel);
        mBinding.setViewModel(new AddModelViewModel(getContext(), ADD_MODEL_CALLER_ID, new BaseViewModel.IOnEventOccuredCallbacks() {
            @Override
            public void onEventOccured(int callerId, int eventId, BaseViewModel baseViewModelInstance) {
                if (callerId == ADD_MODEL_CALLER_ID) {
                    switch (eventId) {
                        case AddModelViewModel.SUBMIT_BTN_CLICKED:
                            DemoModel demoModel = ((AddModelViewModel) baseViewModelInstance).mBinding.getDemoModel();
                            ((MainActivity) getActivity()).setData(demoModel);
                            getActivity().onBackPressed();
                            break;
                    }
                }
            }
        }, mBinding));
        return mBinding.getRoot();
    }
}
