package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class MyJPanel extends JPanel {
    private File file;

    public MyJPanel(File file) {
        this.file = file;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int height = getParent().getHeight();
        int width = getParent().getWidth();
        int indent;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);


        if (width >= height) {
            indent = height / 25;
            drawFon(height - indent * 2, indent, g2d);
            drawPoint(height - indent * 2, indent, g2d);
        } else {
            indent = width / 25;
            drawFon(width - indent * 2, indent, g2d);
            drawPoint(width - indent * 2, indent, g2d);
        }

    }

    private void drawFon(int size, int indent, Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawOval(indent, indent, size, size);
        graphics2D.setColor(Color.LIGHT_GRAY);
        graphics2D.drawOval(size / 6 + indent, size / 6 + indent, (int) (size / 1.5), (int) (size / 1.5));
        graphics2D.drawOval(size / 3 + indent, size / 3 + indent, size / 3, size / 3);
        graphics2D.drawLine(0, size / 2 + indent, size + indent * 2, size / 2 + indent);
        graphics2D.drawLine(size / 2 + indent, 0, size / 2 + indent, size + indent * 2);
        graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, size/37));
        graphics2D.drawString("0", size / 2 + indent+2, size / 2 + indent);
        graphics2D.drawString("30", size / 2 + size / 6 + indent, size / 2 + indent);
        graphics2D.drawString("60", size / 2 + size / 3 + indent, size / 2 + indent);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("0", size / 2 + indent+2, indent);
        graphics2D.drawString("90", size + indent+2, size / 2 + indent-1);
        graphics2D.drawString("180", size/2 + indent+2, size+indent+size/40);
        graphics2D.drawString("270", 0, size / 2 + indent-1);
    }


    private void drawPoint(int size, int indent, Graphics2D graphics2D) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            graphics2D.setColor(Color.RED);
            while (reader.ready()) {
                String[] coordinates = reader.readLine().split("\\s+");
                int width = (int) ((double) size / 180 * Integer.parseInt(coordinates[0]) * Math.sin(Math.toRadians(Integer.parseInt(coordinates[1]))));
                int height = (int) ((double) size / 180 * Integer.parseInt(coordinates[0]) * Math.cos(Math.toRadians(Integer.parseInt(coordinates[1]))));
                graphics2D.fillOval(size / 2 + width - 3 + indent, size / 2 - height - 3 + indent, 6, 6);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveImage(String name, String type) {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        paint(g2);
        try {
            ImageIO.write(image, type, new File(name + "." + type));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
