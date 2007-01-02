package com.sirenian.hellbound.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.Map;

import com.sirenian.hellbound.domain.Segment;
import com.sirenian.hellbound.domain.Segments;

public class RenderedPit extends Graphics {

	private final int scale;
	private Color color;
	private Map pitMap;
    private char[][] asciiRepresentation;
    private final TypeAndColorMap colormap;
    private final int pitWidth;
    private final int pitHeight;

	public RenderedPit(int scale, int width, int height, TypeAndColorMap colormap) {
		this.scale = scale;
        this.pitWidth = width;
        this.pitHeight = height;
        this.colormap = colormap;
		pitMap = new HashMap(); 
        asciiRepresentation = new char[height][width];
	}

	public void dispose() {	}

	public void setPaintMode() {}

	public void translate(int x, int y) {}

	public void clearRect(int x, int y, int width, int height) {}

	public void clipRect(int x, int y, int width, int height) {}

	public void drawLine(int x1, int y1, int x2, int y2) {}

	public void drawOval(int x, int y, int width, int height) {}

	public void fillOval(int x, int y, int width, int height) {}

	public void setClip(int x, int y, int width, int height) {}

	public void copyArea(int x, int y, int width, int height, int dx, int dy) {}

	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {}

	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {}

	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {}

	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {}

	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {}

	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {}

	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {}

	public Color getColor() { return null;}

	public void setXORMode(Color c1) {}

	public Font getFont() { return null;}

	public void setFont(Font font) {}

	public Graphics create() {return null;}

	public Rectangle getClipBounds() {return null;}

	public Shape getClip() { return null;}

	public void setClip(Shape clip) {}

	public void drawString(String str, int x, int y) {}

	public void drawString(AttributedCharacterIterator iterator, int x, int y) {}

	public FontMetrics getFontMetrics(Font f) {return null;}

	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		return false;
	}

	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		return false;
	}

	public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
		return false;
	}

	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
		return false;
	}

	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		return false;
	}

	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		return false;
	}

	public void setColor(Color c) {
		color = c;
	}	
	
	public void fillRect(int x, int y, int width, int height) {
		int scaledX = x / scale;
		int scaledY = y / scale;
		
        if (scaledX >= 0 && scaledX < pitWidth && scaledY >=0 && scaledY < pitHeight) { 
            pitMap.put(new Segment(scaledX, scaledY), color);
            asciiRepresentation[scaledY][scaledX] = colormap.getTypeFor(color).toAscii();
        }
	}

	public Color getColor(Segment square) {
		return (Color) pitMap.get(square);
	}

	public Map getColoredSegments() {
		return pitMap;
	}

    public boolean contains(Segments expectedSegments, Color c) {
        for (int i = 0; i < expectedSegments.size(); i++) {
            Object colorAtSegment = pitMap.get(expectedSegments.get(i));
            if (colorAtSegment == null || !colorAtSegment.equals(c)) {
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(System.getProperty("line.separator"));
        for (int i = 0; i < asciiRepresentation.length; i++) {
            buffer.append(String.valueOf(asciiRepresentation[i])).append(System.getProperty("line.separator"));
        }
        return buffer.toString();
    }
}
