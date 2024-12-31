package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import menu.MenuService;
import menu.MenuItem;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Form3MenuOneriTablo extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;

    public Form3MenuOneriTablo(double vki) {
        setTitle("The Menü");
        setSize(800, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("The Menü", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Num", "Çorba", "Pilav", "Ana Yemek", "Meze", "İçecek", "Toplam Kalori", "Seç"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class<?> getColumnClass(int column) {
                return column == 7 ? Boolean.class : String.class;
            }
        };

        // Kolon genişliklerini ayarla
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // Num
        columnModel.getColumn(1).setPreferredWidth(150); // Çorba
        columnModel.getColumn(2).setPreferredWidth(170); // Pilav
        columnModel.getColumn(3).setPreferredWidth(150); // Ana Yemek
        columnModel.getColumn(4).setPreferredWidth(120); // Meze
        columnModel.getColumn(5).setPreferredWidth(120); // İçecek
        columnModel.getColumn(6).setPreferredWidth(100); // Toplam Kalori
        columnModel.getColumn(7).setPreferredWidth(50);  // Seç

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton geriDonButton = new JButton("Geri Dön");
        JButton yenileButton = new JButton("Yenile");
        JButton theMenuButton = new JButton("The Menü");

        buttonPanel.add(geriDonButton);
        buttonPanel.add(yenileButton);
        buttonPanel.add(theMenuButton);
        add(buttonPanel, BorderLayout.SOUTH);

        fillTable(vki);

        table.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (column == 7) {
                    Boolean isSelected = (Boolean) table.getValueAt(row, column);
                    if (Boolean.TRUE.equals(isSelected)) {
                        String corba = (String) table.getValueAt(row, 1);
                        String pilav = (String) table.getValueAt(row, 2);
                        String anaYemek = (String) table.getValueAt(row, 3);
                        String salata = (String) table.getValueAt(row, 4);
                        String icecek = (String) table.getValueAt(row, 5);

                        // Kalori bilgilerini oluştur
                        MenuService menuService = new MenuService();
                        List<MenuSelection> selections = List.of(
                            new MenuSelection("Çorba", corba, menuService.getCaloriesByNameAndType(corba, "Çorba")),
                            new MenuSelection("Pilav", pilav, menuService.getCaloriesByNameAndType(pilav, "Pilav")),
                            new MenuSelection("Ana Yemek", anaYemek, menuService.getCaloriesByNameAndType(anaYemek, "Ana Yemek")),
                            new MenuSelection("Meze", salata, menuService.getCaloriesByNameAndType(salata, "Salata")),
                            new MenuSelection("İçecek", icecek, menuService.getCaloriesByNameAndType(icecek, "İçecek"))
                        );

                        SwingUtilities.invokeLater(() -> {
                            Form4SecilenMenuGoster form4 = new Form4SecilenMenuGoster(selections);
                            form4.setVisible(true);
                            dispose();
                        });
                    }
                }
            }
        });

        geriDonButton.addActionListener(e -> {
            Form2VKI form2 = new Form2VKI();
            form2.setVisible(true);
            dispose();
        });

        yenileButton.addActionListener(e -> fillTable(vki));

        theMenuButton.addActionListener(e -> {
            Form1Ana form1 = new Form1Ana();
            form1.setVisible(true);
            dispose();
        });
    }

    private void fillTable(double vki) {
        tableModel.setRowCount(0);

        int minCalories, maxCalories;
        if (vki < 18.5) {
            minCalories = 2500;
            maxCalories = 3000;
        } else if (vki < 25) {
            minCalories = 2200;
            maxCalories = 2800;
        } else if (vki < 30) {
            minCalories = 2000;
            maxCalories = 2500;
        } else if (vki < 35) {
            minCalories = 1800;
            maxCalories = 2200;
        } else if (vki < 40) {
            minCalories = 1600;
            maxCalories = 2000;
        } else if (vki < 50) {
            minCalories = 1400;
            maxCalories = 1600;
        } else {
            minCalories = 1200;
            maxCalories = 1600;
        }

        int mealCalories = (minCalories + maxCalories) / 6;

        MenuService menuService = new MenuService();
        List<MenuItem> corbalar = menuService.getRandomMenuSuggestions("Çorba", mealCalories);
        List<MenuItem> pilavlar = menuService.getRandomMenuSuggestions("Pilav", mealCalories);
        List<MenuItem> anaYemekler = menuService.getRandomMenuSuggestions("Ana Yemek", mealCalories);
        List<MenuItem> salatalar = menuService.getRandomMenuSuggestions("Salata", mealCalories);
        List<MenuItem> icecekler = menuService.getRandomMenuSuggestions("İçecek", mealCalories);

        for (int i = 0; i < 4; i++) {
            String corba = corbalar.get(i % corbalar.size()).getName();
            String pilav = pilavlar.get(i % pilavlar.size()).getName();
            String anaYemek = anaYemekler.get(i % anaYemekler.size()).getName();
            String salata = salatalar.get(i % salatalar.size()).getName();
            String icecek = icecekler.get(i % icecekler.size()).getName();

            int toplamKalori = corbalar.get(i % corbalar.size()).getCalories()
                    + pilavlar.get(i % pilavlar.size()).getCalories()
                    + anaYemekler.get(i % anaYemekler.size()).getCalories()
                    + salatalar.get(i % salatalar.size()).getCalories()
                    + icecekler.get(i % icecekler.size()).getCalories();

            tableModel.addRow(new Object[]{i + 1, corba, pilav, anaYemek, salata, icecek, toplamKalori, false});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Form3MenuOneriTablo(25).setVisible(true)); // VKI örneği
    }
}
