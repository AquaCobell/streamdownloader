package com.nico;

import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

public class userinterface 
{
    Scanner scan;
   userinterface()
   {
         scan = new Scanner(System.in);
   }

   public void showMainInterface()
   {
        int auswahl = 0;
        while(true)
        {
            System.out.println("╔═══════════════════Streamdownloader═════════════════════╗");
            System.out.println("║      1. Download Serie                                 ║");
            System.out.println("║      2. Settings                                       ║");
            System.out.println("║      3. Quit                                           ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            try
            {
                auswahl = scan.nextInt();
            }
            catch(Exception e)
            {
                System.out.println("!!FEHLER: Nur Zahlen möglich!");
                scan.nextLine();
            }
            if(auswahl == 1)
            {
                enterlink();
            }
            else if(auswahl == 2)
            {
                //showsettings
            }
            else
            {
                return;
            }
        }
        
        

        
   }

   public String enterlink()
   {
        System.out.print("\033[H\033[2J");
        scan.nextLine();
        System.out.println("Copy Paste Link here:");
       
        return scan.nextLine();
        
   }

   
}
