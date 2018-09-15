package com.arit.demo.localstrorage;

import android.app.Application;
import android.content.SharedPreferences;

import com.arit.demo.localstrorage.model.User;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class BaseApplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("setting",MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("display-name"," Jonh Doe");
        editor.putBoolean("safe-mode",true);
        editor.apply();
        Realm.init(this);

        Realm realm = Realm.getDefaultInstance();

        final User user = new User();
        user.setFirstname("J");
        user.setLastname("A");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insert(user);
            }
        });


//
//
//
//
//        Realm.init(this);
////        RealmConfiguration config = new RealmConfiguration.Builder().name("localstory").build();
//
//

    }
    @Override
    public void onTerminate(){
        super.onTerminate();
    }


}
