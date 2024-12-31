package main;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class Form1Ana extends JFrame implements Serializable{
	private static final long serialVersionUID = 1L;
    public Form1Ana() {
        setTitle("Ana Menü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        // Üst ortada başlık
        JLabel lblBaslik = new JLabel("The Menü", SwingConstants.CENTER);
        lblBaslik.setFont(new Font("Arial", Font.BOLD, 24));
        lblBaslik.setBounds(100, 20, 200, 40);
        add(lblBaslik);

        // Giriş Butonu
        JButton btnGiris = new JButton("Giriş");
        btnGiris.setBounds(150, 100, 100, 30);
        btnGiris.addActionListener(e -> {
            Form2VKI form2 = new Form2VKI();
            form2.setVisible(true);
            dispose(); // Bu formu kapat
        });
        //new Form2VKI().setVisible(true));
        add(btnGiris);

        // Çıkış Butonu
        JButton btnCikis = new JButton("Çıkış");
        btnCikis.setBounds(150, 150, 100, 30);
        btnCikis.addActionListener(e -> System.exit(0));
        add(btnCikis);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Form1Ana().setVisible(true));
    }
}
