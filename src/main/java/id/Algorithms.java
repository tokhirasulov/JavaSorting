package id;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.*;

public class Algorithms extends JPanel{

    private final int WIDTH = 800, HEIGHT = WIDTH / 16 * 9;
    public int SIZE = 100;
    public float BAR_WIDTH = (float) WIDTH / SIZE;
    private float[] BAR_HEIGHT = new float[SIZE];
    private SwingWorker<Void, Void> shuffler, sorter;
    private int current_element, traversing_element;

    Algorithms(){
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public void setSize(int size){
        this.SIZE = size;
    }

    int getSIZE(){
        return SIZE;
    }

    // @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setColor(Color.WHITE);
        Rectangle2D.Float bar;

        for(int i = 0; i < getSIZE(); i++){
            bar = new Rectangle2D.Float(i * BAR_WIDTH, 0, BAR_WIDTH - 1, BAR_HEIGHT[i]);
            graphics2d.fill(bar);
        }
        bar = new Rectangle2D.Float(current_element * BAR_WIDTH, 0, BAR_WIDTH - 1, BAR_HEIGHT[current_element]);
        graphics2d.fill(bar);
        graphics2d.setColor(Color.RED);

        bar = new Rectangle2D.Float(traversing_element * BAR_WIDTH, 0, BAR_WIDTH - 1, BAR_HEIGHT[traversing_element]);
        graphics2d.fill(bar);
        graphics2d.setColor(Color.YELLOW);
    }

    public void insertionSort(){
        sorter = new SwingWorker<Void, Void>(){

            public Void doInBackground() throws InterruptedException{
                for(current_element = 1; current_element < getSIZE(); current_element++){
                    traversing_element = current_element;
                    while(traversing_element > 0 && BAR_HEIGHT[traversing_element] < BAR_HEIGHT[traversing_element - 1]){
                        swap(traversing_element, traversing_element - 1);
                        traversing_element--;

                        repaint();
                    }
                }
                current_element = 0;
                traversing_element = 0;
                return null;

            }


        };
    }

    public void bubbleSort(){
        sorter = new SwingWorker<Void,Void>() {
            @Override
            public Void doInBackground() throws InterruptedException{
                for(current_element = 0; current_element < getSIZE(); current_element++){
                    for(traversing_element = 1; traversing_element < (getSIZE() - current_element); traversing_element++){
                        if(BAR_HEIGHT[traversing_element - 1] > BAR_HEIGHT[traversing_element]){
                            swap(traversing_element, traversing_element - 1);
                            traversing_element--;

                            repaint();
                        }
                    }
                    }
                    current_element = 0;
                    traversing_element = 0;
                    return null;
            }
        };
    }

    public void mergerSort(){
        sorter = new SwingWorker<Void,Void>() {
            @Override
            public Void doInBackground() throws InterruptedException{
                for(current_element = 0; current_element < getSIZE(); current_element++){
                    for(traversing_element = 1; traversing_element < (getSIZE() - current_element); traversing_element++){
                        if(BAR_HEIGHT[traversing_element - 1] > BAR_HEIGHT[traversing_element]){
                            swap(traversing_element, traversing_element - 1);
                            traversing_element--;

                            repaint();
                        }
                    }
                }
                current_element = 0;
                traversing_element = 0;

                return null;
            }
        };
    }

    public void selectionSort(){
        sorter = new SwingWorker<Void,Void>() {
            @Override
            public Void doInBackground() throws InterruptedException{
                for(current_element = 0; current_element < getSIZE() - 1; current_element++){
                    int min_index = current_element;
                    for(int traversing_element = current_element + 1; traversing_element < getSIZE(); traversing_element++){
                        if(BAR_HEIGHT[traversing_element] < BAR_HEIGHT[min_index]){
                            min_index = traversing_element;
                        }
                    }
                    swap(current_element, min_index);
                    repaint();
                }
                current_element = 0;
                traversing_element = 0;
                return null;
            }
        };
    }

    public void initShuffleBar(){
        shuffler = new SwingWorker<Void,Void>() {
            @Override
            public Void doInBackground() throws InterruptedException{
                int middle = getSIZE() / 2;
                for(int i = middle, j = 0; j < middle; i++, j++){
                    int random_index = new Random().nextInt(getSIZE());
                    swap(i, random_index);

                    random_index = new Random().nextInt(getSIZE());
                    swap(j, random_index);

                    Thread.sleep(10);
                    repaint();
                }
                return null;
            }
            @Override
            public void done(){
                super.done();
                sorter.execute();
            }
        };

        shuffler.execute();
    }

    public void initBarHeight(){
        float interval = (float) HEIGHT / getSIZE();
        for(int i = 0; i < getSIZE(); i++){
            BAR_HEIGHT[i] = i * interval;
        }

    }

    public void swap(int indexA, int indexB){
        float temp = BAR_HEIGHT[indexA];
        BAR_HEIGHT[indexA] = BAR_HEIGHT[indexB];
        BAR_HEIGHT[indexB] = temp;
    }


}
