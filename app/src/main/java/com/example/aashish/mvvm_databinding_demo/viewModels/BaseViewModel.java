package com.example.aashish.mvvm_databinding_demo.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;

import java.lang.ref.WeakReference;

/**
 * Created by aashish on 10/20/16.
 */

public class BaseViewModel extends BaseObservable {

    protected WeakReference<Context> mContext;
    protected int mCallerId;
    protected IOnEventOccuredCallbacks mOnEventOccuredCallbacks;

    public BaseViewModel(Context context, int callerId, IOnEventOccuredCallbacks onEventOccuredCallbacks) {
        mContext = new WeakReference<Context>(context);
        mCallerId = callerId;
        mOnEventOccuredCallbacks = onEventOccuredCallbacks;
    }

    public interface IOnEventOccuredCallbacks {
        void onEventOccured(int callerId, int eventId, BaseViewModel baseViewModelInstance);
    }
}
