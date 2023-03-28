package com.tebreca.simplemodelhost.ui;

import com.tebreca.simplemodelhost.ModelManager;
import com.tebreca.simplemodelhost.SimplemodelhostApplication;
import com.tebreca.simplemodelhost.pojo.Model;
import com.tebreca.simplemodelhost.pojo.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherUI {

    private final Logger logger = LoggerFactory.getLogger(TeacherUI.class);
    private final Button addAll = new Button();
    private final Button removeAll = new Button();
    private final JPanel modelSelectorPane = new JPanel(new GridLayout(0, 1));
    private final JPanel buttonPane = new JPanel(new GridLayout(0, 1));
    private final ButtonGroup modelSelection = new ButtonGroup();
    private Model active;
    JFrame window = new JFrame();

    private List<Table> selected = new ArrayList<>();
    private List<JCheckBox> checkBoxes = new ArrayList<>();


    public TeacherUI(Table[] tables, SimplemodelhostApplication app) {
        window.setLayout(null);
        window.setSize(1200, 800);
        for (Table table : tables) {
            JPanel table1 = new JPanel();
            int tableId = table.getId();
            table1.setBounds(x(tableId), y(tableId), 100, 50);
            table1.setSize(100, 50);
            table1.setBackground(new Color(143, 143, 143));
            JTextArea id = new JTextArea();
            id.setEditable(false);
            id.setText(Integer.toString(tableId));
            id.setBounds(25, 20, 50, 50);
            id.setSize(50, 50);
            id.setBackground(new Color(0, 0, 0, 0));
            table1.add(id);
            JCheckBox checkBox = new JCheckBox();
            checkBox.addActionListener(e -> {
                if (checkBox.isSelected()) {
                    if (!selected.contains(table)) selected.add(table);
                } else {
                    selected.remove(table);
                }
            });
            checkBox.setBackground(new Color(143, 143, 143));
            checkBox.setForeground(new Color(143, 143, 143));
            table1.add(checkBox);
            checkBoxes.add(checkBox);
            window.add(table1);
        }
        addAll.setLabel("Select All");
        removeAll.setLabel("Deselect All");
        addAll.setBounds(25, 10, 100, 20);
        removeAll.setBounds(150, 10, 100, 20);
        addAll.addActionListener(e -> checkBoxes.stream().filter(b -> !b.isSelected()).forEach(JCheckBox::doClick));
        removeAll.addActionListener(e -> checkBoxes.stream().filter(AbstractButton::isSelected).forEach(JCheckBox::doClick));
        window.add(addAll);
        window.add(removeAll);
        modelSelectorPane.setBounds(400, 10, 300, 740);
        for (Model model : ModelManager.INSTANCE.getAll()) {
            String name = model.getFile().getName();
            JRadioButton radioButton = new JRadioButton();
            radioButton.addActionListener(a -> {
                if (radioButton.isSelected()) {
                    active = model;
                }
            });
            radioButton.setText(name);
            modelSelection.add(radioButton);
            modelSelectorPane.add(radioButton);
        }
        window.add(modelSelectorPane);
        buttonPane.setBounds(800, 10, 300, 740);
        Button button = new Button();
        button.setSize(200, 100);
        button.setLabel("Start");
        button.addActionListener(a -> selected.forEach(Table::start));
        buttonPane.add(button);
        button = new Button();
        button.setSize(200, 100);
        button.setLabel("Freeze");
        button.addActionListener(a -> selected.forEach(Table::freeze));
        buttonPane.add(button);
        button = new Button();
        button.setSize(200, 100);
        button.setLabel("Unfreeze");
        button.addActionListener(a -> selected.forEach(Table::unfreeze));
        buttonPane.add(button);
        button = new Button();
        button.setSize(200, 100);
        button.setLabel("Synchronize");
        button.addActionListener(a -> selected.forEach(table -> table.open(active)));
        buttonPane.add(button);
        button = new Button();
        button.setSize(200, 100);
        button.setLabel("Stop");
        button.addActionListener(a -> selected.forEach(Table::stop));
        buttonPane.add(button);
        button = new Button();
        button.setSize(200, 100);
        button.setLabel("Shutdown");
        button.addActionListener(a -> selected.forEach(Table::off));
        buttonPane.add(button);
        window.add(buttonPane);
        button.setLabel("Refresh");
        button.addActionListener(a -> app.refresh());
        buttonPane.add(button);
        window.add(buttonPane);
    }


    public void show() {
        window.setVisible(true);
    }

    private int x(int i) {
        return i % 2 == 1 ? 25 : 150;
    }

    private int y(int i) {
        return (((int) Math.ceil(i / 2.) - 1) * 70) + 40;
    }

    public void hide() {
        window.setVisible(false);
    }
}
