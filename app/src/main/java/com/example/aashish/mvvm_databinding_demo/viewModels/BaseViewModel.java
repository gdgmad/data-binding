package com.example.aashish.mvvm_databinding_demo.viewModels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by aashish on 10/20/16.
 */

public class BaseViewModel extends BaseObservable {

    protected WeakReference<Context> mContext;
    protected int mCallerId;
    protected IOnEventOccuredCallbacks mOnEventOccuredCallbacks;

    public BaseViewModel(Context context, int callerId, IOnEventOccuredCallbacks onEventOccuredCallbacks) {
        mContext = new WeakReference<>(context);
        mCallerId = callerId;
        mOnEventOccuredCallbacks = onEventOccuredCallbacks;
    }

    public interface IOnEventOccuredCallbacks {
        /***
         * Callback for specific event. Use id to identify the event.
         * @param callerId Request code sent to the viewModel to avoid conflicts in case of similar IDs between viewModels
         * @param eventId Any static final int defined in the ViewModel based on which the driver will switch actions
         * @param baseViewModelInstance Instance of the viewModel to access Model or other public variable
         */
        void onEventOccured(int callerId, int eventId, BaseViewModel baseViewModelInstance);
    }

    public interface MyTestAdapter {

        @BindingAdapter("android:src")
        void setImageUrl(ImageView iv, String url);
    }

    public interface DataBindingComponent {
        MyTestAdapter getTestAdapter();
    }
}
