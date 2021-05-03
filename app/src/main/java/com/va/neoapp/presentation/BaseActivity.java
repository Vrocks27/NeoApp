package com.va.neoapp.presentation;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.va.neoapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(setLayoutResource());
        initGUI(savedInstanceState);
        initData();
    }

    protected abstract int setLayoutResource();

    protected abstract void initGUI(Bundle savedInstanceState);

    protected abstract void initData();

    /**
     * This is to replace the new fragment in view.
     *
     * @param fragmentClass fragment class need to pass
     * @param contentFrame  frame layout in which the parse fragment will be added
     * @param bundle        bundle data to pass it to a fragment
     */
    public void replaceFragment(Class fragmentClass, int contentFrame, Bundle bundle) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentClass.getSimpleName());
        boolean isPopBackStack = getSupportFragmentManager().popBackStackImmediate(fragmentClass.getSimpleName(), 0);
        if (!isPopBackStack) {
            if (fragment == null) {
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (fragment != null) {
                if (bundle != null) {
                    fragment.setArguments(bundle);
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_out_left, R.anim.slide_left_in, R.anim.slide_out_right);
                fragmentTransaction.addToBackStack(fragmentClass.getSimpleName());
                fragmentTransaction.replace(contentFrame, fragment, fragmentClass.getSimpleName());
                getSupportFragmentManager().executePendingTransactions();
                fragmentTransaction.commit();
            }
        }
    }

}