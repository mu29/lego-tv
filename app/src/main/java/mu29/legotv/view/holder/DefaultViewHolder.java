package mu29.legotv.view.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import mu29.legotv.adapter.DefaultListener;

/**
 * InJung Chung
 */

public abstract class DefaultViewHolder<I> extends RecyclerView.ViewHolder {

    protected Context context;

    protected DefaultViewHolder(View view) {
        super(view);
        context = view.getContext();
    }

    public abstract void onBindView(I item, DefaultListener listener);

}
