package menu;

public class Pilav extends MenuItem {
    public Pilav(int id, String name, int calories) {
        super(id, name, "Pilav", calories);
    }

    @Override
    public String getDetails() {
        return "Pilav: " + getName() + " (" + getCalories() + " kalori)";
    }
}
