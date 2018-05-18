package com.brzhang.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brzhang.livedata.LiveDataManger;
import com.brzhang.livedata.LiveDataNotInitializationException;

public class DialogFragment2 extends DialogFragment implements View.OnClickListener {

    LiveDataTestActivity.TestLiveData mTestLiveData;

    public static DialogFragment2 newInstance() {

        Bundle args = new Bundle();

        DialogFragment2 fragment = new DialogFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mTestLiveData = (LiveDataTestActivity.TestLiveData) LiveDataManger.getInstance().obtain(getContext());
        } catch (LiveDataNotInitializationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.live_data_framgnet, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn).setOnClickListener(this);
    }

    private EditText getText() {
        return (EditText) getView().findViewById(R.id.text);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                mTestLiveData.datatext2 = getText().getText().toString();
                mTestLiveData.update(LiveDataTestActivity.TestLiveData.KEY_TEXT2);
                dismissAllowingStateLoss();
                break;
        }
    }
}
