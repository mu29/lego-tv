package mu29.legotv.view;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.youtube.player.YouTubeApiServiceUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import mu29.legotv.R;
import mu29.legotv.action.VideoAction;
import mu29.legotv.adapter.DefaultListener;
import mu29.legotv.adapter.VideoAdapter;
import mu29.legotv.common.flux.Action;
import mu29.legotv.common.view.BaseActivity;
import mu29.legotv.data.local.PreferencesHelper;
import mu29.legotv.data.model.Program;
import mu29.legotv.data.model.Video;

/**
 * InJung Chung
 */

@EActivity(R.layout.activity_video_list)
public class VideoListActivity extends BaseActivity {

    private static final int REQ_START_STANDALONE_PLAYER = 1;
    private static final int REQ_RESOLVE_SERVICE_MISSING = 2;

    @ViewById(R.id.tv_title) TextView titleTV;
    @ViewById(R.id.rv_video) RecyclerView videoRV;
    @ViewById(R.id.ad_bar_google) AdView googleAdBar;
    @Extra("label") String label;

    private VideoAction videoAction;
    private VideoAdapter adapter;
    private InterstitialAd mGoogleFullAd;
    private static String mVideoId;

    @Override
    @AfterViews
    protected void setView() {
        super.setView();
        videoAction = new VideoAction(getDispatcher());
        titleTV.setText(label);
        adapter = new VideoAdapter();
        adapter.setListener(new AdapterListener());
        videoRV.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        videoRV.setHasFixedSize(true);
        videoRV.setLayoutManager(manager);
        initBarAd();
        initFullAd();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgressView();
        videoAction.fetchProgramList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_START_STANDALONE_PLAYER && resultCode != RESULT_OK) {
            YouTubeInitializationResult errorReason = YouTubeStandalonePlayer.getReturnedInitializationResult(data);
            showSnackBar(errorReason.toString());
        } else {
            PreferencesHelper.getInstance(getApplicationContext()).setString(mVideoId, "READ");
            adapter.notifyDataSetChanged();
        }

        mVideoId = "";
        mGoogleFullAd.show();
    }

    public void openFullAd(String videoId) {
        mVideoId = videoId;
        if (mGoogleFullAd.isLoaded()) {
            mGoogleFullAd.show();
        } else {
            if (!mVideoId.isEmpty())
                openVideoActivity(mVideoId);
        }
    }

    private void openVideoActivity(String videoId) {
        Intent intent = YouTubeStandalonePlayer.createVideoIntent(this, "AIzaSyBg6bvz3co156Xt11NM9Lcd1zhpGTOuUKo", videoId, 0, true, false);
        if (intent == null)
            return;

        if (!canResolveIntent(intent) || !YouTubeApiServiceUtil.isYouTubeApiServiceAvailable(getApplicationContext()).equals(YouTubeInitializationResult.SUCCESS)) {
            YouTubeInitializationResult.SERVICE_MISSING.getErrorDialog(this, REQ_RESOLVE_SERVICE_MISSING).show();
            return;
        }

        startActivityForResult(intent, REQ_START_STANDALONE_PLAYER);
    }

    private boolean canResolveIntent(Intent intent) {
        List<ResolveInfo> resolveInfo = getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfo != null && !resolveInfo.isEmpty();
    }

    // 바 광고 세팅
    private void initBarAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        googleAdBar.loadAd(adRequest);
        googleAdBar.setVisibility(View.VISIBLE);
    }

    // 전면 광고 세팅
    private void initFullAd() {
        mGoogleFullAd = new InterstitialAd(this);
        mGoogleFullAd.setAdUnitId(getResources().getString(R.string.google_full_ad));
        requestGoogleFullAd();

        mGoogleFullAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestGoogleFullAd();
                if (!mVideoId.isEmpty())
                    openVideoActivity(mVideoId);
            }
        });

    }

    private void requestGoogleFullAd() {
        AdRequest adRequest = new AdRequest.Builder()
            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
            .build();

        mGoogleFullAd.loadAd(adRequest);
    }

    @Override
    protected void consume(Action action) {
        super.consume(action);
        switch (action.getType()) {
            case VideoAction.PROGRAM_LIST_SUCCESS:
                hideProgressView();
                adapter.clear();
                List<Program> programs = action.get("programs");
                for (Program program : programs) {
                    if (program.label.equals(label)) {
                        adapter.addAll(program.videos, VideoAdapter.TYPE_VIDEO);
                        break;
                    }
                }
                hideProgressView();
                break;
        }
    }

    class AdapterListener implements DefaultListener {

        @Override
        public void onAction(int actionId, Object... params) {
            switch (actionId) {
                case SELECT_ITEM:
                    Video video = (Video) params[0];
                    openFullAd(video.videoId);
                    break;
            }
        }

    }

}
