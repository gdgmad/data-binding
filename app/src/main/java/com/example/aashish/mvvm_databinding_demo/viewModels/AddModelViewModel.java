package com.example.aashish.mvvm_databinding_demo.viewModels;

import android.content.Context;

import com.example.aashish.mvvm_databinding_demo.databinding.FragmentEditModelBinding;

/**
 * Created by aashish on 10/20/16.
 */

public class AddModelViewModel extends BaseViewModel {

    public FragmentEditModelBinding mBinding;

    public AddModelViewModel(Context context, int callerId, IOnEventOccuredCallbacks onEventOccuredCallbacks, FragmentEditModelBinding binding) {
        super(context, callerId, onEventOccuredCallbacks);
        mBinding = binding;
    }
}
