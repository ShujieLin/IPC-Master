package com.lsj.ipc_master;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.lsj.linker.Linker;

public class MainActivity extends AppCompatActivity implements Linker.BindCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBind() {

    }

    @Override
    public void onUnbind() {

    }
}