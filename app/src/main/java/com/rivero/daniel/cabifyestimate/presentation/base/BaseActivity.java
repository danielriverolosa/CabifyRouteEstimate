package com.rivero.daniel.cabifyestimate.presentation.base;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.rivero.daniel.cabifyestimate.R;
import com.rivero.daniel.cabifyestimate.infrastructure.di.component.DaggerViewComponent;
import com.rivero.daniel.cabifyestimate.infrastructure.di.component.ViewComponent;
import com.rivero.daniel.cabifyestimate.infrastructure.di.module.ViewModule;
import com.rivero.daniel.cabifyestimate.presentation.AndroidApplication;
import com.rivero.daniel.cabifyestimate.presentation.base.navigator.Navigator;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;

    @BindView(R.id.fragment_container)
    FrameLayout container;

    @Inject
    Navigator navigator;

    protected boolean displayHomeAsUpEnabled;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeInjector();
        setContentView(getLayout());
    }

    @LayoutRes
    public abstract int getLayout();

    public abstract void initializeInjector();

    protected ViewComponent buildInjector(ViewModule viewModule) {
        return DaggerViewComponent.builder()
                .viewModule(viewModule)
                .applicationComponent(((AndroidApplication) getApplication()).getApplicationComponent())
                .build();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    private void initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (toolbarTitle != null) {
            toolbarTitle.setText(title);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    public <T extends Serializable> T getParamByClass(Class<T> paramClass) {
        return getParamByClass(paramClass, "");
    }

    @SuppressWarnings("unchecked")
    protected <T extends Serializable> T getParamByClass(Class<T> paramClass, String tag) {
        return (T) getIntent().getSerializableExtra(paramClass.getName() + tag);
    }

    protected static <T extends Serializable> void setParamByClass(Intent intent, Class<T> registeredClass, T object) {
        setParamByClass(intent, registeredClass, object, "");
    }

    protected static <T extends Serializable> void setParamByClass(Intent intent, Class<T> registeredClass, T object, String tag) {
        intent.putExtra(registeredClass.getName() + tag, object);
    }

    protected void initFragmentContainer(int containerId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int message) {
        Snackbar.make(container, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void close() {
        finish();
    }
}
