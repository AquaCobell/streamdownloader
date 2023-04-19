package com.nico;

import java.awt.Robot;
import java.awt.MouseInfo;
import java.awt.Point;


class Coordinates

{


    public static void main(String[] args) throws Exception {

    Point p = new Point(0, 0);
        
        
        while(true)
        {
            
            p = MouseInfo.getPointerInfo().getLocation();

            System.out.println("x: " +p.x);
            System.out.println("y: " +p.y);
            Thread.sleep(1000);
        }

    }
}