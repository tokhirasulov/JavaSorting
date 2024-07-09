package id;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame {
    Algorithms algo = new Algorithms();

    JPanel pPanel1, pPanel2;

    JButton jbtRandomize, jbtMerge, jbtBubble, jbtInsertion, jbtSelection, jbtStart;

    JProgressBar jb1;

    JSlider slider = new JSlider(0, 100, 2);

    public Main(){
        pPanel1 = new JPanel();
        pPanel1.setLayout(new GridLayout(1, 7));
        pPanel1.setBackground(Color.CYAN);
        pPanel2 = new JPanel();
        pPanel2.setLayout(new BorderLayout());

        jbtRandomize = new JButton("Randomize");
        jbtMerge = new JButton("Merge Sort");
        jbtBubble = new JButton("Bubble Sort");
        jbtInsertion = new JButton("Insertion Sort");
        jbtSelection = new JButton("Selection");
        jbtStart = new JButton("Start");

        jbtStart.setBackground(Color.GREEN);

        pPanel1.add(jbtRandomize);
        pPanel1.add(jbtMerge);pPanel1.add(jbtBubble);pPanel1.add(jbtInsertion);pPanel1.add(jbtSelection);

        pPanel1.add(slider, BorderLayout.WEST);
        pPanel2.add(algo, BorderLayout.CENTER);

        ListenerClass listener = new ListenerClass();
        jbtRandomize.addActionListener(listener);
        jbtMerge.addActionListener(listener);
        jbtBubble.addActionListener(listener);
        jbtInsertion.addActionListener(listener);
        jbtSelection.addActionListener(listener);
        jbtStart.addActionListener(listener);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event){
                int value = slider.getValue();
                algo.setSize(value);
                algo.BAR_WIDTH = (float)800 / algo.getSIZE();
                algo.repaint();
            }
        });

        add(pPanel1, BorderLayout.NORTH);
        add(pPanel2, BorderLayout.CENTER);

        setTitle("Sorting Techniques");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

    }
    class ListenerClass implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == jbtRandomize){
                algo.initShuffleBar();
            }else if(e.getSource() == jbtMerge){
                algo.mergerSort();
                algo.initShuffleBar();
            }else if(e.getSource() == jbtSelection){
                algo.selectionSort();
                algo.initShuffleBar();
            }else if(e.getSource() == jbtInsertion){
                algo.insertionSort();
                algo.initShuffleBar();
            }else if(e.getSource() == jbtBubble){
                algo.bubbleSort();
                algo.initShuffleBar();
            }else if(e.getSource() == jbtStart){
                System.out.println("Start button is clicked");
            }
        }
    }

}
