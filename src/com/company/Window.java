package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Window {

    private JFrame jFrame = new JFrame();
    private MyJPanel myJPanel;

    public void drawWindow() {
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 200, 500, 500);

        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        jMenuBar.add(fileMenu);
        fileMenu.add(new JMenuItem("Открыть")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(jFrame);
                File file = fileChooser.getSelectedFile();
                myJPanel = new MyJPanel(file);
                jFrame.add(myJPanel);
                jFrame.revalidate();
            }
        });
        fileMenu.add(new JMenuItem("Сохранить")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showSaveDialog(jFrame);
                if(ret == JFileChooser.APPROVE_OPTION) {
                    myJPanel.saveImage(fileChooser.getSelectedFile().getAbsolutePath(), "jpg");
                }
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Выход")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jFrame.setJMenuBar(jMenuBar);
        jFrame.revalidate();
    }


}
