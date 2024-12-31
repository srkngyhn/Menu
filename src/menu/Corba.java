package menu;

public class Corba extends MenuItem {
    public Corba(int id, String name, int calories) {
        super(id, name, "Çorba", calories);
    }

    @Override
    public String getDetails() {
        return "Çorba: " + getName() + " (" + getCalories() + " kalori)";
    }
}
