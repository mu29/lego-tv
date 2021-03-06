package mu29.legotv.common.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import mu29.legotv.R;
import mu29.legotv.common.App;
import mu29.legotv.common.flux.Action;
import mu29.legotv.common.flux.Dispatcher;
import rx.Observable;
import rx.Subscription;

/**
 * InJung Chung
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Subscription subscription;
    private View progressView;

    @Override
    protected void onResume() {
        super.onResume();
        Observable<Action> actionObservable = getDispatcher().getObservable();
        subscription = actionObservable.subscribe(this::consume, e -> e.printStackTrace());
    }

    @Override
    protected void onPause() {
        super.onPause();
        subscription.unsubscribe();
    }

    protected void setView() {
        progressView = findViewById(R.id.view_progress);
    }

    protected void showProgressView() {
        if (progressView == null) return;
        progressView.setVisibility(View.VISIBLE);
    }

    protected void hideProgressView() {
        if (progressView == null) return;
        progressView.setVisibility(View.GONE);
    }

    protected void consume(Action action) {
        switch (action.getType()) {

        }
    }

    protected void showSnackBar(String message) {
        View view = findViewById(R.id.wrapper);
        if (view == null) return;
        showSnackBar(view, message);
    }

    protected void showSnackBar(View view, String message) {
        Snackbar bar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        bar.show();
    }

    protected Dispatcher getDispatcher() {
        return ((App) getApplicationContext()).getDispatcher();
    }

}

