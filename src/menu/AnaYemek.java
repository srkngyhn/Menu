package menu;

public class AnaYemek extends MenuItem {
    public AnaYemek(int id, String name, int calories) {
        super(id, name, "Ana Yemek", calories);
    }

    @Override
    public String getDetails() {
        return "Ana Yemek: " + getName() + " (" + getCalories() + " kalori)";
    }
}
