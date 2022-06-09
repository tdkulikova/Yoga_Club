package com.example.yogaclub.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yogaclub.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment2() {
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
    public static BlankFragment2 newInstance(String param1, String param2) {
        BlankFragment2 fragment = new BlankFragment2();
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


    ArrayList<Meditation> recordings = new ArrayList<Meditation>();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_blank2, container,
                false);
        // начальная инициализация списка
        setInitialData();
        RecyclerView recyclerView = rootView.findViewById(R.id.recycleFirst);
        // создаем адаптерrecycle
        MeditationAdapter adapter = new MeditationAdapter(getContext(), recordings);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layout);
        recyclerView.addItemDecoration(new SpacesItemDecoration(50));
        return rootView;
        //return inflater.inflate(R.layout.fragment_blank3, container, false);
    }

    private void setInitialData() {
        recordings.add(new Meditation("Body Scan meditation", R.drawable.bodyscan, R.raw.bodyscan));
        recordings.add(new Meditation("Breathing together", R.drawable.breathing, R.raw.breathingtogether));
        recordings.add(new Meditation("Listening meditation", R.drawable.listening, R.raw.listening));
        recordings.add(new Meditation("Loving kindness meditation", R.drawable.kindness, R.raw.lovingkindness));
        recordings.add(new Meditation("Mountain meditation", R.drawable.mountain, R.raw.mountain));
    }

    public static class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.ViewHolder> implements com.example.yogaclub.ui.home.MeditationAdapter {
        private LayoutInflater inflater;
        private ArrayList<Meditation> meditationList;

        MeditationAdapter(Context context, ArrayList<Meditation> recordings) {
            this.meditationList = recordings;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public MeditationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_item2, parent, false);
            return new MeditationAdapter.ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(MeditationAdapter.ViewHolder viewHolder, int position) {
            Meditation recording = meditationList.get(position);
            viewHolder.nameView.setText(recording.getName());
            viewHolder.icon.setImageResource(recording.getImageResource());
            viewHolder.playButton.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            viewHolder.stopButton.setImageResource(R.drawable.ic_baseline_pause_24);
            MediaPlayer mediaPlayer = MediaPlayer.create(inflater.getContext(), recording.getAudio());
            viewHolder.stopButton.setOnClickListener(new View.OnClickListener() {
                int length;
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        length = mediaPlayer.getCurrentPosition();
                        mediaPlayer.seekTo(length);
                    }
                }
            });
            viewHolder.playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.start();
                }
            });
            viewHolder.pauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mediaPlayer.isPlaying())
                    {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return meditationList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            final ImageButton playButton, stopButton, pauseButton;
            final TextView nameView;
            final ImageView icon;

            ViewHolder(View view) {
                super(view);
                playButton = view.findViewById(R.id.imageButtonBlank2);
                stopButton = view.findViewById(R.id.imageButtonBlank3);
                pauseButton = view.findViewById(R.id.imageButtonBlank4);
                nameView = view.findViewById(R.id.textViewFirst);
                icon = view.findViewById(R.id.imageViewFirst);
            }
        }
    }

    public class Meditation {

        private String name; // название
        private int imageResource; // ресурс флага
        private int audio;

        public Meditation(String name, int image, int audio) {

            this.name = name;
            this.imageResource = image;
            this.audio = audio;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAudio() {
            return this.audio;
        }

        public void setAudioResource(int flagResource) {
            this.audio = flagResource;
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