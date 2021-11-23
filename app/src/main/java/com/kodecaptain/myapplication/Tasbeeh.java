package com.kodecaptain.myapplication;

public class Tasbeeh {
    public int id;
    public String title;
    public int count;

    public Tasbeeh() {
        id = -1;
        title = null;
        count = -1;
    }

    public Tasbeeh(String title, int count) {
        this.title = title;
        this.count = count;
    }

    public Tasbeeh(int id, String title, int count) {
        this.id = id;
        this.title = title;
        this.count = count;
    }

    public Tasbeeh(String tasbeehCSV) {
        String[] values = tasbeehCSV.split(",");
        id = Integer.parseInt(values[0]);
        title = values[1];
        count = Integer.parseInt(values[2]);
    }

    public String getCSVLine() {
        return id + "," + title + "," + count + "\n";
    }
}
