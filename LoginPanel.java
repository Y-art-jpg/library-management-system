package com.library.system;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private MainGUI parent;

    public LoginPanel(MainGUI parent) {
        this.parent = parent;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JTextField txtId = new JTextField(15);
        JPasswordField txtPass = new JPasswordField(15);
        JButton btnLogin = new JButton("Login");

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("User ID:"), gbc);

        gbc.gridx = 1;
        add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        add(txtPass, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(btnLogin, gbc);

        btnLogin.addActionListener(e -> {
            String id = txtId.getText();
            String pass = new String(txtPass.getPassword());

            // Replace with your real authentication logic
            if (id.equals("admin") && pass.equals("1234")) {
                parent.showCard("DASHBOARD");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login");
            }
        });
    }
}
