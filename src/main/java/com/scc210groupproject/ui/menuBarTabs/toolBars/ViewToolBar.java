package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.structure.ExtendedElement;
import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.ui.helper.GeneralButtons;
import com.scc210groupproject.ui.presentations.PresentationActions;
import com.scc210groupproject.ui.presentations.animations.FadeIn;
import com.scc210groupproject.ui.presentations.animations.SlideIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class extends ToolBar and contains all the buttons and components that will be displayed on the
 * ViewToolBar
 *
 * @author madukaag
 * */
public class ViewToolBar extends ToolBar {
    private JButton present, presentAt;

    private JScrollPane animations, transistions;

    private JPanel animationsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    private JPanel transistionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public ViewToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        present = makeToolbarButton(GeneralButtons.PRESENT, recentsPanel);

        presentAt = makeToolbarButton(GeneralButtons.PRESENT_AT, recentsPanel);

        /*animationsPanel.setBackground(Color.WHITE);
        animations = new JScrollPane(animationsPanel);
        animations.setMaximumSize(new Dimension(500, 90));
        animations.setToolTipText("Animations");
        animations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        transistionsPanel.setBackground(Color.WHITE);
        transistions = new JScrollPane(transistionsPanel);
        transistions.setMaximumSize(new Dimension(500, 90));
        transistions.setToolTipText("Transitions");
        transistions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addButtonsToAnimations("Remove Animation", null);
        addButtonsToAnimations("Slide In", new SlideIn(90));
        addButtonsToAnimations("Fade In", new FadeIn(10));

        addButtonsToTransition("Transition 1");
        addButtonsToTransition("Transition 2");
        addButtonsToTransition("Transition 3");
        addButtonsToTransition("Transition 4");
        addButtonsToTransition("Transition 5");
        addButtonsToTransition("Transition 6");
        addButtonsToTransition("Transition 7");*/

        this.add(present);
        this.add(presentAt);

        /*separator(this);

        this.add(animations);

        separator(this);

        this.add(transistions);*/

        this.setName("View");
        this.setFloatable(false);
    }

    private void addButtonsToTransition(String title) {
        JButton button = new JButton("<html>" + title + "</html>");
        button.setPreferredSize(new Dimension(76, 76));
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        transistionsPanel.add(button);
    }

    private void addButtonsToAnimations(String title, PresentationActions animation) {
        // Create button and assign its title
        JButton button = new JButton("<html>" + title + "</html>");
        
        // Does the formating/design of the button
        formatButton(button);

        // Adds an animation to the selected element
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(animation != null) 
                    addAnimationToElement(animation);
                else
                    removeAnimationFromElement();
            }
        });

        // Adds the button directly to the panel
        animationsPanel.add(button);
    }

    private void addAnimationToElement(PresentationActions animation) {
        // Get the selected element
        ExtendedElement selectedElement = MainDisplayPanel.instance.getCurrentSelectedElement();

        if(MainDisplayPanel.instance.getCurrentSlideImage().getSlide().getActions().contains(animation)) {
            JOptionPane.showMessageDialog(null, "The element already has already been assigned this animation!", "Element Animation", JOptionPane.ERROR_MESSAGE);
        } else {
            // Check if an element has been selected
            if(selectedElement != null) {
                // Set the animations assigned element to the selected element
                animation.setSelectedElement(selectedElement);

                // Set the selected element's animation as the animation passed in
                selectedElement.setAnimation(animation);

                MainDisplayPanel.instance.getCurrentSlideImage().getSlide().getActions().add(animation);

                // Runs the passed in animation's animation once as a preview
                animation.doAction();

                // Set the prestentation manager's current selected element to null - allows new elements to be selected
                MainDisplayPanel.instance.setCurrentSelectedElement(null);
            } else {
                JOptionPane.showMessageDialog(null, "To add an animation to an element, an element must be selected first!", "No selected element", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeAnimationFromElement() {
        // Get the selected element
        ExtendedElement selectedElement = MainDisplayPanel.instance.getCurrentSelectedElement();

        // Check if an element has been selected
        if(selectedElement != null) {
            MainDisplayPanel.instance.getCurrentSlideImage().getSlide().getActions().remove(selectedElement.getAnimation());

            selectedElement.getAnimation().setSelectedElement(null);

            selectedElement.setAnimation(null);
        } else {
            JOptionPane.showMessageDialog(null, "To remove an animation from an element, an element must be selected first!", "No selected element", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void formatButton(JButton button) {
        button.setPreferredSize(new Dimension(76, 76));
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
}
