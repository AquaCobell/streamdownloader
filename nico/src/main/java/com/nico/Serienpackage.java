package com.nico;

import java.io.Serializable;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

class Serienpackage implements Serializable
{

    ArrayList<Serie> serien;
    Serienpackage()
    {
        serien=new ArrayList<>();
    }

    public void addSerie(Serie serie)
    {
        serien.add(serie);
    }

    //removeserie
    public ArrayList<Serie> getSerien() 
    {
        return serien;
    }
}