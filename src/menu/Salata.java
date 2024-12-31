package menu;

public class Salata extends MenuItem {
    public Salata(int id, String name, int calories) {
        super(id, name, "Salata", calories);
    }

    @Override
    public String getDetails() {
        return "Salata: " + getName() + " (" + getCalories() + " kalori)";
    }
}
