package com.catt.common.util.image;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Position;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.OutputStream;

/**
 * java操作图片类库（包括生成图像缩略图、裁切、旋转、添加水印等操作）
 * @author liangguanglong
 *
 */
public class ImageUtil {
	/**
	 * 设置图片相关参数
	 * @param sourcePath 要添加水印的图片（源图片）
	 * @param watermarkPath 水印图片
	 * @param x 距离源图片顶部的位置
	 * @param y 距离源图片左边的位置
	 * @throws Exception 
	 */
	private static Builder<File> setting(String sourcePath,String watermarkPath,int x,int y) throws Exception{
		//设置水印位置
		Position p = new Position() {
			@Override
			public Point calculate(int enclosingWidth, int enclosingHeight, int width, int height, int insetLeft, int insetRight, int insetTop, int insetBottom) {
				return new Point(x+insetTop, y+insetLeft);
			}
		};
		//判断文件路径不能为空
		Assert.hasText(sourcePath, "'sourcePath'不能为空");
		Assert.hasText(watermarkPath, "'watermarkPath'不能为空");
		//进行链式调用，添加水印
		Builder<File> builder = Thumbnails.of(sourcePath)//读取源文件
		.scale(1.0d)//设置比例大小不变，与源图片一样，如需要修改，查看相关方法-->size();
		.watermark(p, ImageIO.read(new File(watermarkPath)), 1f)//设置水印图片，1f表示透明度
		.outputQuality(1.0f);//设置输出图片质量
		//.toFile(targerPath);
		return builder;
	}
	/**
	 * 
	 * @param sourcePath 要添加水印的图片（源图片）
	 * @param watermarkPath 水印图片
	 * @param x 距离源图片顶部的位置
	 * @param y 距离源图片左边的位置
	 * @param targerPath 生成最终图片路径
	 * @throws Exception
	 */
	public static void watermarkForFile(String sourcePath,String watermarkPath,int x,int y,String targerPath) throws Exception{
		//得到图片设置之后的Builder
		Builder<File> setting = com.catt.common.util.image.ImageUtil.setting(sourcePath, watermarkPath, x, y);
		//把图片写入目录文件
		setting.toFile(targerPath);
	}
	
	/**
	 * 
	 * @param sourcePath 要添加水印的图片（源图片）
	 * @param watermarkPath 水印图片
	 * @param x 距离源图片顶部的位置
	 * @param y 距离源图片左边的位置
	 * @param os 把文件输出的流
	 * @throws Exception
	 */
	public static void watermarkForStream(String sourcePath,String watermarkPath,int x,int y,OutputStream os) throws Exception{
		//得到图片设置之后的Builder
		Builder<File> setting = com.catt.common.util.image.ImageUtil.setting(sourcePath, watermarkPath, x, y);
		//把图片写入目录文件
		setting.toOutputStream(os);
	}
}
