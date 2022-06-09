package com.example.yogaclub.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yogaclub.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment4.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment1 newInstance(String param1, String param2) {
        BlankFragment1 fragment = new BlankFragment1();
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


    ArrayList<Video> videos = new ArrayList<Video>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank1, container,
                false);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = rootView.findViewById(R.id.recycleFirst);
        // создаем адаптерrecycle
        VideoAdapter adapter = new VideoAdapter(getContext(), videos);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new SpacesItemDecoration(50));
        return rootView;
        //return inflater.inflate(R.layout.fragment_blank3, container, false);
    }

    private void setInitialData() {
        videos.add(new Video("Morning Yoga", R.drawable.morningyoga, R.raw.morningyoga));
        videos.add(new Video("Yoga for all body", R.drawable.yogaforallbody, R.raw.allbodyyoga));
        videos.add(new Video("Relaxing Yoga", R.drawable.relaxingyoga, R.raw.relaxingyoga));
        videos.add(new Video("Evening Yoga", R.drawable.eveningyoga, R.raw.eveningyoga));
        videos.add(new Video("Energetic Yoga", R.drawable.energeticyoga, R.raw.energeticyoga));
    }

    public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
        private LayoutInflater inflater;
        private ArrayList<Video> videoList;

        VideoAdapter(Context context, ArrayList<Video> videos) {
            this.videoList = videos;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_item1, parent, false);
            return new VideoAdapter.ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(VideoAdapter.ViewHolder viewHolder, int position) {
            Video video = videoList.get(position);
            viewHolder.nameView.setText(video.getName());
            viewHolder.icon.setImageResource(video.getImageResource());
            viewHolder.startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), MainActivityVideo.class);
                    MainActivityVideo.setVideo(video.getVideo());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return videoList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final Button startButton;
            final TextView nameView;
            final ImageView icon;

            ViewHolder(View view) {
                super(view);
                startButton = view.findViewById(R.id.button);
                nameView = view.findViewById(R.id.textViewFirst);
                icon = view.findViewById(R.id.imageViewFirst);
            }
        }
    }

    public class Video {

        private String name; // название
        private int imageResource; // ресурс флага
        private int video;

        public Video(String name, int image, int video) {
            this.name = name;
            this.imageResource = image;
            this.video = video;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVideo() {
            return this.video;
        }

        public void setVideoResource(int flagResource) {
            this.video = flagResource;
        }

        public int getImageResource() {
            return this.imageResource;
        }

        public void setButtonPlayResource(int flagResource) {
            this.imageResource = flagResource;
        }

    }

    public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //добавить переданное кол-во пикселей отступа снизу
            outRect.bottom = space;
        }
    }
}