package com.nico;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;



@Command(name = "sws",
description = "The basic Stream Web Scrapper Command",
subcommands = {download.class}

)

public class CLI implements Runnable
{
    Controller controller;
    CLI()
    {
        controller = new Controller();
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'run'");
        System.out.println("lol");
    } 
}

@Command(name = "download")
class download  implements Runnable
{
    @Option(names={"-l", "-link"}, required = true ) 
    String link;
    @Override
    public void run()
    {
        Controller c = new Controller();
        try 
        {
            c.downloadFullAnime(link);
        } 
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Leider ist ein Fehler aufgetreten.");
        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }


}


