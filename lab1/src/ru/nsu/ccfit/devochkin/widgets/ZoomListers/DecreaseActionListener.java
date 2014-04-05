package ru.nsu.ccfit.devochkin.widgets.ZoomListers;

import ru.nsu.ccfit.devochkin.DrawPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DecreaseActionListener extends AbstractAction{
    private DrawPanel panel;

    public DecreaseActionListener(String text, ImageIcon icon,
                                  String desc, Integer mnemonic, DrawPanel panel) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.decrease();
    }
}
