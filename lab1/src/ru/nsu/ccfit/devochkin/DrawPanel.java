package ru.nsu.ccfit.devochkin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;


public class DrawPanel extends JPanel implements MouseMotionListener, MouseWheelListener{
    private JFrame frame;
    private int xMouse,yMouse;
    private int xMid,yMid;
    private int stepMove,stepScale;
    private int pixelUnit;
    static final int STEP_MIN = 1,SCALE_MIN = 1;
    static final int STEP_MAX = 100,SCALE_MAX = 100;
    static final int STEP_INIT = 10,SCALE_INIT = 10;
    static final int PIXEL_UNIT_MIN = 20,PIXEL_UNIT_MAX = 20;
    private boolean isScrollable = true;
    private boolean isMovable = true;
    private CanvasPanel canvasPanel;
    private BufferedImage canvas;

    public DrawPanel(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        stepMove = STEP_INIT;
        stepScale = SCALE_INIT;
        pixelUnit = PIXEL_UNIT_MAX;
        canvasPanel = new CanvasPanel();
        canvasPanel.addMouseMotionListener(this);
        canvasPanel.addMouseWheelListener(this);
        add(canvasPanel, BorderLayout.CENTER);
    }



    public boolean isScrollable() {
        return isScrollable;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public void setScrollable(boolean isScrollable) {
        this.isScrollable = isScrollable;
    }

    public void setMovable(boolean isMovable) {
        this.isMovable = isMovable;
    }

    public interface Function {

        public double calc(double t);
    }

    public int getStepMove() {
        return stepMove;
    }

    public int getStepScale() {
        return stepScale;
    }

    public void setStepScale(int stepScale) {
        this.stepScale = stepScale;
    }

    public void setStepMove(int stepMove) {
        this.stepMove = stepMove;
    }

    public void increase() {
        pixelUnit += stepScale;
        canvasPanel.repaint();
    }

    public void decrease() {
        if(pixelUnit - stepScale >= PIXEL_UNIT_MIN)
            pixelUnit -= stepScale;
        canvasPanel.repaint();
    }

    public void moveLeft() {
        xMid += stepMove;
        canvasPanel.repaint();
    }

    public void moveRight() {
        xMid -= stepMove;
        canvasPanel.repaint();
    }

    public void moveUp() {
        yMid += stepMove;
        canvasPanel.repaint();
    }

    public void moveDown() {
        yMid -= stepMove;
        canvasPanel.repaint();
    }

    public void reset() {
        xMid = canvas.getWidth() / 2;
        yMid = canvas.getHeight() / 2;
        pixelUnit = PIXEL_UNIT_MAX;
        canvasPanel.repaint();
    }



    public void showSettingDialog() {
        SettingDialog settingDialog = new SettingDialog(frame, this);
        settingDialog.setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!isMovable) return;
        xMid += e.getX() - xMouse;
        yMid += e.getY() - yMouse;
        xMouse = e.getX();
        yMouse = e.getY();
        canvasPanel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(!isMovable) return;
        xMouse = e.getX();
        yMouse = e.getY();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(!isScrollable) return;
        int step = stepScale * (-e.getWheelRotation());
        if(pixelUnit + step > PIXEL_UNIT_MIN) {
            pixelUnit += step;
        }
        else {
            pixelUnit = PIXEL_UNIT_MAX;
        }
        canvasPanel.repaint();
    }


    protected class CanvasPanel extends JPanel {
        private boolean init = false;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            drawPlane();
            drawFunc();
            g.drawImage(canvas, 0, 0, null);

        }

        public void drawPlane() {
            if(!init) {
                init = true;
                xMid = getWidth() / 2;
                yMid = getHeight() / 2;
            }
            int width = getWidth();
            int height = getHeight();
            for(int i = xMid % pixelUnit; i < width; i += pixelUnit) {
                if(i < 0) i += pixelUnit;
                for(int j = 0; j < height; j++) {
                    canvas.setRGB(i, j, Color.WHITE.getRGB());
                }
            }
            for(int i = yMid % pixelUnit; i < height; i += pixelUnit) {
                if(i < 0) i += pixelUnit;
                for(int j = 0; j < width; j++) {
                    canvas.setRGB(j, i, Color.WHITE.getRGB());
                }
            }
        }
        public void drawFunc() {
            int width = getWidth();
            int height = getHeight();

            Function f[] = {new Function() {
                @Override
                public double calc(double t) {
                    return (t*t / (t*t - 1));
                }
            },
                    new Function() {
                        @Override
                        public double calc(double t) {
                            return ((t*t + 1) / (t + 2));
                        }
                    }};
            Function df[] = {new Function() {
                @Override
                public double calc(double t) {
                    double tmp = (t*t - 1);
                    return (2*t / (tmp*tmp));
                }
            },
                    new Function() {
                        @Override
                        public double calc(double t) {
                            double tmp = t+2;
                            return (t*t + 4*t -1) / tmp*tmp;
                        }
                    }};
            double minX = -xMid * 1.0 / pixelUnit;
            double minY = -(height - yMid) * 1.0 / pixelUnit;
            double maxX = (width - xMid) * 1.0 / pixelUnit;
            double maxY = yMid * 1.0 / pixelUnit;

            double tyPlus = 0;
            double yuMinus = 0;
            double txMinus = 0;
            double txPlus = 0;
            double dt = 0;


            tyPlus = (minY + Math.sqrt((minY + 4) * (minY + 4) - 20)) / 2;
            yuMinus = (minY - Math.sqrt((minY + 4) * (minY + 4) - 20)) / 2;
            dt = 1.0 / (pixelUnit * Math.max(Math.abs(df[1].calc(tyPlus)), Math.abs(df[1].calc(yuMinus))));

            for (double i = yuMinus; i <= tyPlus; i += dt) {
                int x = xMid + (int) ( pixelUnit * f[0].calc(i));
                int y = yMid - (int) ( pixelUnit * f[1].calc(i));
                if (y < height && y >= 0
                        && x < width && x >= 0) {

                    canvas.setRGB(x, y, Color.RED.getRGB());
                }
            }


            txMinus = -1.0 * Math.sqrt(maxX / (maxX - 1));
            yuMinus = (maxY - Math.sqrt((maxY + 4) * (maxY + 4) - 20)) / 2;
            dt = 1.0 / (pixelUnit * Math.max(Math.abs(df[0].calc(txMinus)), Math.abs(df[1].calc(yuMinus))));
            for(double i = yuMinus; i <= txMinus; i += dt) {
                int x = xMid + (int) ( pixelUnit * f[0].calc(i));
                int y = yMid - (int) ( pixelUnit * f[1].calc(i));
                if (y < height && y >= 0
                        && x < width && x >= 0) {
                    canvas.setRGB(x, y, Color.RED.getRGB());
                }
            }


            txPlus = Math.sqrt(minX / (minX - 1));
            txMinus = -1 * txPlus;
            dt = 1.0 / (pixelUnit * Math.max(Math.abs(df[0].calc(txPlus)), Math.abs(df[0].calc(txMinus))));
            for(double i = txMinus + dt; i <= txPlus; i += dt) {
                int x = xMid + (int) ( pixelUnit * f[0].calc(i));
                int y = yMid - (int) ( pixelUnit * f[1].calc(i));
                if (y < height && y >= 0
                        && x < width && x >= 0) {
                    canvas.setRGB(x, y, Color.RED.getRGB());
                }
            }


            txPlus = 1.* Math.sqrt(maxX / (maxX - 1));
            tyPlus = (maxY + Math.sqrt((maxY + 4) * (maxY + 4) - 20)) / 2;
            dt = 1.0 / (pixelUnit * Math.max(Math.abs(df[1].calc(tyPlus)), Math.abs(df[0].calc(txPlus))));
            for(double i = txPlus + dt; i <= tyPlus; i += dt) {
                int x = xMid + (int) ( pixelUnit * f[0].calc(i));
                int y = yMid - (int) ( pixelUnit * f[1].calc(i));
                if (y < height && y >= 0
                        && x < width && x >= 0) {
                    canvas.setRGB(x, y, Color.RED.getRGB());
                }
            }
        }
    }
}