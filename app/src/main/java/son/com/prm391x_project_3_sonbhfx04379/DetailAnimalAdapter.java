package son.com.prm391x_project_2_sonbhfx04379;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class DetailAnimalAdapter extends PagerAdapter {
    private ArrayList<Animal> listAnimals;
    private Context mContext;


    public DetailAnimalAdapter(ArrayList<Animal> listAnimals, Context mContext){
        this.listAnimals = listAnimals;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.animal_details, container, false);
        Animal item = listAnimals.get(position);

        ImageView animal_Bg = view.findViewById(R.id.imagebg);
        TextView animal_Title = view.findViewById(R.id.tv_animal_name);
        ImageView isFav = view.findViewById(R.id.iv_favorite);
        TextView ani_des = view.findViewById(R.id.tv_animal_desc);

        SharedPreferences pref = mContext.getSharedPreferences("Save_file", Context.MODE_PRIVATE);

        animal_Bg.setImageBitmap(item.getPhoto());
        animal_Title.setText(item.getName());
        if(item.isFav() == false){
            isFav.setImageResource(R.drawable.ic_favorite_border);
        }else{
            isFav.setImageResource(R.drawable.ic_favorited);
        }

        isFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isFav() == false){
                    isFav.setImageResource(R.drawable.ic_favorited);
                    pref.edit().putBoolean(item.getName(),true).apply();
                    item.setFav(true);
                }else{
                    isFav.setImageResource(R.drawable.ic_favorite_border);
                    pref.edit().putBoolean(item.getName(),false).apply();
                    item.setFav(false);
                }
            }
        });

        ani_des.setText(item.getContent());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return listAnimals.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    public void destroyItem(@NonNull ViewGroup viewPager, int position, @NonNull Object object) {
        viewPager.removeView((View) object);
    }
}
