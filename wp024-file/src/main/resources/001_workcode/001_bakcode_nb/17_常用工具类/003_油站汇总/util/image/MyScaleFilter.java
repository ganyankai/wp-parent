package com.catt.common.util.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class MyScaleFilter {
	private int width;
	private int height;

	public MyScaleFilter() {
		this(32, 32);
	}

	public MyScaleFilter(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public BufferedImage filter(BufferedImage src, BufferedImage dest) {
		if (dest == null) {
			ColorModel dstCM = src.getColorModel();
			dest = new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(width, height), dstCM.isAlphaPremultiplied(), null);
		}

		Image scaleImage = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		Graphics2D g = dest.createGraphics();
		g.drawImage(scaleImage, 0, 0, width, height, null);
		g.dispose();

		return dest;
	}

}
