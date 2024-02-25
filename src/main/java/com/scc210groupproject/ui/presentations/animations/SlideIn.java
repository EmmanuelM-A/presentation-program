package com.scc210groupproject.ui.presentations.animations;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/*import java.util.Timer;
import java.util.TimerTask;*/


import com.scc210groupproject.structure.ExtendedElement;
import com.scc210groupproject.ui.presentations.PresentationManager;

public class SlideIn extends Animation {
    /**
     * 
     */
    private Timer timer;

    private long duration;
    
    /**
     * The calculated starting point for the animation
     */
    private Point startingPoint;

    /**
     * The location where the animation should stop
     */
    private Point targetPoint;

    /**
     * The speed of the animation
     */
    private int speed = 25;

    /**
     * The selected box element the animation is applied to
     */
    //protected ExtendedElement element;


    /**
     * The constructor of the SlideIn class
     */
    public SlideIn() {

        this.duration = 10;
    }

    @Override
    public void doAnimation() {
        if(selectedElement.hasAnimation()) {
            this.startingPoint = calculateStartingPoint(selectedElement);

            this.targetPoint = selectedElement.getLocation();

            selectedElement.setLocation(startingPoint);

            int delta = this.startingPoint.x * -1;

            //System.out.println("Starting point: " + startingPoint + " - Target point: " + targetPoint);

            this.timer = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    moveObject(startingPoint, targetPoint);
                }
            });

            /*timer = new Timer();

            this.timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    moveObject(startingPoint, targetPoint);
                }
                
            }, 0, duration);*/
            timer.start();
        } else {
            System.out.println("Element has no animation!");
        }
        //PresentationManager.instance.getPresentationDisplay().setInputState(false);
    }

    @Override
    protected void moveObject(Point startingPoint, Point targetPoint) {
        if(startingPoint.x < targetPoint.x) {
            startingPoint.x += speed;
            selectedElement.setLocation(new Point(startingPoint.x, startingPoint.y));
            selectedElement.asComp().repaint();
        } else {
            /*timer.cancel();
            timer.purge();*/
            timer.stop();
        }
    }

    @Override
    public Point calculateStartingPoint(ExtendedElement element) {
        Point point = new Point();

        // Get the distance between the right boundary of the slide to the closest side of the element
        int deltaX = element.getLocation().x;

        // Calculate the starting x point of the element
        point.x = (deltaX + element.getSize().width) * -1;

        // Calculate the starting y point of the element
        point.y = element.getLocation().y;
        
        return point;
    }
}
