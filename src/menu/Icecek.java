package menu;

public class Icecek extends MenuItem {
    public Icecek(int id, String name, int calories) {
        super(id, name, "İçecek", calories);
    }

    @Override
    public String getDetails() {
        return "İçecek: " + getName() + " (" + getCalories() + " kalori)";
    }
}
