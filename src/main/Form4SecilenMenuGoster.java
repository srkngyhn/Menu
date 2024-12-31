package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Form4SecilenMenuGoster extends JFrame implements Serializable {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;

    public Form4SecilenMenuGoster(List<MenuSelection> selections) {
        setTitle("Seçilen Menü");
        setSize(500, 400);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("The Menü", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        String[] columnNames = {"Tür", "Ad", "Kalori"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        int toplamKalori = 0;
        for (MenuSelection selection : selections) {
            tableModel.addRow(new Object[]{selection.getType(), selection.getName(), selection.getCalories()});
            toplamKalori += selection.getCalories();
        }

        JLabel toplamKaloriLabel = new JLabel("Toplam = " + toplamKalori + " kalori");
        toplamKaloriLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        toplamKaloriLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel afiyetOlsunLabel = new JLabel("Afiyet Olsun. Bizi Tercih Ettiğiniz için Teşekkürler.");
        afiyetOlsunLabel.setFont(new Font("Arial", Font.BOLD, 16));
        afiyetOlsunLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(toplamKaloriLabel);
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(afiyetOlsunLabel);
        add(bottomPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        JButton geriDonButton = new JButton("Geri Dön");
        JButton theMenuButton = new JButton("The Menü");

        buttonPanel.add(geriDonButton);
        buttonPanel.add(theMenuButton);
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(buttonPanel);

        geriDonButton.addActionListener(e -> {
            Form3MenuOneriTablo form3 = new Form3MenuOneriTablo(25); // Örnek VKI
            form3.setVisible(true);
            dispose();
        });

        theMenuButton.addActionListener(e -> {
            Form1Ana form1 = new Form1Ana();
            form1.setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Form4SecilenMenuGoster form4 = new Form4SecilenMenuGoster(List.of());
            form4.setVisible(true);
        });
    }
}
