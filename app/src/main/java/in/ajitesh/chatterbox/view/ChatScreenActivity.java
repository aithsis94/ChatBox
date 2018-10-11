package in.ajitesh.chatterbox.view;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.ajitesh.chatterbox.R;
import in.ajitesh.chatterbox.Utils.Util;
import in.ajitesh.chatterbox.adapter.MessageAdapter;
import in.ajitesh.chatterbox.broadcastreceiver.NetworkStatusListener;
import in.ajitesh.chatterbox.component.DaggerIChatComponent;
import in.ajitesh.chatterbox.interfaces.IChatPresenter;
import in.ajitesh.chatterbox.interfaces.IChatView;
import in.ajitesh.chatterbox.model.db.MessageModel;
import io.realm.RealmResults;

public class ChatScreenActivity extends AppCompatActivity implements IChatView {

    @BindView(R.id.editTextChatMessage)
    EditText chatMessageEditText;

    @BindView(R.id.recyclerViewMessages)
    RecyclerView messageRecyclerView;

    private MessageAdapter messageAdapter;

    @Inject
    IChatPresenter presenter;

    @Inject
    NetworkStatusListener networkStatusListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_chat_screen);
        ButterKnife.bind(this);
        DaggerIChatComponent.create().injectToView(this);
        this.presenter.injectView(this);
        this.networkStatusListener.setHostingActivity(this);
        this.requestPermissions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.registerNetworkBroadcastReceiver(this, this.networkStatusListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(this.networkStatusListener);
    }


    @Override
    public void loadChatData(RealmResults<MessageModel> messageModels) {
        this.messageAdapter = new MessageAdapter(LayoutInflater.from(this), messageModels);
        this.messageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.messageRecyclerView.setAdapter(this.messageAdapter);
        this.messageRecyclerView.scrollToPosition(this.messageAdapter.getItemCount());
    }

    @Override
    public void onChatDataUpdated() {
        this.messageAdapter.notifyDataSetChanged();
        this.messageRecyclerView.smoothScrollToPosition(this.messageAdapter.getItemCount());
    }

    @Override
    public void showMessage(String message, boolean isLongLivedMessage) {
        Toast.makeText(
                this,
                message,
                isLongLivedMessage ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    @Override
    @OnClick(R.id.imageButtonSendMessage)
    public void onSendMessageClicked() {
        String message = this.chatMessageEditText.getText().toString();
        this.presenter.sendMessage(this, message);
        this.chatMessageEditText.setText("");
    }

    private void requestPermissions() {

        Dexter.initialize(this);
        Dexter.checkPermissions(new CompositeMultiplePermissionsListener() {

            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                super.onPermissionsChecked(report);

                if (!report.areAllPermissionsGranted()) {
                    Toast.makeText(ChatScreenActivity.this, "Permissions not granted, exiting app", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }, Manifest.permission.INTERNET);
    }
}