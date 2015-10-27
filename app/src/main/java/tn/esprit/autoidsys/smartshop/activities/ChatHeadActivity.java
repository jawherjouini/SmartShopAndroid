package tn.esprit.autoidsys.smartshop.activities;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import tn.esprit.autoidsys.smartshop.R;
import tn.esprit.autoidsys.smartshop.fragments.ChatHeadFragment;
import tn.esprit.autoidsys.smartshop.utils.ChatHeadService;


public class ChatHeadActivity extends Activity implements ServiceConnection, ChatHeadFragment.ChatHeadActionCallback {

    private static final String FRAGMENT_TAG_CHATHEAD = "chathead";

    /**
     * ChatHeadService
     */
    private ChatHeadService mChatHeadService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chathead);

        if (savedInstanceState == null) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.container, ChatHeadFragment.newInstance(), FRAGMENT_TAG_CHATHEAD);
            ft.commit();
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mChatHeadService = ((ChatHeadService.ChatHeadServiceBinder) service).getService();

        if (mChatHeadService != null) {
            unbindService(this);
            mChatHeadService.stopSelf();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        mChatHeadService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearChatHead() {
        //bindService(new Intent(this, ChatHeadService.class), this, Context.BIND_AUTO_CREATE);
    }
}
