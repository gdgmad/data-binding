package com.example.aashish.mvvm_databinding_demo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.aashish.mvvm_databinding_demo.databinding.ActivityMainBinding;
import com.example.aashish.mvvm_databinding_demo.models.DemoModel;
import com.example.aashish.mvvm_databinding_demo.views.ListFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private DemoModel mDemoModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        addFragment(new ListFragment());
    }

    public void addFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(mBinding.fragmentContainer.getId(), fragment);
        transaction.commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left
                , R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getName());
        }
        transaction.replace(mBinding.fragmentContainer.getId(), fragment);
        transaction.commitAllowingStateLoss();
    }

    public void setData(DemoModel model) {
        mDemoModel = model;
    }

    public DemoModel getDemoModel() {
        return mDemoModel;
    }
}
