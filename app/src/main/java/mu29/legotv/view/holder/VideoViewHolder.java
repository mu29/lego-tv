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
import mu29.legotv.data.local.PreferencesHelper;
import mu29.legotv.data.model.Video;
import mu29.legotv.util.CircleTransform;

/**
 * InJung Chung
 */

public class VideoViewHolder extends DefaultViewHolder<Video> {

    private ViewGroup wrapper;
    private TextView titleTV;
    private TextView minuteTV;
    private View readView;
    private ImageView thumbnailIV;

    public static VideoViewHolder newInstance(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    private VideoViewHolder(View view) {
        super(view);
        wrapper = (ViewGroup) view.findViewById(R.id.wrapper);
        titleTV = (TextView) view.findViewById(R.id.tv_title);
        minuteTV = (TextView) view.findViewById(R.id.tv_minute);
        readView = view.findViewById(R.id.view_read);
        thumbnailIV = (ImageView) view.findViewById(R.id.iv_thumbnail);
    }

    @Override
    public void onBindView(Video item, DefaultListener listener) {
        titleTV.setText(String.format(Locale.KOREA, "레고® %s", item.title));
        minuteTV.setText(String.format(Locale.KOREA, "%s", item.minute));
        boolean read = PreferencesHelper.getInstance(context).getString(item.videoId).equals("READ");
        readView.setVisibility(read ? View.VISIBLE : View.INVISIBLE);

        Picasso.with(context)
            .load(item.thumbnailUrl())
            .error(R.mipmap.ic_launcher)
            .centerCrop()
            .transform(new CircleTransform()).fit().centerCrop()
            .into(thumbnailIV);

        if (listener == null) return;
        wrapper.setOnClickListener(v -> listener.onAction(DefaultListener.SELECT_ITEM, item));
    }

}
