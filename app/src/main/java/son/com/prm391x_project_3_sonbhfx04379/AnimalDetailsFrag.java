package son.com.prm391x_project_2_sonbhfx04379;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class AnimalDetailsFrag extends Fragment {
    private ArrayList<Animal> listAnimals;
    private Animal currentAnimal;
    private Context mContext;
    private String animalType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_animal_details, container, false);
        initViews(rootView);
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }



    private void initViews(View v) {
        ImageView backbtn = v.findViewById(R.id.iv_menu);
        backbtn.setImageResource(R.drawable.ic_back);

        backbtn.setOnClickListener(v1 -> gotoListAnimal(currentAnimal.getPath()));

        ViewPager vp = v.findViewById(R.id.vp_animal);
        DetailAnimalAdapter adapter = new DetailAnimalAdapter(listAnimals, mContext);
        vp.setAdapter(adapter);
        vp.setCurrentItem(listAnimals.indexOf(currentAnimal), true);
    }

    private void gotoListAnimal(String path) {
        ((MainActivity) getActivity()).gotoListAnimal(path);
    }


//    private void gotoListAnimal(ArrayList<Animal> listAnimals) {
//        ((MainActivity) getActivity()).gotoListAnimal(listAnimals);
//    }

    public void setData(String animalType,ArrayList<Animal> listAnimals, Animal currentAnimal) {
        this.animalType = animalType;
        this.listAnimals = listAnimals;
        this.currentAnimal = currentAnimal;

    }
}