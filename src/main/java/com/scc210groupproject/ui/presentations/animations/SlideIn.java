package com.scc210groupproject.ui.presentations.animations;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.BoxElement;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.ui.SlideImage;

public class SlideIn {
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
    private int speed = 5;

    /**
     * The selected box element the animation is applied to
     */
    private BoxElement object;


    /**
     * The constructor of the SlideIn class
     */
    public SlideIn(BoxElement object) {
        this.object = object;
        this.targetPoint = object.getLocation();

        this.startingPoint = calculateStartingPoint(object);

        System.out.println(startingPoint);
    }

    public void doAnimation() {
        this.timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveObject();
            }
        });
        
        this.timer.start();
    }

    private void moveObject() {
        if(startingPoint.x < targetPoint.x) {
            targetPoint.x += speed;
            this.object.asComp().repaint();
        } else {
            timer.stop();
        }
    }

    public Point calculateStartingPoint(BoxElement object) {
        Point point = new Point();

        /*
         * Get the size of the slide
         * get the size of the object
         * set the starting point so that whole object is not in the view
         */

        SlideImage slide = MainDisplayPanel.instance.getCurrentSlideImage();

        Dimension slideDimension = slide.getSlide().asComp().getSize();

        point.x = (slide.getX() + slideDimension.width) * -1;

        point.y = slide.getY();
        
        return point;
    }

    public BoxElement getObject() {
        return this.object;
    }

    public void setObject(BoxElement newObject) {
        this.object = newObject;
    }
}
