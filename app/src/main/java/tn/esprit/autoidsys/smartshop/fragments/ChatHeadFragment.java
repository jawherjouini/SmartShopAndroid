package tn.esprit.autoidsys.smartshop.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tn.esprit.autoidsys.smartshop.R;


/**
 * FloatingView???????????????????????
 */
public class ChatHeadFragment extends Fragment {

    /**
     * ChatHeadActionCallback
     */
    private ChatHeadActionCallback mChatHeadActionCallback;

    /**
     * ChatHeadFragment???????
     *
     * @return ChatHeadFragment
     */
    public static ChatHeadFragment newInstance() {
        final ChatHeadFragment fragment = new ChatHeadFragment();
        return fragment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mChatHeadActionCallback = (ChatHeadActionCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + ChatHeadActionCallback.class.toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_chathead, container, false);
        // ?????
        final View clearFloatingButton = rootView.findViewById(R.id.clearDemo);
        clearFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rootView;
    }

    /**
     * ?????????????????
     */
    public interface ChatHeadActionCallback {

        /**
         * ???????????
         */
        void clearChatHead();

    }
}
