package com.marwaeltayeb.movietrailer.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.marwaeltayeb.movietrailer.models.Movie;

import java.util.List;

public class MovieRepository {

    private MovieDao mMovieDao;
    private LiveData<List<Movie>> mAllMovies;

    MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllMovies = mMovieDao.getAllMovies();
    }

    LiveData<List<Movie>> getAllMovies() {
        return mAllMovies;
    }

    public void insert(Movie movie) {
        new InsertAsyncTask(mMovieDao).execute(movie);
    }

    private static class InsertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        InsertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void delete(Movie movie) {
        new DeleteAsyncTask(mMovieDao).execute(movie);
    }

    private static class DeleteAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDao mAsyncTaskDao;

        DeleteAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
