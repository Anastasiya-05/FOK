/*package com.sportcenter.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EventDemo {
    JTextField jTextFieldFirst;
    JFrame jFrame;

    public EventDemo() {
        JLabel jLabel = new JLabel();
        jFrame = new JFrame(MainMenu.selectMenu());
        jFrame.setLayout(new FlowLayout());
        // ввести текстовое поле на панель содержимого
        jTextFieldFirst = new JTextField(15);
        jTextFieldFirst.setActionCommand("jTextFieldFirst");
        // установить исходные размеры фрейма
        jFrame.setSize(500, 150);
        ActionListener actionListener = new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if (jTextFieldFirst.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Данные пользователя должны быть заполнены", "alert",
                            JOptionPane.ERROR_MESSAGE);
                }

                jTextFieldFirst.setText("");
            }
        };
        //завершить работу приложения, если пользователь закрывает окно
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jTextFieldFirst.addActionListener(actionListener);
        jFrame.add(jLabel);
        jFrame.add(jTextFieldFirst);
        // отобразить фрейм
        jFrame.setVisible(true);
    }
    public static void main(String args[]) {
        // создать фрейм в потоке диспетчеризации событий
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventDemo();
            }
        });
    }}*/