package mu29.legotv.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mu29.legotv.view.holder.DefaultViewHolder;

/**
 * InJung Chung
 */

@SuppressWarnings("unchecked")
public abstract class DefaultAdapter<T> extends RecyclerView.Adapter<DefaultViewHolder> {

    private DefaultListener listener;
    private List<AdapterItem<?>> items = new ArrayList<>();

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.onBindView(items.get(position).getItem(), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (items.size() <= position)
            return items.get(items.size() - 1).getViewType();

        return items.get(position).getViewType();
    }

    public void setListener(DefaultListener listener) {
        this.listener = listener;
    }

    public void addAll(List<?> items, int viewType) {
        for (Object item : items) {
            this.items.add(AdapterItem.create(item, viewType));
        }
        notifyDataSetChanged();
    }

    public void add(AdapterItem<?> item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public T getLastItem() {
        if (items.size() == 0)
            return null;
        return (T) items.get(items.size() - 1).getItem();
    }

    public T getItem(int position) {
        return (T) items.get(position).getItem();
    }

}
