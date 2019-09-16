package com.kh;

import com.kh.util.MyFileReader;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlertEtlMain {
    static {
        try {
            Class.forName("com.kh.dao.AlertLogDao");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        if(args.length>0){
            String filePath = args[0];
            try {
                MyFileReader.read(filePath);
            } catch (IOException e) {
                Logger.getGlobal().log(Level.SEVERE,e.getMessage());
                e.printStackTrace();
            }
        }else {
            Logger.getGlobal().log(Level.SEVERE,"Please provide the correct file path ...");
        }
    }
}
