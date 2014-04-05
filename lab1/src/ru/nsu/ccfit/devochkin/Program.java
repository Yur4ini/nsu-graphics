package ru.nsu.ccfit.devochkin; /**
 * Created with IntelliJ IDEA.
 * User: Yura
 * Date: 26.02.14
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */



import ru.nsu.ccfit.devochkin.widgets.MoveListeners.MoveDownActionListener;
import ru.nsu.ccfit.devochkin.widgets.MoveListeners.MoveLeftActionListener;
import ru.nsu.ccfit.devochkin.widgets.MoveListeners.MoveRightActionListener;
import ru.nsu.ccfit.devochkin.widgets.MoveListeners.MoveUpActionListener;
import ru.nsu.ccfit.devochkin.widgets.OtherListeners.AboutActionListener;
import ru.nsu.ccfit.devochkin.widgets.OtherListeners.ExitActionListener;
import ru.nsu.ccfit.devochkin.widgets.OtherListeners.ResetActionListener;
import ru.nsu.ccfit.devochkin.widgets.OtherListeners.SettingActionListener;
import ru.nsu.ccfit.devochkin.widgets.ZoomListers.DecreaseActionListener;
import ru.nsu.ccfit.devochkin.widgets.ZoomListers.IncreaseActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Program extends JPanel{
    protected Action aboutAction, exitAction, settingAction, resetAction,
            moveLeftAction, moveRightAction, moveUpAction, moveDownAction,
            increaseAction, decreaseAction;

    public class ToolBar extends JToolBar {

        public ToolBar() {
            super();
            setFloatable(false);
        }
    }

    public Program() {
        super(new BorderLayout());
    }

    public void initActions(DrawPanel panel) {
        aboutAction = new AboutActionListener("About",
                new ImageIcon("res/icon-info.png"),"About lab1",new Integer(KeyEvent.VK_H), getRootPane());

        exitAction = new ExitActionListener("Exit",
                new ImageIcon("res/basic1-175_checkbox_no-20.png"),"Quit the application",  new Integer(KeyEvent.VK_E));

        moveLeftAction = new MoveLeftActionListener("Move left",
                new ImageIcon("res/icon-arrow-left.png"),"Move left",new Integer(KeyEvent.VK_LEFT), panel);

        moveRightAction = new MoveRightActionListener("Move right",
                new ImageIcon("res/icon-arrow.png"),"Move Right", new Integer(KeyEvent.VK_RIGHT), panel);

        moveUpAction = new MoveUpActionListener("Move up",
                new ImageIcon("res/up12.png"),"Move Up", new Integer(KeyEvent.VK_UP), panel);

        moveDownAction = new MoveDownActionListener("Move down",
                new ImageIcon("res/down1.png"),"Move Down",new Integer(KeyEvent.VK_DOWN), panel);

        increaseAction = new IncreaseActionListener("Increase",
                new ImageIcon("res/active_zoom.png"),"Increase", new Integer(KeyEvent.VK_PLUS), panel);

        decreaseAction = new DecreaseActionListener("Decrease",
                new ImageIcon("res/search_minus-20.png"),"Decrease",new Integer(KeyEvent.VK_MINUS), panel);

        settingAction = new SettingActionListener("Setting",
                new ImageIcon("res/1_settings-button.jpg"),"Setting",new Integer(KeyEvent.VK_S), panel);

        resetAction = new ResetActionListener("Reset",
                new ImageIcon("res/images1.jpg"),"Reset graphic",new Integer(KeyEvent.VK_R), panel);
    }


    public JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createViewMenu());
        menuBar.add(createHelpMenu());
        return menuBar;
    }

    public JMenu createFileMenu() {
        JMenuItem menuItem = null;
        JMenu fileMenu = new JMenu("File");

        Action[] actions = {settingAction, exitAction};
        for (int i = 0; i < actions.length; i++) {
            menuItem = new JMenuItem(actions[i]);
            menuItem.setIcon(null);
            fileMenu.add(menuItem);
        }

        return fileMenu;
    }

    public JMenu createViewMenu() {
        JMenuItem menuItem = null;
        JMenu viewMenu = new JMenu("View");

        Action[] actions = {resetAction, moveLeftAction, moveRightAction, moveUpAction, moveDownAction,
                increaseAction, decreaseAction};
        for (int i = 0; i < actions.length; i++) {
            menuItem = new JMenuItem(actions[i]);
            menuItem.setIcon(null);
            viewMenu.add(menuItem);
        }

        return viewMenu;
    }

    public JMenu createHelpMenu() {
        JMenuItem menuItem = null;
        JMenu helpMenu = new JMenu("Help");

        Action[] actions = {aboutAction};
        for (int i = 0; i < actions.length; i++) {
            menuItem = new JMenuItem(actions[i]);
            menuItem.setIcon(null);
            helpMenu.add(menuItem);
        }

        return helpMenu;
    }

    public void createToolBar() {
        JButton button = null;

        ToolBar toolBar = new ToolBar();
        add(toolBar, BorderLayout.PAGE_START);

        Action[] actions = {moveLeftAction, moveRightAction, moveUpAction, moveDownAction,
                increaseAction, decreaseAction, resetAction, settingAction, aboutAction, exitAction};

        for(int i = 0; i < actions.length; i++){
            button = new JButton(actions[i]);
            if (button.getIcon() != null) {
                button.setText("");
            }
            toolBar.add(button);
        }
    }




    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Lab 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Program demo = new Program();
        DrawPanel drawPanel = new DrawPanel(frame);
        demo.initActions(drawPanel);
        frame.setJMenuBar(demo.createMenuBar());
        demo.createToolBar();
        demo.add(drawPanel);
        demo.setOpaque(true);
        frame.setContentPane(demo);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}