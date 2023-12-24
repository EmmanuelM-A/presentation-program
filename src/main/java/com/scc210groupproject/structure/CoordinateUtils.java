package com.scc210groupproject.structure;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

public class CoordinateUtils {

    public static Point convertRelativeToSlideSpace(Point2D.Double relative, BaseElement element) {

        Point local = CoordinateUtils.convertRelativeToLocalSpace(relative, element);
        Point global = CoordinateUtils.convertLocalToSlideSpace(local, element);

        return global;
    }

    public static Point convertRelativeToLocalSpace(Point2D.Double relative, BaseElement element) {

        Dimension dimension = element.asComp().getSize();

        return new Point(
            (int)(dimension.getWidth() * relative.x),
            (int)(dimension.getHeight() * relative.y)
        );
    }

    public static Point convertLocalToSlideSpace(Point local, BaseElement element)
    {
        Point p = local;

        BaseElement above = element;
        while (above != null) {
            if (above instanceof Slide)
                break;

            Point space = above.asComp().getLocation();
            p.setLocation(
                space.x + p.x,
                space.y + p.y);

            above = above.parent;
        }

        return p;
    }
}
