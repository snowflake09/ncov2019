package com.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片转换处理类
 * 
 * @author AlawnPang
 *
 * @ date 2016-02-18
 *
 */
public class ImageResizer {

	/**
	 * 按照指定宽度计算比例缩放图片
	 * 
	 * @param originalImgPath
	 *            初始图片路径
	 * @param newImgPath
	 *            新 图片路径
	 * @param newWidth
	 *            生成新图片的宽
	 * @param suffix
	 *            文件后缀
	 * @throws IOException
	 */
	public static void resizeImage(String originalImgPath, String newImgPath, float newWidth, String suffix)
			throws IOException {
		File srcFile = new File(originalImgPath);
		Image srcImg = ImageIO.read(srcFile);
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(srcFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 图片原始宽度
		int originalWidth = bi.getWidth();
		// 图片原始高度
		int originalHeight = bi.getHeight();
		// 按照宽度计算的缩放比例
		float multiple =  originalWidth / newWidth;
		// 生成新图片的高度
		float newHeight = originalHeight / multiple;

		BufferedImage buffImg = null;
		buffImg = new BufferedImage((int)newWidth, (int)newHeight, BufferedImage.TYPE_INT_RGB);
		buffImg.getGraphics().drawImage(srcImg.getScaledInstance((int)newWidth, (int)newHeight, Image.SCALE_SMOOTH), 0, 0, null);
		ImageIO.write(buffImg, suffix, new File(newImgPath));

	}

}
