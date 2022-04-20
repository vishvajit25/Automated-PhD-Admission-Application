package com.example.college_phd.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.college_phd.MainActivity_teacher;
import com.example.college_phd.R;

public class GalleryFragment extends Fragment {

    ImageView prev;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        View v = null;
        v = inflater.inflate(R.layout.fragment_gallery, container, false);
        prev = v.findViewById(R.id.prevbutton);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), MainActivity_teacher.class);
                startActivity(i);


            }
        });
        return v;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}