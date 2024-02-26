package com.scc210groupproject.ui.menuBarTabs.toolBars;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.ExtendedElement;
import com.scc210groupproject.ui.MainDisplayPanel;
import com.scc210groupproject.ui.helper.GeneralButtons;
import com.scc210groupproject.ui.presentations.PresentationManager;
import com.scc210groupproject.ui.presentations.animations.Animation;
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
    private JButton present, presentAt, help;
    //private JTextField presentAt;
    private JScrollPane animations, transistions;

    private JPanel animationsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel transistionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public ViewToolBar(JPanel recentsPanel) {
        this.setRollover(true);

        present = makeToolbarButton(GeneralButtons.PRESENT, recentsPanel);

        presentAt = makeToolbarButton(GeneralButtons.PRESENT_AT, recentsPanel);

        animationsPanel.setBackground(Color.WHITE);
        animations = new JScrollPane(animationsPanel);
        animations.setMaximumSize(new Dimension(500, 90));
        animations.setToolTipText("Animations");
        animations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        transistionsPanel.setBackground(Color.WHITE);
        transistions = new JScrollPane(transistionsPanel);
        transistions.setMaximumSize(new Dimension(500, 90));
        transistions.setToolTipText("Transitions");
        transistions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        help = makeToolbarButton(GeneralButtons.HELP, recentsPanel);

        addButtonsToAnimations("Remove Animation", null);
        addButtonsToAnimations("Slide In", new SlideIn());
        /*addButtonsToAnimations("Animation 2");
        addButtonsToAnimations("Animation 3");
        addButtonsToAnimations("Animation 4");
        addButtonsToAnimations("Animation 5");
        addButtonsToAnimations("Animation 6");
        addButtonsToAnimations("Animation 7");*/

        addButtonsToTransition("Transition 1");
        addButtonsToTransition("Transition 2");
        addButtonsToTransition("Transition 3");
        addButtonsToTransition("Transition 4");
        addButtonsToTransition("Transition 5");
        addButtonsToTransition("Transition 6");
        addButtonsToTransition("Transition 7");


        help = makeToolbarButton(GeneralButtons.HELP, recentsPanel);

        this.add(present);
        this.add(presentAt);

        separator(this);

        this.add(animations);

        separator(this);

        this.add(transistions);

        separator(this);

        this.add(help);

        this.setName("View");
        this.setFloatable(false);
    }

    private void addButtonsToTransition(String title) {
        JButton button = new JButton("<html> " + title + "</html>");
        button.setPreferredSize(new Dimension(76, 76));
        button.setFocusable(false);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        transistionsPanel.add(button);
    }

    private void addButtonsToAnimations(String title, Animation animation) {
        // Create button and assign its title
        JButton button = new JButton("<html> " + title + "</html>");
        
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

    private void addAnimationToElement(Animation animation) {
        // Get the selected element
        ExtendedElement selectedElement = PresentationManager.instance.getSelectedElement();


        /*
         * If the selected element is not null set the elmenent's animation to the animation passed in
         * and then run that animation just to show users what it looks like.
         */
        if(selectedElement != null) {

            MainDisplayPanel.instance.getCurrentSlideImage().getSlide().getElementAnimations().add(animation);

            animation.setSelectedElement(selectedElement);

            selectedElement.setAnimation(animation);

            animation.doAnimation();
            //System.out.println("This animation has been assigned to the selected element!");
        } else {
            // Open a dialog box to show users that an element has not be selected
            System.out.println("No element has been selected!");
        }
    }

    private void removeAnimationFromElement() {
        ExtendedElement selectedElement = PresentationManager.instance.getSelectedElement();


        /*
         * If the selected element is not null set the elmenent's animation to the animation passed in
         * and then run that animation just to show users what it looks like.
         */
        if(selectedElement != null) {
            selectedElement.getAnimation().setSelectedElement(null);

            selectedElement.setAnimation(null);

            System.out.println("Animation removed from element");

            //animation.doAnimation();
            //System.out.println("This animation has been assigned to the selected element!");
        } else {
            // Open a dialog box to show users that an element has not be selected
            System.out.println("No element has been selected!");
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
