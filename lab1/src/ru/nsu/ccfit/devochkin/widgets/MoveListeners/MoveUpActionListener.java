package ru.nsu.ccfit.devochkin.widgets.MoveListeners;

import ru.nsu.ccfit.devochkin.DrawPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MoveUpActionListener extends AbstractAction {
    private DrawPanel panel;

    public MoveUpActionListener(String text, ImageIcon icon,
                                String desc, Integer mnemonic, DrawPanel panel) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
        this.panel = panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.moveUp();
    }
}