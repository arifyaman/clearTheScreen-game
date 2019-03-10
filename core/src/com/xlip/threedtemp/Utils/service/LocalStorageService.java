package com.xlip.threedtemp.Utils.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by arif on 01.12.2017.
 */

public class LocalStorageService<T> {
    private File file;
    private T model;

    public LocalStorageService(String path, String fileName, T init) {
        file = new File(path, fileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
                writeModel(init);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public T getModel() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            T ret = ((T) in.readObject());
            in.close();
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeModel(T data) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
