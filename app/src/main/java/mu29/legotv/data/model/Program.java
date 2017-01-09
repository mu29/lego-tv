package mu29.legotv.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

/**
 * InJung Chung
 */

public class Program {

    public String label;
    public String img;
    @SerializedName("videos")
    public List<Video> videos;

    public String imageUrl() {
        return String.format(Locale.KOREA, "http://i.ytimg.com/vi/%s/default.jpg", img);
    }

}
