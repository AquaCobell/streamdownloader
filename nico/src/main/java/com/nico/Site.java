package com.nico;

import org.jsoup.select.Elements;

public abstract class Site 
{
    private final String url= "";
    
    public Site()
    {
       
    }
    public abstract String getDownloadLink(String url);
}
