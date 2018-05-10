package com.example.farzadfarshad.adeiye.Movie;

import com.example.farzadfarshad.adeiye.Movie.MovieFragment;
import com.example.farzadfarshad.adeiye.Movie.MovieModule;


import dagger.Component;

/**
 * Created by farshad&farzad on 4/24/2018.
 */
@Component(modules ={MovieModule.class})
public interface MovieFragmentComponent {


//    MovieAdapter movieAdapte();

    void injectMovieComponent(MovieFragment movieFragment);


}
