package com.kodecaptain.myapplication.data;

import android.content.Context;
import android.util.Log;

import com.kodecaptain.myapplication.Tasbeeh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManagement {
    private static final String TAG = "DataManagement";
    final Context applicationContext;
    final File dataFile;
    ArrayList<Tasbeeh> tasbeehList;

    public DataManagement(Context applicationContext) {
        this.applicationContext = applicationContext;
        this.dataFile = new File(this.applicationContext.getFilesDir(), "tasbeeh.csv");
        this.tasbeehList = new ArrayList<>();

        // populating data in list
        try (Scanner in = new Scanner(this.dataFile)) {
            while (in.hasNextLine()) {
                this.tasbeehList.add(new Tasbeeh(in.nextLine()));
            }
        } catch (IOException ioException) {
            Log.e(TAG, "DataManagement: " + ioException.getMessage(), null);
        }
    }

    public ArrayList<Tasbeeh> getTasbeehList() {
        return tasbeehList;
    }

    public void addNewTasbeeh(Tasbeeh tasbeeh) {
        if (tasbeehList.size() > 0) {
            int tempId = tasbeehList.get(tasbeehList.size() - 1).id;
            tasbeeh.id = tempId + 1;
        } else
            tasbeeh.id = 1;
        tasbeehList.add(tasbeeh);
        try (FileWriter fileWriter = new FileWriter(dataFile, true)) {

            fileWriter.write(tasbeeh.getCSVLine());
        } catch (IOException ioException) {
            Log.e(TAG, "DataManagement: " + ioException.getMessage(), null);
        }
    }

    public void updateTasbeeh(Tasbeeh updatedTasbeeh) {
        try (FileWriter fileWriter = new FileWriter(dataFile)) {
            for (int i = 0; i < tasbeehList.size(); i++) {
                // updating in list
                if (tasbeehList.get(i).id == updatedTasbeeh.id) {
                    tasbeehList.get(i).title = updatedTasbeeh.title;
                    tasbeehList.get(i).count = updatedTasbeeh.count;
                }
                // updating in file
                fileWriter.write(tasbeehList.get(i).getCSVLine());
            }
        } catch (IOException ioException) {
            Log.e(TAG, "DataManagement: " + ioException.getMessage(), null);
        }
    }

    public void deleteTasbeeh(int tasbeehID) {
        int indexToDelete = -1;
        try (FileWriter fileWriter = new FileWriter(dataFile)) {
            for (int i = 0; i < tasbeehList.size(); i++) {
                if (tasbeehList.get(i).id == tasbeehID) {
                    indexToDelete = i;
                    continue;
                }
                fileWriter.write(tasbeehList.get(i).getCSVLine());
            }
            if (indexToDelete >= 0 && indexToDelete < tasbeehList.size())
                tasbeehList.remove(indexToDelete);
        } catch (IOException ioException) {
            Log.e(TAG, "DataManagement: " + ioException.getMessage(), null);
        }
    }
}
