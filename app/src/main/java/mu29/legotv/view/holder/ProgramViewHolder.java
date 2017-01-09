package mu29.legotv.view.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import mu29.legotv.R;
import mu29.legotv.adapter.DefaultListener;
import mu29.legotv.data.model.Program;

/**
 * InJung Chung
 */

public class ProgramViewHolder extends DefaultViewHolder<Program> {

    private ViewGroup wrapper;
    private TextView titleTV;
    private TextView countTV;
    private ImageView imageIV;

    public static ProgramViewHolder newInstance(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_program, parent, false);
        return new ProgramViewHolder(view);
    }

    private ProgramViewHolder(View view) {
        super(view);
        wrapper = (ViewGroup) view.findViewById(R.id.wrapper);
        titleTV = (TextView) view.findViewById(R.id.tv_title);
        countTV = (TextView) view.findViewById(R.id.tv_count);
        imageIV = (ImageView) view.findViewById(R.id.iv_thumbnail);
    }

    @Override
    public void onBindView(Program item, DefaultListener listener) {
        titleTV.setText(String.format(Locale.KOREA, "레고® %s", item.label));
        countTV.setText(String.format(Locale.KOREA, "%d개의 영상", item.videos.size()));

        Picasso.with(context)
            .load(item.imageUrl())
            .error(R.mipmap.ic_launcher)
            .centerCrop()
            .into(imageIV);

        if (listener == null) return;
        wrapper.setOnClickListener(v -> listener.onAction(DefaultListener.SELECT_ITEM, item));
    }

}
