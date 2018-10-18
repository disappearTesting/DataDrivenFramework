package com.example.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    public static void main(String[] args) {
        // read Properties file
        Properties config = new Properties();;
        Properties OR = new Properties();;

        FileInputStream sourceStreamConfig;
        FileInputStream sourceStreamOR;
        String filePathConfig = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties";
        String filePathOR = System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties";

        try {
            sourceStreamConfig = new FileInputStream(filePathConfig);
            try {
                config.load(sourceStreamConfig);
                System.out.println(config.getProperty("browser"));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch(FileNotFoundException fe) {
            fe.printStackTrace();
        }

        try {
            sourceStreamOR = new FileInputStream(filePathOR);
            try {
                OR.load(sourceStreamOR);
                System.out.println(OR.getProperty("Button_BankManagerLogin"));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch(FileNotFoundException fe) {
            fe.printStackTrace();
        }
    }
}
