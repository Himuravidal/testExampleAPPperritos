package com.criss.perritoapp.vista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.criss.perritoapp.R;

import java.util.ArrayList;
import java.util.List;

public class BreedImageListFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private List<String> mParam1;

    public BreedImageListFragment() {
        // Required empty public constructor
    }

    public static BreedImageListFragment newInstance(List<String> urlList) {
        BreedImageListFragment fragment = new BreedImageListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList<String>) urlList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breed_image_list, container, false);
        TextView textViewImages = view.findViewById(R.id.textViewImages);
        textViewImages.setText(mParam1.get(0).toString());
        return view;
    }
}
