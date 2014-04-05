package ru.nsu.ccfit.devochkin.widgets.OtherListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitActionListener extends AbstractAction {

    public ExitActionListener(String text, ImageIcon icon,
                              String desc, Integer mnemonic) {
        super(text, icon);
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}