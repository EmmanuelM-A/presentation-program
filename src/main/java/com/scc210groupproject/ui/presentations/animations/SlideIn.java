package com.scc210groupproject.ui.presentations.animations;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.scc210groupproject.structure.ExtendedElement;
import com.scc210groupproject.ui.presentations.PresentationActions;

public class SlideIn extends PresentationActions {
    /**
     * 
     */
    private Timer timer;
    
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
    private int speed;

    public SlideIn(int speed) {
        this.speed = speed;
    }

    @Override
    public void doAction() {        
        this.startingPoint = calculateStartingPoint(selectedElement);

        this.targetPoint = selectedElement.getLocation();

        selectedElement.setLocation(startingPoint);

        this.timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveObject(startingPoint, targetPoint);
            }
        });

        timer.start();
    }

    private void moveObject(Point startingPoint, Point targetPoint) {
        if(startingPoint.x < targetPoint.x) {
            startingPoint.x += speed;
            selectedElement.setLocation(new Point(startingPoint.x, startingPoint.y));
            
            selectedElement.asComp().repaint();
        } else {
            timer.stop();
        }
    }

    private Point calculateStartingPoint(ExtendedElement element) {
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
