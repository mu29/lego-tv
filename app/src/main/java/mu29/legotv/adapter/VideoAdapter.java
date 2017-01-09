package mu29.legotv.adapter;

import android.view.ViewGroup;

import mu29.legotv.data.model.Program;
import mu29.legotv.view.holder.DefaultViewHolder;
import mu29.legotv.view.holder.VideoViewHolder;

/**
 * InJung Chung
 */

public class VideoAdapter extends DefaultAdapter<Program> {

    public static final int TYPE_VIDEO = 0;

    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_VIDEO:
                return VideoViewHolder.newInstance(parent);
        }
        return null;
    }

}