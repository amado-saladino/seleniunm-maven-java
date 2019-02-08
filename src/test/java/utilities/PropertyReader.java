package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private String resourceFile;

    public PropertyReader(String resourceName)  {

        this.resourceFile = resourceName;
    }

    private Properties getAllProperties() {

        Properties properties=new Properties();

        try {
            File file=new File(resourceFile);
            FileInputStream fis=new FileInputStream(file);
            properties.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public String getProperty(String propertyName){

        return getAllProperties().getProperty(propertyName);
    }

}