package id;

import java.awt.*;
import java.awt.geom.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        initBarHeight();
    }

    public void setSize(int size){
        this.SIZE = size;
        BAR_WIDTH = (float) WIDTH / SIZE;
        BAR_HEIGHT= new float[SIZE];
        initBarHeight();
        repaint();
    }

    int getSIZE(){
        return SIZE;
    }

    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setColor(Color.WHITE);
        Rectangle2D.Float bar;

        for(int i = 0; i < getSIZE(); i++){
            bar = new Rectangle2D.Float(i * BAR_WIDTH, HEIGHT - BAR_HEIGHT[i], BAR_WIDTH - 1, BAR_HEIGHT[i]);
            graphics2d.fill(bar);
        }
        if (current_element < getSIZE() && traversing_element < getSIZE()) {
            graphics2d.setColor(Color.RED);
            bar = new Rectangle2D.Float(current_element * BAR_WIDTH, HEIGHT - BAR_HEIGHT[current_element], BAR_WIDTH, BAR_HEIGHT[current_element]);
            graphics2d.fill(bar);

            graphics2d.setColor(Color.YELLOW);
            bar = new Rectangle2D.Float(traversing_element * BAR_WIDTH, HEIGHT - BAR_HEIGHT[traversing_element], BAR_WIDTH, BAR_HEIGHT[traversing_element]);
            graphics2d.fill(bar);
        }

    }

public void insertionSort() {
    sorter = new SwingWorker<Void, Void>() {
        @Override
        public Void doInBackground() throws InterruptedException {
            for (current_element = 1; current_element < getSIZE(); current_element++) {
                traversing_element = current_element;
                float key = BAR_HEIGHT[traversing_element];
                int j = traversing_element - 1;
                while (j >= 0 && BAR_HEIGHT[j] > key) {
                    BAR_HEIGHT[j + 1] = BAR_HEIGHT[j];
                    j = j - 1;
                    repaint();
                    Thread.sleep(10);
                }
                BAR_HEIGHT[j + 1] = key;
                repaint();
                Thread.sleep(10);
            }
            current_element = 0;
            traversing_element = 0;
            return null;
        }
        @Override
        protected void done(){
            repaint();
        }
    };
    sorter.execute();
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
                            Thread.sleep(10);
                            repaint();
                        }
                    }
                    }
                    current_element = 0;
                    traversing_element = 0;
                    return null;
            }
            @Override
            protected void done(){
                repaint();
            };
        };
        sorter.execute();
    }


    public void mergerSort(){
        sorter = new SwingWorker<Void,Void>() {
            @Override
            public Void doInBackground() throws InterruptedException{
               BAR_HEIGHT = mergeSortHelper(BAR_HEIGHT, 0);
                return null;

            }
            @Override
            protected void done(){
                current_element = 0;
                traversing_element = 0;
                repaint();
            }
        };
        sorter.execute();
    }

    private float[] mergeSortHelper(float[] array, int baseIndex) throws InterruptedException {
        if (array.length <= 1) {
            return array;
        }
        int middle = array.length / 2;
        float[] left = Arrays.copyOfRange(array, 0, middle);
        float[] right = Arrays.copyOfRange(array, middle, array.length);

        float[] sortedLeft = mergeSortHelper(left, baseIndex);
        float[] sortedRight = mergeSortHelper(right, baseIndex + middle);

        return merge(sortedLeft, sortedRight, baseIndex);
    }

    private float[] merge(float[] left, float[] right, int baseIndex) throws InterruptedException {
        float[] result = new float[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int elementsInResult = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result[elementsInResult] = left[leftIndex];
                current_element = baseIndex + elementsInResult;
                traversing_element = baseIndex + leftIndex;
                leftIndex++;
            } else {
                result[elementsInResult] = right[rightIndex];
                current_element = baseIndex + elementsInResult;
                traversing_element = baseIndex + rightIndex + left.length;
                rightIndex++;
            }
            elementsInResult++;
            repaint();
            Thread.sleep(10);
        }

        while (leftIndex < left.length) {
            result[elementsInResult] = left[leftIndex];
            current_element = baseIndex + elementsInResult;
            traversing_element = baseIndex + leftIndex;
            leftIndex++;
            elementsInResult++;
            repaint();
            Thread.sleep(10);
        }

        while (rightIndex < right.length) {
            result[elementsInResult] = right[rightIndex];
            current_element = baseIndex + elementsInResult;
            traversing_element = baseIndex + rightIndex + left.length;
            rightIndex++;
            elementsInResult++;
            repaint();
            Thread.sleep(10);
        }

        for (int i = 0; i < result.length; i++) {
            BAR_HEIGHT[baseIndex + i] = result[i];
        }

        return result;
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
                    Thread.sleep(10);
                    repaint();
                }
                current_element = 0;
                traversing_element = 0;
                return null;

            }
            @Override
            protected void done(){
                repaint();
            }
        };
        sorter.execute();
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
