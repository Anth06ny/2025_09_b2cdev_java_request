package org.example.mexicanfood;

import javax.swing.*;
import java.awt.*;

public class MexicanUI extends JFrame {
    private JButton jbPositive;
    private JLabel jlQuestion;
    private JLabel jlMessage;
    private JTextField jtfAnswer;
    private JProgressBar progressBar;

    public MexicanUI() {
        //Appel du constructeur parent
        super("Weather");

        //construct components
        jbPositive = new JButton("Chercher");
        jlQuestion = new JLabel("Donne un id : ");
        jtfAnswer = new JTextField(5);
        jlMessage = new JLabel("");
        progressBar = new JProgressBar();
        progressBar.setVisible(false);
        progressBar.setIndeterminate(true);


        //Conteneur
        JPanel pannel = new JPanel();
        pannel.setPreferredSize(new Dimension(408, 211));
        pannel.setLayout(null);

        //add components
        pannel.add(jbPositive);
        pannel.add(jlQuestion);
        pannel.add(jtfAnswer);
        pannel.add(jlMessage);
        pannel.add(progressBar);

        //set component bounds (only needed by Absolute Positioning)
        jbPositive.setBounds(285, 155, 95, 35);
        jlQuestion.setBounds(25, 15, 410, 70);
        jtfAnswer.setBounds(25, 80, 355, 30);
        jlMessage.setBounds(25, 110, 355, 30);
        progressBar.setBounds(25, 140, 355, 10);

        //Travail Sur la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(pannel);
        pack();
        setVisible(true);

        //clic
        jbPositive.addActionListener(e -> {

            progressBar.setVisible(true);

            new Thread(() -> {
                try {
                    MexicanResultBean res = MexicanRepository.loadMexicanfood(jtfAnswer.getText());
                    jlMessage.setText(String.format("%s", res.getTitle()));
                } catch (Exception ex) {
                    ex.printStackTrace();

                    jlMessage.setText(ex.getMessage());
                } finally {
                    progressBar.setVisible(false);
                }
            }).start();
        });
    }

    public static void main(String[] args) {
        MexicanUI popup = new MexicanUI();
    }
}
