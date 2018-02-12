package pl.lukk;

import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties
{

    /**
     * Folder location for storing files
     */
    private String location = new File("src/main/resources/static/uploads/").getAbsolutePath();;

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

}