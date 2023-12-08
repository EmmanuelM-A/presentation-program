package com.scc210groupproject.ui;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.scc210groupproject.structure.Presentation;
import com.scc210groupproject.structure.Slide;
import com.scc210groupproject.structure.eventListeners.IChangePresentationListener;
import com.scc210groupproject.structure.eventListeners.ICreateSlideListener;
import com.scc210groupproject.structure.eventListeners.IDiscardSlideListener;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

public class SelectorPane extends JScrollPane implements IChangePresentationListener, ICreateSlideListener, IDiscardSlideListener
{
    public static SelectorPane instance;

    private JPanel client = new JPanel();

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

        super.validate();
    }

    @Override
    public void onCreateSlide(int index, Slide slide) 
    {
        JLabel label = new JLabel();
        double ratio = (double)slide.asComp().getWidth() / (double)slide.asComp().getHeight();
        BufferedImage preview = slide.createPreview(new Dimension((int)(super.getHeight() * ratio), super.getHeight()));
        label.setIcon(new ImageIcon(preview));

        if (index >= client.getComponentCount())
        {
            for (int i = client.getComponentCount(); i < index; i++)
                client.add(new JLabel());

            client.add(label, index);
        }
        else
        {
            client.remove(index);
            client.add(label, index);
        }

        super.validate();
    }

    @Override
    public void onChangePresentation(Presentation current, Presentation discarded) {
        client.removeAll();

        super.validate();
    }
}
