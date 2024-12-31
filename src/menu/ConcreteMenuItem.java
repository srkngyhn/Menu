package menu;

public class ConcreteMenuItem extends MenuItem {
    public ConcreteMenuItem(int id, String name, String type, int calories) {
        super(id, name, type, calories);
    }

    @Override
    public String getDetails() {
        return getName() + " (" + getCalories() + " kcal)";
    }
}