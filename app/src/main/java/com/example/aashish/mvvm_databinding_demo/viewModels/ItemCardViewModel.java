package com.example.aashish.mvvm_databinding_demo.viewModels;

import android.content.Context;
import android.databinding.Bindable;
import android.view.View;

import com.example.aashish.mvvm_databinding_demo.databinding.LayoutCardItemBinding;

/**
 * Created by aashish on 10/20/16.
 */

public class ItemCardViewModel extends BaseViewModel {

    public LayoutCardItemBinding binding;
    public static final int CARD_CLICKED = 0;

    public ItemCardViewModel(Context context, int callerId, IOnEventOccuredCallbacks onEventOccuredCallbacks, LayoutCardItemBinding binding) {
        super(context, callerId, onEventOccuredCallbacks);
        this.binding = binding;
    }

    @Bindable
    public View.OnClickListener getItemClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnEventOccuredCallbacks.onEventOccured(mCallerId, CARD_CLICKED, ItemCardViewModel.this);
            }
        };
    }
}
