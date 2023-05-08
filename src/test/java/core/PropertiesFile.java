package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesFile {
    private static Properties properties;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;

    static String projectPath = System.getProperty("user.dir") + "/";
    private static String propertiesPath = "src/test/java/data/data.properties";

    public static void setPropertiesFile() {
        properties = new Properties();
        try {
            fileIn = new FileInputStream(projectPath + propertiesPath);
            properties.load(fileIn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }

    public static String getPropValue(String key){
        String value = null;
        try {
            value = properties.getProperty(key);
            return value;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return value;
    }

    public static void serPropValue(String key, String value) {
        try {
            fileOut = new FileOutputStream(projectPath + propertiesPath);
            properties.setProperty(key, value);
            properties.store(fileOut, "set new value");

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
