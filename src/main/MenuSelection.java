package main;

import java.io.Serializable;

public class MenuSelection implements Serializable {
    private static final long serialVersionUID = 1L;

    private String type;
    private String name;
    private int calories;

    public MenuSelection(String type, String name, int calories) {
        this.type = type;
        this.name = name;
        this.calories = calories;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }
}
