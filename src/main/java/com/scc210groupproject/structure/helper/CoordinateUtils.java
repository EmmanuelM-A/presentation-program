package com.scc210groupproject.structure.helper;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import com.scc210groupproject.structure.BaseElement;
import com.scc210groupproject.structure.Slide;

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

            above = above.getParent();
        }

        return new Point(x, y);
    }
    

    public static Point convertSlideToLocalSpace(Point global, BaseElement element)
    {
        Point offset = convertLocalToSlideSpace(new Point(0, 0), element);

        return new Point(global.x - offset.x, global.y - offset.y);
    }
    
    public static Shape getTransformed(Shape original, AffineTransform transform, double tx, double ty, double r, double sx, double sy) {
        transform.setToScale(sx, sy);
        Shape scaled = transform.createTransformedShape(original);
        transform.setToRotation(r);
        Shape rotated = transform.createTransformedShape(scaled);
        transform.setToTranslation(tx, ty);
        return transform.createTransformedShape(rotated);
    }
}
