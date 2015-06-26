package com.pictorial.View.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.huewu.pla.lib.MultiColumnListView;
import com.pictorial.R;
import com.pictorial.app.Constant;
import com.pictorial.net.Exceptions;
import com.pictorial.net.IRequest;
import com.pictorial.net.RequestListener;

/**
 * Created by zhangbing1 on 2015/6/26.
 */
public class NewsFragment extends Fragment {

    private static final String ARG_POSITION = "News";
    private int mArg;
    private PLAAdapter mAdapter;

    public static NewsFragment newInstance(int position) {
        NewsFragment f = new NewsFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);

        return f;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(savedInstanceState!=null){
            mArg = getArguments().getInt(ARG_POSITION);
        }
        ListView listView=new ListView(getActivity());
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MultiColumnListView listView = (MultiColumnListView) view.findViewById(R.id.list);
        mAdapter = new PLAAdapter(getActivity());
        fillAdapter(mAdapter, 30);
        listView.setAdapter(mAdapter);
    }

    private static class PLAAdapter extends ArrayAdapter<String> {
        public PLAAdapter(Context context) {
            super(context, R.layout.sample_item, android.R.id.text1);
        }
    }

    private void fillAdapter(PLAAdapter adapter, int count) {
        for (int i = 0; i < count; ++i) {
            StringBuilder builder = new StringBuilder();
            for (int j = adapter.getCount(), max = (i * 1234) % 500; j < max; j++)
                builder.append(i).append(' ');
            adapter.add(builder.toString());
        }
    }

    private void getNewsList(){
        final ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...");
        pDialog.show();
        IRequest.get(getActivity(), Constant.base, new RequestListener() {
            @Override
            public void requestSuccess(String json) {
                pDialog.dismiss();

                System.out.print(json);


            }

            @Override
            public void requestError(VolleyError e) {
                Exceptions.errorHandle(getActivity(), e);
                pDialog.dismiss();
            }
        });
    }




}
