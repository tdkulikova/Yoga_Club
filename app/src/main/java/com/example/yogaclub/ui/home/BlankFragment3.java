package com.example.yogaclub.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.yogaclub.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment3 newInstance(String param1, String param2) {
        BlankFragment3 fragment = new BlankFragment3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ArrayList<Image> images = new ArrayList<Image>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_item3, container,
                false);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = rootView.findViewById(R.id.recycle);
        // создаем адаптер
        StateAdapter adapter = new StateAdapter(getContext(), images);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new SpacesItemDecoration(50));
        return rootView;
        //return inflater.inflate(R.layout.fragment_blank3, container, false);
    }
    private void setInitialData(){
        images.add(new Image ("Young moon position", R.drawable.young_moon));
        images.add(new Image ("Snake position", R.drawable.snake));
        images.add(new Image ("Shiva position", R.drawable.shiva));
        images.add(new Image ("Lateral traction", R.drawable.lateral_traction));
        images.add(new Image ("Half moon position", R.drawable.halfmoon));
        images.add(new Image ("Freedom position", R.drawable.freedom));
        images.add(new Image ("Fish position", R.drawable.fish));
        images.add(new Image ("Dog with the head down position", R.drawable.dog));
        images.add(new Image ("Child position", R.drawable.child));
        images.add(new Image ("Chair position", R.drawable.chair));
        images.add(new Image ("Cat position", R.drawable.cat));
        images.add(new Image ("Butterfly position", R.drawable.butterfly));
        images.add(new Image ("Bow position", R.drawable.bow));
    }
    public static class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

        private final LayoutInflater inflater;
        private final List<Image> states;

        StateAdapter(Context context, List<Image> states) {
            this.states = states;
            this.inflater = LayoutInflater.from(context);
        }
        @Override
        public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = inflater.inflate(R.layout.list_item3, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
            Image state = states.get(position);
            holder.imageView.setImageResource(state.getImageResource());
            holder.nameView.setText(state.getName());
        }

        @Override
        public int getItemCount() {
            return states.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView imageView;
            final TextView nameView;
            ViewHolder(View view){
                super(view);
                imageView = view.findViewById(R.id.imageView);
                nameView = view.findViewById(R.id.textView2);
            }
        }
    }

    public class Image {

        private String name; // название
        private int imageResource; // ресурс флага

        public Image(String name, int image){

            this.name=name;
            this.imageResource=image;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getImageResource() {
            return this.imageResource;
        }

        public void setFlagResource(int flagResource) {
            this.imageResource = flagResource;
        }
    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration
    {
        private int space;
        public SpacesItemDecoration(int space)
        {
            this.space = space;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            //добавить переданное кол-во пикселей отступа снизу
            outRect.bottom = space;
        }
    }


}