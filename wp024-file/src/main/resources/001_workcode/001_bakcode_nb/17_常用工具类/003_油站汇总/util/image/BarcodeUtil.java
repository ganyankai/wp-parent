package com.catt.common.util.image;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类
 * 
 * @author 纪建宏
 * 
 */
public class BarcodeUtil {

	/**
	 * 中间图片边框宽度
	 */
	private static final int FRAME_WIDTH = 2;

	// 二维码写码器
	private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

	/**
	 * 生成二维码到指定路径
	 * 
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param srcImagePath
	 *            中间图片路径
	 * @param destImagePath
	 *            目标图片路径
	 */
	public static void encode(String content, int width, int height,
			String srcImagePath, String destImagePath) {
		try {
			encode(content, width, height, srcImagePath, new FileOutputStream(
					destImagePath));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成二维码到指定输出流
	 * 
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param srcImagePath
	 *            中间图片路径
	 * @param outputStream
	 *            目标输出流
	 */
	public static void encode(String content, int width, int height,
			String srcImagePath, OutputStream outputStream) {
		try {
			BufferedImage image = genBarcode(content, width, height,
					srcImagePath);
			ImageIO.write(image, "jpg", outputStream);
		} catch (Exception e) {
			throw new RuntimeException("生成二维码图片失败", e);
		}
	}

	/**
	 * 生成二维码图片
	 * 
	 * @param content
	 *            二维码内容
	 * @param width
	 *            图片宽度
	 * @param height
	 *            图片高度
	 * @param srcImagePath
	 *            中间图片路径
	 * @return
	 * @throws WriterException
	 * @throws IOException
	 */
	private static BufferedImage genBarcode(String content, int width,
			int height, String srcImagePath) throws WriterException,
			IOException {
		int imageSize = Math.min(width, height) / 6;
		int imageHalfSize = imageSize / 2;
		int[][] srcPixels = null;

		if (srcImagePath != null) {
			// 读取源图像
			BufferedImage scaleImage = scale(srcImagePath, imageSize,
					imageSize, true);
			srcPixels = new int[imageSize][imageSize];
			for (int i = 0; i < scaleImage.getWidth(); i++) {
				for (int j = 0; j < scaleImage.getHeight(); j++) {
					srcPixels[i][j] = scaleImage.getRGB(i, j);
				}
			}
		}

		Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
		hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hint.put(EncodeHintType.MARGIN, 0);
		// 生成二维码
		BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,
				width, height, hint);

		// 二维矩阵转为一维像素数组
		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;
		int[] pixels = new int[width * height];

		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				// 读取图片
				if (srcPixels != null
						&& (x > halfW - imageHalfSize
								&& x < halfW + imageHalfSize
								&& y > halfH - imageHalfSize && y < halfH
								+ imageHalfSize)) {
					pixels[y * width + x] = srcPixels[x - halfW + imageHalfSize][y
							- halfH + imageHalfSize];
				} else if (srcPixels != null
						&& ((x > halfW - imageHalfSize - FRAME_WIDTH
								&& x < halfW - imageHalfSize + FRAME_WIDTH
								&& y > halfH - imageHalfSize - FRAME_WIDTH && y < halfH
								+ imageHalfSize + FRAME_WIDTH)
								|| (x > halfW + imageHalfSize - FRAME_WIDTH
										&& x < halfW + imageHalfSize
												+ FRAME_WIDTH
										&& y > halfH - imageHalfSize
												- FRAME_WIDTH && y < halfH
										+ imageHalfSize + FRAME_WIDTH)
								|| (x > halfW - imageHalfSize - FRAME_WIDTH
										&& x < halfW + imageHalfSize
												+ FRAME_WIDTH
										&& y > halfH - imageHalfSize
												- FRAME_WIDTH && y < halfH
										- imageHalfSize + FRAME_WIDTH) || (x > halfW
								- imageHalfSize - FRAME_WIDTH
								&& x < halfW + imageHalfSize + FRAME_WIDTH
								&& y > halfH + imageHalfSize - FRAME_WIDTH && y < halfH
								+ imageHalfSize + FRAME_WIDTH))) {
					pixels[y * width + x] = 0xfffffff; // 在图片四周形成边框
				} else {
					// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
					pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
							: 0xfffffff;
				}
			}
		}

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, width, height, pixels);

		return image;
	}

	/**
	 * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
	 * 
	 * @param srcImageFile
	 *            源文件地址
	 * @param width
	 *            目标宽度
	 * @param height
	 *            目标高度
	 * @param hasFiller
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 * @throws IOException
	 */
	private static BufferedImage scale(String srcImageFile, int width,
			int height, boolean hasFiller) throws IOException {
		double ratio = 0.0; // 缩放比例
		File file = new File(srcImageFile);
		BufferedImage srcImage = ImageIO.read(file);
		Image destImage = srcImage.getScaledInstance(width, height,
				BufferedImage.SCALE_SMOOTH);
		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue()
						/ srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue()
						/ srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(
					AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {// 补白
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0,
						(height - destImage.getHeight(null)) / 2,
						destImage.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			else
				graphic.drawImage(destImage,
						(width - destImage.getWidth(null)) / 2, 0,
						destImage.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}

}
