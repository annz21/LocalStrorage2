package com.arit.demo.localstrorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.arit.demo.localstrorage.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tvShow)
    TextView tvShow;

    private String displayname;
    private boolean safemode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @Override
    protected  void onResume(){
        super.onResume();
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("setting",MODE_PRIVATE);
//
//        SharedPreferences.Editor editor = pref.edit();
//
//        editor.putString("display-name"," Jonh Doe");
//        editor.putBoolean("safe-mode",true);
//
//
//        editor.apply();
    }
    @OnClick(R.id.btGetPref)
    public void showPref(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("settings",MODE_PRIVATE);
        this.displayname = pref.getString("display", String.valueOf(false));

        this.tvShow.setText(this.displayname);
    }
    @OnClick(R.id.btnGetRealmData)
    public void showRealmData(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User users = realm.where(User.class).findFirst();
                tvShow.setText(users.getFirstname());
            }
        });
    }
}
