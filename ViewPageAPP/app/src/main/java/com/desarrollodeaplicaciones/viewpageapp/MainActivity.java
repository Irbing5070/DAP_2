package com.desarrollodeaplicaciones.viewpageapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] ropa = {"imagen 1", "imagen 2", "imagen 3", "imagen 4"};
    int[] imagenes ={R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};

    ManejadorGaleria manejadorCaleria;
    ViewPager mViewpager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Lista
        ListView lista = (ListView)findViewById(R.id.listView1);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_expandable_list_item_1,ropa);

        lista.setAdapter(adapter);

        //Galeria de imagenes
        manejadorCaleria = new ManejadorGaleria(getSupportFragmentManager());

        mViewpager = (ViewPager)findViewById(R.id.pager);
        manejadorCaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[0]));
        manejadorCaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[1]));
        manejadorCaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[2]));
        manejadorCaleria.agregarFragmentos(FragmentosImagenes.newInstance(imagenes[3]));
        mViewpager.setAdapter(manejadorCaleria);

    }

    public class ManejadorGaleria extends FragmentPagerAdapter {

        List<Fragment> fragmentos;
        public ManejadorGaleria(FragmentManager fm){
            super(fm);
            fragmentos = new ArrayList();
        }

        public void agregarFragmentos(Fragment xfragmento){
            fragmentos.add(xfragmento);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }
    }

    public static class FragmentosImagenes extends Fragment{

        private static final String ARG_IMAGE = "imagen";
        private int imagen;

        public static FragmentosImagenes newInstance (int imagen){
            FragmentosImagenes fragment = new FragmentosImagenes();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE,imagen);
            fragment.setArguments(args);
            fragment.setRetainInstance(true);
            return fragment;
        }


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if(getArguments() != null){
                imagen = getArguments().getInt(ARG_IMAGE);
            }
        }

        public FragmentosImagenes(){

        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment, container, false);

            ImageView imagenView =(ImageView) rootView.findViewById(R.id.imageView1);
            imagenView.setImageResource(imagen);

            return rootView;
        }

    }
}
