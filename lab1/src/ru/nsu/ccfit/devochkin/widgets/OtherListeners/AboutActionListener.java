package ru.nsu.ccfit.devochkin.widgets.OtherListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutActionListener extends AbstractAction {
    private JRootPane rootPane;

    public AboutActionListener(String text, ImageIcon icon,
                               String desc, Integer mnemonic, JRootPane rootPane) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
        this.rootPane = rootPane;
    }

    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(rootPane,"Цель лаборатороной работы нарисовать график функции заданной параметрически.");
    }
}