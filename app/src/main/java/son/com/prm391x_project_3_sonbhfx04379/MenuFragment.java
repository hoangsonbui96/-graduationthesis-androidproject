package son.com.prm391x_project_2_sonbhfx04379;


import android.content.Context;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;



public class MenuFragment extends Fragment {
    private Context mContext;
    private RecyclerView rvAnimal;
    private ArrayList<Animal> listAnimals;
    private DrawerLayout mDrawer;
    private String animalType = "";
    private Animal animal;
    RecyclerView.LayoutManager layoutManager;

    public void setPathName(String animalType){
        this.animalType = animalType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, container, false);
        initView(v);
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initView(View v) {
        mDrawer = v.findViewById(R.id.drawer);
        rvAnimal = v.findViewById(R.id.rv_animals);
        //Xử lý mở menu trái
        v.findViewById(R.id.iv_menu).setOnClickListener(v12 -> mDrawer.openDrawer(GravityCompat.START));

        if(!animalType.equals("")){
            showAnimals(animalType);
        }

        //Hiển thị ảnh động vật biển
        v.findViewById(R.id.iv_sea).setOnClickListener(v1 -> {
            v.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
            showAnimals("sea");

        });

        //Hiển thị ảnh động vật có vú
        v.findViewById(R.id.iv_mammal).setOnClickListener(v1 -> {
            v.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
            showAnimals("mammal");

        });

        //Hiển thị ảnh chim muông
        v.findViewById(R.id.iv_bird).setOnClickListener(v1 -> {
            v.startAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
            showAnimals("bird");
        });
    }

    public void showAnimals(String animalType) {
        listAnimals = new ArrayList<>();
        SharedPreferences pref = mContext.getSharedPreferences("Save_file", Context.MODE_PRIVATE);

        try{
            String[] list = mContext.getAssets().list(animalType);
            for(String photo : list){
                Bitmap photoB = BitmapFactory.decodeStream(mContext.getAssets().open(animalType + "/" + photo));
                Bitmap photoBG = null;

                String name = photo;
                name = name.replace("ic_", "");
                name = name.substring(0, name.indexOf("."));
                String fileText = "description/" + animalType + "/des_" + name + ".txt";
                InputStream is = mContext.getAssets().open(fileText);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String content = reader.readLine();
                boolean isLove = mContext.getSharedPreferences("Save_file", Context.MODE_PRIVATE).getBoolean(name, false);
                Animal animal = new Animal(animalType, photoB, photoBG, name, content, isLove);
                listAnimals.add(animal);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        //Hiển thị danh sách ảnh lên RecyclerView
        layoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL,false);
        rvAnimal.setLayoutManager(layoutManager);
        AnimalAdapter animalAdapter = new AnimalAdapter(mContext,listAnimals);
        rvAnimal.setAdapter(animalAdapter);
        mDrawer.closeDrawers();
    }

}
