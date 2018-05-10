package com.example.farzadfarshad.adeiye;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by farshad&farzad on 4/16/2018.
 */

@Module
public class ContextModule {

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context context() {
        return context;
    }
}
