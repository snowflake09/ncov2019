package com.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码
 * 
 * @author LiQiang
 * @date 2017年1月13日
 */
public class QRCode {

	/**
	 * 生成二维码
	 * 
	 * @param content
	 * @param qrPath
	 * @author LiQiang
	 * @date 2017年1月13日
	 */
	public static String encode(String content, String qrPath,String folderName) {
		SimpleDateFormat yearmonth = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat dayhour = new SimpleDateFormat("ddHH");
		/** 构建图片保存的目录 **/
		String picPathDir = "/files/" + folderName+"/"+ yearmonth.format(new Date()) +"/"+dayhour.format(new Date());
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String savePath = "";
		String fileRealPathDir = qrPath + picPathDir + "/" + uuid + "/";
		/** 根据真实路径创建目录 **/
		File picSaveFile = new File(fileRealPathDir);
		if (!picSaveFile.exists())
			picSaveFile.mkdirs();
		String fileName = CommonContants.PIC_NAME_BIG;
		int width = 200; // 图像宽度
		int height = 200; // 图像高度
		String format = "png";// 图像类型
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			// 生成矩阵
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			Path path = FileSystems.getDefault().getPath(fileRealPathDir, fileName);
			// 输出图像
			MatrixToImageWriter.writeToPath(bitMatrix, format, path);
			savePath = picPathDir + "/" + uuid + "/";
			return savePath;
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return savePath;
	}
	
	/**
	 * 生成带文字二维码
	 * 
	 * @param content
	 * @param qrPath
	 * @author LiQiang
	 * @date 2017年1月13日
	 */
	public static String encodeIsText(String text,String qrContent, String qrPath,String folderName) {
		SimpleDateFormat yearmonth = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat dayhour = new SimpleDateFormat("ddHH");
		/** 构建图片保存的目录 **/
		String picPathDir = "/files/" + folderName+"/"+ yearmonth.format(new Date()) +"/"+dayhour.format(new Date());
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String savePath = "";
		String fileRealPathDir = qrPath + picPathDir + "/" + uuid + "/";
		/** 根据真实路径创建目录 **/
		File picSaveFile = new File(fileRealPathDir,text+".jpg");
		if (!picSaveFile.exists())
			picSaveFile.mkdirs();
		try {
			writeToFile(qrContent, "jpg", picSaveFile);
			pressText(text, picSaveFile, 5, Color.BLACK, 92);
			savePath = picPathDir + "/" + uuid + "/"+text+".jpg";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return savePath;
	}

	/**
	 * 解析图像
	 */
	public static String decode(String fliePath) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(fliePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
			return result.getText();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	private static final int WIDTH = 720;
	private static final int HEIGHT = 720;

	/**
	 * 把生成的二维码存入到图片中
	 * 
	 * @param matrix
	 *            zxing包下的二维码类
	 * @return
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 生成二维码并写入文件
	 * 
	 * @param content
	 *            扫描二维码的内容
	 * @param format
	 *            图片格式 jpg
	 * @param file
	 *            文件
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void writeToFile(String content, String format, File file) throws Exception {
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		@SuppressWarnings("rawtypes")
		Map hints = new HashMap();
		// 设置UTF-8， 防止中文乱码
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		// 设置二维码四周白色区域的大小
		hints.put(EncodeHintType.MARGIN, 1);
		// 设置二维码的容错性
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 画二维码
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, WIDTH, HEIGHT+125, hints);
		BufferedImage image = toBufferedImage(bitMatrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	/**
	 * 给二维码图片加上文字
	 * 
	 * @param pressText
	 *            文字
	 * @param qrFile
	 *            二维码文件
	 * @param fontStyle
	 * @param color
	 * @param fontSize
	 */
	public static void pressText(String pressText, File qrFile, int fontStyle, Color color, int fontSize)
			throws Exception {
		pressText = new String(pressText.getBytes(), "utf-8");
		Image src = ImageIO.read(qrFile);
		int imageW = src.getWidth(null);
		int imageH = src.getHeight(null);
		BufferedImage image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		g.drawImage(src, 0, 0, imageW, imageH, null);
		// 设置画笔的颜色
		g.setColor(color);
		// 设置字体
		Font font = new Font("宋体", fontStyle, fontSize);
		FontMetrics metrics = g.getFontMetrics(font);
		// 文字在图片中的坐标 这里设置在中间
		int startX = (WIDTH - metrics.stringWidth(pressText)) / 2;
		int startY = HEIGHT + 120;
		g.setFont(font);
		g.drawString(pressText, startX, startY);
		g.dispose();
		FileOutputStream out = new FileOutputStream(qrFile);
		ImageIO.write(image, "JPEG", out);
		out.close();
	}

	public static void main(String[] args) {
	}

}
