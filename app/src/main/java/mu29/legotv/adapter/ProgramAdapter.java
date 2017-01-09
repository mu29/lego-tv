package mu29.legotv.adapter;

import android.view.ViewGroup;

import mu29.legotv.data.model.Program;
import mu29.legotv.view.holder.DefaultViewHolder;
import mu29.legotv.view.holder.ProgramViewHolder;

/**
 * InJung Chung
 */

public class ProgramAdapter extends DefaultAdapter<Program> {

    public static final int TYPE_PROGRAM = 0;

    @Override
    public DefaultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_PROGRAM:
                return ProgramViewHolder.newInstance(parent);
        }
        return null;
    }

}