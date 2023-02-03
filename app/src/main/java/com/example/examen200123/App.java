package com.example.examen200123;

import android.app.Application;

public class App extends Application {
    public static App instance;

    //se crea un constructor App para que en el metodo getInstance en instace se cree un nuevo objeto.
    public App() {
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static App getInstance() {
        if (instance == null) instance = new App();
        return instance;
    }
}
