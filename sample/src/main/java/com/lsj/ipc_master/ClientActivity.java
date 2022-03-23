package com.lsj.ipc_master;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.codezjx.andlinker.AndLinker;
import com.lsj.ipc_master.service.IRemoteService;
import com.lsj.linker.utils.KLog;

public class ClientActivity extends AppCompatActivity implements AndLinker.BindCallback {

    private static final String REMOTE_SERVICE_PACKAGE = "com.lsj.ipc_master";
    private static final String REMOTE_SERVICE_ACTION = "com.lsj.ipc_master.REMOTE_SERVICE_ACTION";
    private AndLinker andLinker;
    private IRemoteService remoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLinker();
    }

    private void initLinker() {
        andLinker = new AndLinker.Builder(this)
                .packageName(REMOTE_SERVICE_PACKAGE)
                .action(REMOTE_SERVICE_ACTION)
                .build();
        andLinker.setBindCallback(this);
        andLinker.bind();
    }

    private void initView() {
        Button btnGetID = findViewById(R.id.btn_get_id);
        btnGetID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KLog.d(remoteService.getPid() + " ");
            }
        });
    }

    @Override
    public void onBind() {
        KLog.d();
        remoteService = andLinker.create(IRemoteService.class);
    }

    @Override
    public void onUnBind() {
        KLog.d();
        remoteService = null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        KLog.d();
        andLinker.unbind();
        andLinker.setBindCallback(null);
    }
}