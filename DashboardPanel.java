package com.library.system;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    private MainGUI parent;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public DashboardPanel(MainGUI parent) {
        this.parent = parent;

        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel(new GridLayout(5,1,5,5));
        JButton btnViewBooks = new JButton("View Books");
        JButton btnAddBook = new JButton("Add Book");
        JButton btnLogout = new JButton("Logout");

        sidebar.add(btnViewBooks);
        sidebar.add(btnAddBook);
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // Content area
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        contentPanel.add(new ViewBooksPanel(), "VIEW_BOOKS");
        contentPanel.add(new AddBookPanel(), "ADD_BOOK");

        add(contentPanel, BorderLayout.CENTER);

        // Button actions
        btnViewBooks.addActionListener(e -> cardLayout.show(contentPanel, "VIEW_BOOKS"));
        btnAddBook.addActionListener(e -> cardLayout.show(contentPanel, "ADD_BOOK"));

        btnLogout.addActionListener(e -> parent.showCard("LOGIN"));
    }
}
