package mu29.legotv.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import mu29.legotv.R;
import mu29.legotv.action.VideoAction;
import mu29.legotv.adapter.DefaultListener;
import mu29.legotv.adapter.ProgramAdapter;
import mu29.legotv.common.flux.Action;
import mu29.legotv.common.view.BaseActivity;
import mu29.legotv.data.model.Program;

/**
 * InJung Chung
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById(R.id.rv_program) RecyclerView programRV;
    @ViewById(R.id.ad_bar_google) AdView googleAdBar;

    private VideoAction videoAction;
    private ProgramAdapter adapter;

    @Override
    @AfterViews
    protected void setView() {
        super.setView();
        videoAction = new VideoAction(getDispatcher());
        adapter = new ProgramAdapter();
        adapter.setListener(new AdapterListener());
        programRV.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        programRV.setHasFixedSize(true);
        programRV.setLayoutManager(manager);
        initBarAd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressView();
        videoAction.fetchProgramList();
    }

    @Override
    protected void consume(Action action) {
        super.consume(action);
        switch (action.getType()) {
            case VideoAction.PROGRAM_LIST_SUCCESS:
                adapter.clear();
                adapter.addAll(action.get("programs"), ProgramAdapter.TYPE_PROGRAM);
                hideProgressView();
                break;
            case VideoAction.PROGRAM_LIST_FAIL:
                break;
        }
    }

    // 바 광고 세팅
    private void initBarAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        googleAdBar.loadAd(adRequest);
        googleAdBar.setVisibility(View.VISIBLE);
    }

    class AdapterListener implements DefaultListener {

        @Override
        public void onAction(int actionId, Object... params) {
            switch (actionId) {
                case SELECT_ITEM:
                    Program item = (Program) params[0];
                    Intent intent = new Intent(getApplicationContext(), VideoListActivity_.class);
                    intent.putExtra("label", item.label);
                    startActivity(intent);
                    break;
            }
        }

    }

}
