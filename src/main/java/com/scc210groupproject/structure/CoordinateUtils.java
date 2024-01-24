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
        int x = local.x;
        int y = local.y;

        BaseElement above = element;
        while (above != null) {
            if (above instanceof Slide)
                break;

            Point space = above.asComp().getLocation();
            x += space.x;
            y += space.y;

            above = above.parent;
        }

        return new Point(x, y);
    }
    

    public static Point convertSlideToLocalSpace(Point global, BaseElement element)
    {
        Point offset = convertLocalToSlideSpace(new Point(0, 0), element);

        return new Point(global.x - offset.x, global.y - offset.y);
    }
}
