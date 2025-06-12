package Practicing_for_exam.Lab1.Application2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Window extends JFrame implements Observer {
    private ArrayList<JProgressBar> bars = new ArrayList<>();

    public Window(int noOfThreads) {
        setLayout(null);
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init(noOfThreads);
        this.setVisible(true);
    }

    private void init(int n) {
        for (int i = 0; i < n; i++) {
            JProgressBar pb = new JProgressBar();
            pb.setMaximum(1000);
            pb.setBounds(50, (i+1)*30, 350, 20);
            this.add(pb);
            this.bars.add(pb);
        }
    }

    public void update(Observable o, Object arg) {  // add this
        this.setProgressValue(((ThreadCounter)o).id , ((ThreadCounter)arg).counter);
    }

    public void setProgressValue(int id, int value) {
        bars.get(id).setValue(value);
    }
}
