package mu29.legotv.adapter;

/**
 * InJung Chung
 */

public class AdapterItem<T> {

    private T item;
    private int viewType;

    public AdapterItem(T item, int viewType) {
        this.item = item;
        this.viewType = viewType;
    }

    public static <T> AdapterItem<T> create(T item, int itemViewType) {
        return new AdapterItem<>(item, itemViewType);
    }

    public T getItem() {
        return item;
    }

    int getViewType() {
        return viewType;
    }

}