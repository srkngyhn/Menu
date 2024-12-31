package menu;

public abstract class MenuItem{
    private int id;
    private String name;
    private String type;
    private int calories;

    // Constructor
    public MenuItem(int id, String name, String type, int calories) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.calories = calories;
    }

    // Getter ve Setter metotları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
      

    // Abstract metot (alt sınıflar bunu kendilerine göre implemente edecek)
    public abstract String getDetails();
}