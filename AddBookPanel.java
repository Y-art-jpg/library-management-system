package com.library.system;

import javax.swing.*;
import java.awt.*;

public class AddBookPanel extends JPanel {

    public AddBookPanel() {
        setLayout(new GridLayout(6,2,10,10));

        JTextField id = new JTextField();
        JTextField title = new JTextField();
        JTextField author = new JTextField();
        JTextField category = new JTextField();
        JButton save = new JButton("Save");

        add(new JLabel("Book ID:"));
        add(id);

        add(new JLabel("Title:"));
        add(title);

        add(new JLabel("Author:"));
        add(author);

        add(new JLabel("Category:"));
        add(category);

        add(new JLabel(""));
        add(save);

        save.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Book added successfully!");
        });
    }
}
