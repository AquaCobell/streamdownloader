package com.nico;

import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI 
{

    public GUI()
    {

    }

    public String enterLink()
    {   

        String input = JOptionPane.showInputDialog("Link:");
        return input;
        

    }

    public ArrayList<Integer> enterSeasonEpisode()
    {   
        ArrayList<Integer> input = new ArrayList();

        JTextField season = new JTextField(2);
        JTextField episode = new JTextField(2);
        //JLabel text = new JLabel("Please enter Season & Episode");
        JPanel myPanel = new JPanel();
       
        //myPanel.add(text);
        myPanel.add(new JLabel("Season:"));
        myPanel.add(season);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Episode:"));
        myPanel.add(episode);
        

        int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Please enter Season and Episode", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) 
        {
            System.out.println("x value: " + season.getText());
            System.out.println("y value: " + episode.getText());
        }
        input.add(Integer.parseInt(season.getText()));
        input.add(Integer.parseInt(episode.getText()));
        return input;
    }

   
}
