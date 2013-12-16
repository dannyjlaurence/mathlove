package com.neonbats.mathlove;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;

/**
 * 
 * @author Chase
 * 
 */
abstract class Shape implements Comparable<Shape> {
	/**
	 * the pixel margin of all shapes. this depth into the shape is used for
	 * calculating rotations and resizing, deeper in is for translation
	 */
	static double margin = 30;
	static double min=90;

	static int RECURSE_COLOR = Color.RED;
	static int FIRST_ITERATION_COLOR = Color.MAGENTA;
	static int BASE_SHAPE_COLOR = Color.BLUE;
	static int RECURSE_COLOR_ALPHA = 255;
	static int FIRST_ITERATION_COLOR_ALPHA = 255;
	static int BASE_SHAPE_COLOR_ALPHA = 255;
	static boolean Fill;

	/**
	 * All shapes can have a recursive shape to them
	 */
	RecursiveShape recursiveShape;
	/**
	 * degree base shape is rotated
	 */
	int rotation = 0;
	/**
	 * number of sides any shape has, 1 entails a circle, 2 is meaningless, and
	 * all following side numbers form regular polygons around a given point.
	 */
	int sides = 0;

	/**
	 * The base distance from central point to vertices, the radius of circles
	 * circumscribing the given polygon, or the radius of the circle, if shape
	 * is a circle
	 */
	int rad = 45;

	/**
	 * center point of basic shape or first iteration of recursive shape
	 */
	Point base;

	/**
	 * x coordinates of vertices, or center point if a circle
	 */
	protected ArrayList<Float> x = new ArrayList<Float>();
	/**
	 * y coordinates of vertices, or the radius if a circle
	 */
	protected ArrayList<Float> y = new ArrayList<Float>();
	/**
	 * x coordinates of vertices, or center point if a circle
	 */
	protected ArrayList<Float> xMarg = new ArrayList<Float>();
	/**
	 * y coordinates of vertices, or the radius if a circle
	 */
	protected ArrayList<Float> yMarg = new ArrayList<Float>();

	/**
	 * draw the given shape on the canvas c, using the given paint and path
	 * 
	 * @param c
	 * @param paint
	 * @param path
	 */
	abstract void draw(Canvas c, Paint paint, Path path);

	/**
	 * 
	 * @param test
	 * @return true if test is within the shape based on the contains method
	 */
	boolean containsCenter(Point test) {
		return contains(this.sides, this.xMarg, this.yMarg, test);
	}

	/**
	 * 
	 * @param s
	 *            the shape this image recurses with.
	 */
	void setRecusiveShape(RecursiveShape s) {
		this.recursiveShape = s;
	}

	/**
	 */
	@Override
	public int compareTo(Shape s) {
		if (this instanceof BasicShape && s instanceof BasicShape)
			return 0;
		if (this instanceof RecursiveShape && s instanceof RecursiveShape)
			return 0;
		if (this instanceof RecursiveShape && s instanceof BasicShape)
			return -1;

		return 1;
	}

	/**
	 * 
	 * @param test
	 * @return true if test is contained within the shape, but not its center
	 */
	boolean containsBoarder(Point test) {
		return !containsCenter(test)
				&& contains(this.sides, this.x, this.y, test);
	}

	/**
	 * This method follow the Jordan Curve Theorem as implemented by W. Randolph
	 * Franklin at
	 * http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html
	 * for C, altered for Java
	 * 
	 * @param n
	 * @param x
	 * @param y
	 * 
	 * @param test
	 * @return if the test point is contained within the shape, but not within
	 *         the center.
	 */
	@SuppressWarnings("boxing")
	public static boolean contains(int n, ArrayList<Float> x,
			ArrayList<Float> y, Point test) {
		// in case of a circle
		if (n == 1) {
			return Math.pow((x.get(0) - test.x), 2)
					+ Math.pow((x.get(1) - test.y), 2) <= Math.pow(y.get(0), 2);
		}

		int i, j;
		boolean ret = false;
		for (i = 0, j = n - 1; i < n; j = i++) {
			if (((y.get(i) > test.y) != (y.get(j) > test.y))
					&& (test.x < ((((x.get(j) - x.get(i)) * (test.y - y.get(i))) / (y
							.get(j) - y.get(i))) + x.get(i))

					))
				ret = !ret;
		}
		return ret;
	}

	/**
	 * draws a shape on a given canvas, requires the coordinates of shape's
	 * vertices
	 * 
	 * @param n
	 *            number of vertices, n=1 implies a circle
	 * @param x
	 *            x vertices or the circles center
	 * @param y
	 *            y vertices or circle's radius
	 * @param c
	 *            canvas to paint on
	 * @param p
	 *            paint to paint with
	 * @param path
	 *            path to paint
	 */
	@SuppressWarnings("boxing")
	public static void draw(int n, ArrayList<Float> x, ArrayList<Float> y,
			Canvas c, Paint p, Path path) {
		if (Fill)
			p.setStyle(Style.FILL);
		else
			p.setStyle(Style.STROKE);
		path.reset();
		p.setStrokeWidth(0);
		// if less than 1 side, draw a circle
		if (n == 1) {
			c.drawCircle(x.get(0), x.get(1), y.get(0), p);
			return;
		}

		path.moveTo(x.get(0), y.get(0));
		for (int size = x.size() - 1; size > -1; size--)
			path.lineTo(x.get(size), y.get(size));
		c.drawPath(path, p);
		p.setAlpha(255);
	}

	/**
	 * Basic n sided shapes.
	 * 
	 * @author Chase
	 * 
	 */
	public static class BasicShape extends Shape {

		/**
		 * 
		 * create a shape with base rotation and radius definitions for a given
		 * center and number of points
		 * 
		 * @param center
		 * @param points
		 */
		BasicShape(Point center, int points) {
			if (points == 4)
				this.rotation = 45;
			setShape(center, points, this.rotation, this.rad);

		}

		/**
		 * create a shape
		 * 
		 * @param center
		 *            center of the shape
		 * @param points
		 *            number of sides the shape will have, -1 implies a circle
		 * @param rot
		 *            rotation of shape
		 * @param r
		 *            distance from center to vertices
		 */
		@SuppressWarnings("boxing")
		void setShape(Point center, int points, int rot, float r) {
			// set variables
			this.base = center;
			this.sides = points;
			this.rotation = rot;
			this.rad = (int) r;
			this.x.clear();
			this.y.clear();
			this.xMarg.clear();
			this.yMarg.clear();

			// build shape
			if (points == 1) {
				this.x.add((float) center.x);
				this.x.add((float) center.y);
				this.y.add(r);
				this.xMarg.add((float) center.x);
				this.xMarg.add((float) center.y);
				this.yMarg.add((float) (this.rad - margin));
			} else {
				double angle = Math.toRadians(this.rotation);
				double increment = Math.toRadians((360 / this.sides));

				// otherwise locate vertices of the n sided shape, and its inner
				// margin bound
				// shape
				int n = this.sides;
				while (n > -1) {
					this.x.add(Float
							.valueOf((float) (this.base.x + (this.rad * Math
									.cos(angle)))));
					this.y.add(Float
							.valueOf((float) ((this.base.y + (this.rad * Math
									.sin(angle))))));
					this.xMarg
							.add(Float
									.valueOf((float) (this.base.x + ((this.rad - margin) * Math
											.cos(angle)))));
					this.yMarg
							.add(Float
									.valueOf((float) (this.base.y + ((this.rad - margin) * Math
											.sin(angle)))));

					angle += increment;
					n--;
				}
			}
			if (this.recursiveShape != null)
				this.recursiveShape.setShape(this.recursiveShape.base,
						this.recursiveShape.tiltAngle,
						this.recursiveShape.scale);
		}

		/**
		 * Draw the given shape on the Canvas
		 * 
		 * @param c
		 * @param path
		 * @param paint
		 */
		@Override
		public void draw(Canvas c, Paint paint, Path path) {
			int col = paint.getColor();
			int al = paint.getAlpha();
			paint.setColor(BASE_SHAPE_COLOR);
			paint.setAlpha(BASE_SHAPE_COLOR_ALPHA);
			super.draw(this.sides, this.x, this.y, c, paint, path);
			paint.setColor(col);
			paint.setAlpha(al);
		}
	}

	/**
	 * A Recursive shape consists of a parent shape that is drawn repeatedly so
	 * that it recurses
	 */
	public static class RecursiveShape extends Shape {

		/** shape of parent image */
		Shape parent;

		/** how far each image is from the one before it */
		double separationDistance = this.rad;

		/** angle first iteration should move in */
		double separationAngle = 0;

		/** angle further iterations should move in */
		double tiltAngle = 0;

		/** how much to scale image by at each iteration of recursion */
		double scale = 1;

		/**
		 * create a recursive shape from a baseshape
		 * 
		 * @param parent1
		 * @param base1
		 */
		RecursiveShape(Shape parent1, Point base1) {
			this.parent = parent1;
			setShape(base1, this.tiltAngle, this.scale);
			this.parent.setRecusiveShape(this);
		}

		/**
		 * create a shape
		 * 
		 * @param center
		 *            center of the first iteration's shape, used to find
		 *            separation angle and distance from parent's base point
		 * @param tilt
		 *            angle subsequent shapes veer in from thebase angle
		 * @param scale1
		 *            ratio between first iteration's size and second iterations
		 *            size
		 */
		@SuppressWarnings("boxing")
		void setShape(Point center, double tilt, double scale1) {
			// set variables
			this.base = center;
			this.separationDistance = Math.sqrt(Math.pow(this.parent.base.x
					- this.base.x, 2)
					+ Math.pow(this.parent.base.y - this.base.y, 2));
			if (this.parent.base.y == this.base.y)
				this.separationAngle = 0;
			else if (this.parent.base.x == this.base.x)
				this.separationAngle = Math.PI / 2;
			else
				this.separationAngle = Math.atan2(this.base.y
						- this.parent.base.y, this.base.x - this.parent.base.x);
			this.tiltAngle = tilt;
			this.scale = scale1;
			this.x.clear();
			this.y.clear();
			this.xMarg.clear();
			this.yMarg.clear();
			this.sides = this.parent.sides;
			this.rad = this.parent.rad;
			this.rotation = this.parent.rotation;
			Point drawPoint = new Point(this.base);
			double drawPointAngle = this.separationAngle;
			double size = this.rad * this.scale;

			int n = this.sides;
			if (n == 1) {
				this.x.add((float) drawPoint.x);
				this.x.add((float) drawPoint.y);
				this.y.add((float) size);
				this.xMarg.add((float) drawPoint.x);
				this.xMarg.add((float) drawPoint.y);
				this.yMarg.add((float) (size - margin));
			} else {
				double shapeIncrement = Math.toRadians(360 / this.sides);
				double ang = drawPointAngle
						+ Math.toRadians(this.parent.rotation);
				while (n > -1) {

					this.x.add((float) (drawPoint.x + size * Math.cos(ang)));
					this.y.add((float) (drawPoint.y + size * Math.sin(ang)));
					this.xMarg.add((float) (drawPoint.x + (size - margin)
							* Math.cos(ang)));
					this.yMarg.add((float) (drawPoint.y + (size - margin)
							* Math.sin(ang)));
					ang += shapeIncrement;
					n--;
				}
			}
		}

		/**
		 * 
		 */
		@SuppressWarnings("boxing")
		@Override
		void draw(Canvas c, Paint paint, Path path) {
			int col = paint.getColor();
			int al = paint.getAlpha();
			ArrayList<Float> xDraw = new ArrayList<Float>();
			ArrayList<Float> yDraw = new ArrayList<Float>();
			paint.setColor(FIRST_ITERATION_COLOR);
			paint.setAlpha(FIRST_ITERATION_COLOR_ALPHA);
			Point drawPoint = new Point(this.base);
			double drawPointAngle = this.separationAngle;
			double size = this.rad * this.scale;

			double sep = this.separationDistance;

			int iterations = 0;
			while (size > 10 && size < c.getHeight() && size < c.getWidth()
					&& iterations < 1000) {
				iterations++;
				if (drawPoint.x > 0 && drawPoint.y > 0
						&& drawPoint.x < c.getWidth()
						&& drawPoint.y < c.getHeight()) {
					int n = this.sides;
					if (n == 1) {
						xDraw.add((float) drawPoint.x);
						xDraw.add((float) drawPoint.y);
						yDraw.add((float) size);
						super.draw(n, xDraw, yDraw, c, paint, path);
					} else {
						double shapeIncrement = Math
								.toRadians(360 / this.sides);
						double ang = drawPointAngle
								+ Math.toRadians(this.parent.rotation);
						while (n > -1) {
							xDraw.add((float) (drawPoint.x + size
									* Math.cos(ang)));
							yDraw.add((float) (drawPoint.y + size
									* Math.sin(ang)));
							ang += shapeIncrement;
							n--;
						}
						super.draw(this.sides, xDraw, yDraw, c, paint, path);
					}
				}

				size *= this.scale;
				drawPointAngle += this.tiltAngle;
				sep *= this.scale;
				drawPoint.x += sep * Math.cos(drawPointAngle);
				drawPoint.y += sep * Math.sin(drawPointAngle);
				xDraw.clear();
				yDraw.clear();
				paint.setColor(Shape.RECURSE_COLOR);
				paint.setAlpha(Shape.RECURSE_COLOR_ALPHA);
			}
			paint.setAlpha(al);
			paint.setColor(col);
		}

	}
}