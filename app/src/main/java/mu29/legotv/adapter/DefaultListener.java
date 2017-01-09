package mu29.legotv.adapter;

/**
 * InJung Chung
 */

public interface DefaultListener {

    int SELECT_ITEM = 0;

    void onAction(int actionId, Object... params);

}