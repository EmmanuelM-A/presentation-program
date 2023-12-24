package com.scc210groupproject.ui;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.scc210groupproject.action.GoToSlideAction;
import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.structure.eventListeners.IChangePresentationListener;
import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SelectorPane extends JScrollPane implements IChangePresentationListener, ICreateSlideListener, IDiscardSlideListener
{
    public static SelectorPane instance;

    private JPanel client = new JPanel();
    private ArrayList<GoToSlideAction> actions = new ArrayList<>();

    public SelectorPane()
    {
        super(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        super.setPreferredSize(new Dimension(0, 150));

        client = new JPanel();
        client.setLayout(new BoxLayout(client, BoxLayout.X_AXIS));
        super.setViewportView(client);

        Presentation.addCreateSlideListener(this);
        Presentation.addDiscardSlideListener(this);
        Presentation.addChangePresentationListener(this);

        instance = this;
    }

    @Override
    public void onDiscardSlide(int index, Slide slide)
    {
        client.remove(index);
        actions.remove(index);
        for (int i = index; i < actions.size(); i++)
            actions.get(i).index -= 1;

        super.validate();
    }

    @Override
    public void onCreateSlide(int index, Slide slide)
    {
        JButton button = new JButton();
        button.setLayout(null);
        button.setBorder(null);

        double ratio = (double)slide.asComp().getWidth() / (double)slide.asComp().getHeight();
        int height = super.getHeight() - super.getHorizontalScrollBar().getHeight();
        Dimension dimension = new Dimension((int)(height * ratio), height);

        BufferedImage preview = slide.createPreview(dimension);
        button.setIcon(new ImageIcon(preview));
        button.setSize(dimension);
        button.setPreferredSize(dimension);
        button.setMinimumSize(dimension);
        button.setMaximumSize(dimension);

        GoToSlideAction action = new GoToSlideAction(index);
        button.addActionListener(action);

        if (index >= client.getComponentCount())
        {
            for (int i = client.getComponentCount(); i < index; i++) {
                client.add(new JButton());
                actions.add(new GoToSlideAction(-1));
            }

            client.add(button, index);
            actions.add(index, action);
        }
        else
        {
            client.remove(index);
            client.add(button, index);

            actions.set(index, action);
        }

        super.validate();
    }

    @Override
    public void onChangePresentation(Presentation current, Presentation discarded) {
        client.removeAll();

        super.validate();
    }
}
