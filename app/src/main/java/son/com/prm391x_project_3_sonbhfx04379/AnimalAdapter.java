package son.com.prm391x_project_2_sonbhfx04379;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.ViewHolder> {

    private ArrayList<Animal> animals;
    private final Context mContext;
    ItemClick activity;

    public interface ItemClick{
        void onItemClicked(int index);
    }

    public AnimalAdapter(Context context, ArrayList<Animal> list){
            this.mContext = context;
            animals = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivAnimal;
        ImageView ivFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnimal = itemView.findViewById(R.id.iv_animal);
            ivFav = itemView.findViewById(R.id.iv_fav);

            //Viết hàm chuyển sang màn hình chi tiết
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity)mContext).gotoDetailAnimal(animals, (Animal)ivAnimal.getTag());
                }
            });
        }
    }

    @NonNull
    @Override
    public AnimalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_animal, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.ViewHolder viewHolder, int position) {
        viewHolder.ivAnimal.setTag(animals.get(position));
        viewHolder.ivAnimal.setImageBitmap(animals.get(position).getPhoto());
        if(animals.get(position).isFav() == false){
            viewHolder.ivFav.setImageResource(R.drawable.ic_favorite_border);
        }else{
            viewHolder.ivFav.setImageResource(R.drawable.ic_favorited);
        }

    }

    @Override
    public int getItemCount() {
        return animals.size();
    }
}
