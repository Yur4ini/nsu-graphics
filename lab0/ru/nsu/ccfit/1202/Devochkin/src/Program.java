/**
 * Created with IntelliJ IDEA.
 * User: Yura
 * Date: 26.02.14
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Program extends JFrame {

    public Program() {
        initUI();
    }


    public final void initUI() {

        DrawPanel dpnl = new DrawPanel();
        dpnl.setLayout(null);
        add(dpnl);
        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Exit");
        JMenu view = new JMenu("About");

        JMenuItem eMenuItem = new JMenuItem("Exit");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(eMenuItem);
        JMenuItem eMenuItem1 = new JMenuItem("About");
        eMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Button");
            }
        });

        view.add(eMenuItem1);

        menubar.add(file);
        menubar.add(view);

        setJMenuBar(menubar);

        JButton button = new JButton("Exit");
        button.setSize(100,40);
        button.setLocation(0,0);
        dpnl.add(button);
        JButton button1 = new JButton("About");
        button1.setSize(100, 40);
        button1.setLocation(100,0);
        dpnl.add(button1);
        setContentPane(dpnl);
        setSize(800, 600);
        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                System.out.println("Button");
            }
        });
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        setTitle("Lab0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Program ex = new Program();
                ex.setVisible(true);
            }
        });
    }
}
