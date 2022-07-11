package me.dio.soccernews.ui.news;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import me.dio.soccernews.data.local.AppDatabase;
import me.dio.soccernews.databinding.FragmentNewsBinding;
import me.dio.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;
    private   AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel NewsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "soccer-news").build();



        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        NewsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
binding.rvNews.setAdapter(new NewsAdapter(news ,upDatedNews -> {

    //db.newsDao().insert(upDatedNews); Comando insert n funciona a aula estava imcompleta
}));

        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}