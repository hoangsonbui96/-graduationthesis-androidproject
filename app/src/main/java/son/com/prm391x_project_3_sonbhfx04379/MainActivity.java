package son.com.prm391x_project_2_sonbhfx04379;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String animalType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_PHONE_STATE
            }, 101);
        };

        if(checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.READ_CALL_LOG
            },1);
        }
        initViews();
    }
    private void initViews() {
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ln_main, menuFragment, null).commit();
    }

    public void gotoListAnimal(String animalType) {
        this.animalType = animalType;
        MenuFragment frg = new MenuFragment();
        frg.setPathName(animalType);
        showFrg(frg);
    }

    public void gotoDetailAnimal(ArrayList<Animal> listAnimals, Animal animal) {

        AnimalDetailsFrag frg = new AnimalDetailsFrag();
        frg.setData(animalType,listAnimals, animal);
        showFrg(frg);
        Toast.makeText(this, animal.getName() + animal.getPhoto(), Toast.LENGTH_SHORT).show();
    }

    private void showFrg(Fragment frg) {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }


}