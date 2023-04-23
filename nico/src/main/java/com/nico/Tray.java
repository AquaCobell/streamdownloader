package com.nico;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
public class Tray 
{
    Controller c;
    Clipboardreader clip;
    Tray()
    {
        c = new Controller();
        clip = new Clipboardreader();
    }

    public void loadTray() throws AWTException
    {
        TrayIcon trayIcon = null;
        if(SystemTray.isSupported())
        {
            SystemTray tray = SystemTray.getSystemTray();
            //Image image = ImageIO.read(TrayIconService.class.getResourceAsStream("/path/to/your/icon.png"));
            Image image = Toolkit.getDefaultToolkit().getImage("/images/image.png");
            
            
            PopupMenu popup = new PopupMenu();

            

            MenuItem downloadFullAnime= new MenuItem("Download Full Anime");
            MenuItem downloaddetailedAnime= new MenuItem("Download from Season & Episode");
            MenuItem downloadSeasonAnime= new MenuItem("Download from Season to Season");
            MenuItem exit = new MenuItem("Exit");

            exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    System.exit(0);
                }
                
            });

            downloadFullAnime.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    try {
                        c.downloadFullAnime(clip.getClipboard());
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                }
                
            });


            popup.add(downloadFullAnime);
            popup.add(downloaddetailedAnime);
            popup.add(downloadSeasonAnime);
            popup.add(exit);





            trayIcon = new TrayIcon(image, "Test",popup);

            tray.add(trayIcon);
    
           /*  move.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                  
                    
                }
                
            });
            MenuItem close = new MenuItem("Beenden");
            close.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.exit(0);
                }
            });

            popup.add(move);
            popup.add(close);
            trayIcon = new TrayIcon(image, "Nummer", popup);
          
            try
            {
                tray.add(trayIcon);
                
            } catch(Exception e)
            {
                System.out.println(e);
            }
            */
            //https://stackoverflow.com/questions/758083/how-do-i-put-a-java-app-in-the-system-tray
          
    

        } 
        else
        {
            System.out.println("erro");
        }
        
    }
}
