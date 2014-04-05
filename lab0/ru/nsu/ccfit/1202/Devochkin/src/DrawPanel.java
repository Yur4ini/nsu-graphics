import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class DrawPanel extends JPanel {
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_4BYTE_ABGR);

        int x0 = 370, y0 = 220;
        int radius = 50;
        int x = 0, y = 0;
        for(double angle = 0; angle < 2 * Math.PI; angle += Math.toRadians(1))
        {
            x = (int)(radius * Math.cos(angle)) + x0;
            y = (int)(radius * Math.sin(angle)) + y0;
            image.setRGB(x, y, Color.BLACK.getRGB());
        }
        g2.drawImage(image, 0, 40, null);
    }
}