package ru.geekbrains.chatapplication;

import android.app.Application;

import ru.geekbrains.chatapplication.di.AppComponent;
import ru.geekbrains.chatapplication.di.AppModule;
import ru.geekbrains.chatapplication.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
