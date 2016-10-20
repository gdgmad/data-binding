package com.example.aashish.mvvm_databinding_demo.viewModels;

import android.content.Context;
import android.databinding.Bindable;
import android.view.View;

import com.example.aashish.mvvm_databinding_demo.databinding.FragmentEditModelBinding;

/**
 * Created by aashish on 10/20/16.
 */

public class AddModelViewModel extends BaseViewModel {

    public FragmentEditModelBinding mBinding;
    public static final int SUBMIT_BTN_CLICKED = 0;

    public AddModelViewModel(Context context, int callerId, IOnEventOccuredCallbacks onEventOccuredCallbacks, FragmentEditModelBinding binding) {
        super(context, callerId, onEventOccuredCallbacks);
        mBinding = binding;
    }

    @Bindable
    public View.OnClickListener getOnSubmitBtnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnEventOccuredCallbacks.onEventOccured(mCallerId, SUBMIT_BTN_CLICKED, AddModelViewModel.this);
            }
        };
    }
}
