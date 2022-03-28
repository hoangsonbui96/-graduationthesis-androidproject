package son.com.prm391x_project_2_sonbhfx04379;

import android.graphics.Bitmap;

public class Animal {
    private final Bitmap photo;
    private final Bitmap photoBg;
    private final String path;
    private final String name;
    private final String content;
    private boolean isFav;

    public void setFav(boolean fav) {
        isFav = fav;
    }

    public boolean isFav() {
        return isFav;
    }

    public Animal(String path, Bitmap photo, Bitmap photoBg, String name, String content, boolean isFav) {
        this.path = path;
        this.photo = photo;
        this.photoBg = photoBg;
        this.name = name;
        this.isFav = isFav;
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public Bitmap getPhotoBg() {
        return photoBg;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

}
