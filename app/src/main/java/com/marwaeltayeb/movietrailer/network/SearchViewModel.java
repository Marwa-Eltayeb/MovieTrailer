package com.marwaeltayeb.movietrailer.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.marwaeltayeb.movietrailer.models.Movie;

import java.util.List;

public class SearchViewModel extends ViewModel {

    private final SearchRepository searchRepository;

    public SearchViewModel() {
        searchRepository = new SearchRepository();
    }

    public LiveData<List<Movie>> getSearchedMovies(String query) {
        return searchRepository.getMutableLiveData(query);
    }
}
