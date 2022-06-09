package com.example.yogaclub.ui.slideshow;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.yogaclub.R;
import com.example.yogaclub.databinding.FragmentSlideshowBinding;
import com.example.yogaclub.ui.gallery.Friends;
import com.example.yogaclub.ui.gallery.MyCursorAdapter;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageView image = root.findViewById(R.id.imageView3);
        image.setImageResource(R.mipmap.ic_launcher);
        Button edit = root.findViewById(R.id.button3);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new
                        AlertDialog.Builder(getContext());
                dialog.setMessage("Editing account settings");
                dialog.setTitle("Enter account parameters");
                LayoutInflater inflater = new
                        LayoutInflater(getContext()) {
                            @Override
                            public LayoutInflater cloneInContext(Context context) {
                                return null;
                            }
                        };
                View dialogView =
                        getLayoutInflater().inflate(R.layout.dialog_edit, null);
                TextView textEmail = root.findViewById(R.id.email);
                TextView textVk = root.findViewById(R.id.vk);
                TextView textTg = root.findViewById(R.id.tg);
                TextView textWeight = root.findViewById(R.id.weight);
                TextView textHeight = root.findViewById(R.id.height);
                TextView textAge= root.findViewById(R.id.age);
                TextView textLevel = root.findViewById(R.id.level);
                TextView textYogas = root.findViewById(R.id.yogas);
                TextView textWorkouts = root.findViewById(R.id.freq);
                EditText emailEdit = dialogView.findViewById(R.id.emailEdit);
                EditText vkEdit = dialogView.findViewById(R.id.vkEdit);
                EditText tgEdit = dialogView.findViewById(R.id.tgEdit);
                EditText weightEdit = dialogView.findViewById(R.id.weightEdit);
                EditText heightEdit = dialogView.findViewById(R.id.heightEdit);
                EditText ageEdit = dialogView.findViewById(R.id.ageEdit);
                EditText levelEdit = dialogView.findViewById(R.id.levelEdit);
                EditText yogasEdit = dialogView.findViewById(R.id.yogasEdit);
                EditText workoutEdit = dialogView.findViewById(R.id.freqEdit);
                dialog.setView(dialogView);
                EditText editName = dialogView.findViewById(R.id.editTextName);
                EditText editPhone = dialogView.findViewById(R.id.editTextPhone);
                emailEdit.setText(textEmail.getText());
                vkEdit.setText(textVk.getText());
                tgEdit.setText(textTg.getText());
                weightEdit.setText(textWeight.getText());
                heightEdit.setText(textHeight.getText());
                ageEdit.setText(textAge.getText());
                levelEdit.setText(textLevel.getText());
                yogasEdit.setText(textYogas.getText());
                workoutEdit.setText(textWorkouts.getText());
                dialog.setNeutralButton("OK", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int i) {
                                textEmail.setText(emailEdit.getText());
                                textVk.setText(vkEdit.getText());
                                textTg.setText(tgEdit.getText());
                                textWeight.setText(weightEdit.getText());
                                textHeight.setText(heightEdit.getText());
                                textAge.setText(ageEdit.getText());
                                textLevel.setText(levelEdit.getText());
                                textYogas.setText(yogasEdit.getText());
                                textWorkouts.setText(workoutEdit.getText());

                            }
                        });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();

            }
        });

        //inal TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}