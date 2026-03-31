package com.library.system;

import javax.swing.*;
import java.awt.*;

public class ViewBooksPanel extends JPanel {

    public ViewBooksPanel() {
        setLayout(new BorderLayout());

        String[] columns = {"ID", "Title", "Author", "Category", "Status"};

        Object[][] data = {
                {"B001", "Java Basics", "John Doe", "Programming", "Available"},
                {"B002", "Database Systems", "Jane Smith", "IT", "Borrowed"}
        };

        JTable table = new JTable(data, columns);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
