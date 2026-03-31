package com.library.system;

import javax.swing.*;
import java.awt.*;

public class MainGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainGUI() {
        setTitle("Library Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(this), "LOGIN");
        mainPanel.add(new DashboardPanel(this), "DASHBOARD");

        add(mainPanel);

        cardLayout.show(mainPanel, "LOGIN");
    }

    public void showCard(String name) {
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}