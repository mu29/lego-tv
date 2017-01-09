package mu29.legotv.data.model;

import java.util.Locale;

/**
 * InJung Chung
 */

public class Video {

    public String title;
    public String videoId;
    public String minute;

    public String thumbnailUrl() {
        return String.format(Locale.KOREA, "http://i.ytimg.com/vi/%s/default.jpg", videoId);
    }

}
