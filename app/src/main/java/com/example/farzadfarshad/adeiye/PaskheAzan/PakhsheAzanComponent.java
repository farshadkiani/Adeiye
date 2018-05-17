package com.example.farzadfarshad.adeiye.PaskheAzan;

import dagger.Component;

/**
 * Created by farshad&farzad on 5/11/2018.
 */


@Component(modules = PakhsheAzanModule.class)
public interface PakhsheAzanComponent {


    void injectPakhsheAzan(PakhshAzanActiviy pakhshAzanActiviy);
}
