package main;

import utilities.VKIHesaplayici;
import java.awt.Font;
import javax.swing.*;
import java.io.Serializable;

public class Form2VKI extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JTextField boyTextField;
    private JTextField kiloTextField;

    public Form2VKI() {
        setTitle("Form2VKI");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        JLabel baslikLabel = new JLabel("The Menu", SwingConstants.CENTER);
        baslikLabel.setFont(new Font("Arial", Font.BOLD, 24));
        baslikLabel.setBounds(100, 20, 200, 40);
        getContentPane().add(baslikLabel);

        JLabel hesaplamaLabel = new JLabel("Vücut Kitle İndeksi Hesaplama:");
        hesaplamaLabel.setBounds(10, 56, 313, 30);
        hesaplamaLabel.setFont(new Font("Arial", Font.BOLD, 12));
        getContentPane().add(hesaplamaLabel);

        JLabel boyLabel = new JLabel("Boyunuz (metre):");
        boyLabel.setBounds(10, 90, 120, 30);
        getContentPane().add(boyLabel);

        boyTextField = new JTextField();
        boyTextField.setBounds(140, 90, 108, 30);
        getContentPane().add(boyTextField);

        JLabel kiloLabel = new JLabel("Kilonuz (kg):");
        kiloLabel.setBounds(10, 130, 120, 30);
        getContentPane().add(kiloLabel);

        kiloTextField = new JTextField();
        kiloTextField.setBounds(140, 130, 108, 30);
        getContentPane().add(kiloTextField);

        JButton temizleButton = new JButton("Temizle");
        temizleButton.setBounds(10, 180, 100, 30);
        temizleButton.addActionListener(e -> {
            boyTextField.setText("");
            kiloTextField.setText("");
        });
        getContentPane().add(temizleButton);

        JButton hesaplaButton = new JButton("Hesapla");
        hesaplaButton.setBounds(140, 180, 108, 30);
        hesaplaButton.addActionListener(e -> {
            try {
                double boy = Double.parseDouble(boyTextField.getText());
                double kilo = Double.parseDouble(kiloTextField.getText());
                double vki = VKIHesaplayici.hesapla(boy, kilo);

                String mesaj = "Hoş geldiniz! VKI Değeriniz: " + String.format("%.2f", vki) + "\n" +
                        VKIHesaplayici.bilgiMesaji(vki) +
                        "\n\nUygun menü önerisi almak ister misiniz?";
                int secim = JOptionPane.showConfirmDialog(this, mesaj, "VKI Sonucu", JOptionPane.YES_NO_OPTION);

                if (secim == JOptionPane.YES_OPTION) {
                    new Form3MenuOneriTablo(vki).setVisible(true); // VKI'yi aktarıyoruz
                    dispose();
                } else {
                    boyTextField.setText("");
                    kiloTextField.setText("");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lütfen geçerli bir sayı girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
        getContentPane().add(hesaplaButton);

        JButton theMenuButton = new JButton("TheMenu");
        theMenuButton.setBounds(274, 180, 100, 30);
        theMenuButton.addActionListener(e -> {
            new Form1Ana().setVisible(true);
            dispose();
        });
        getContentPane().add(theMenuButton);
    }
}
