package com.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.model.common.SysFile;

/**
 * 附件上传
 * 
 * @author LiQiang
 * @date 2016年11月1日
 */
public class FileUpLoad {

	/**
	 * 图片上传
	 * 
	 * @param request
	 * @param fileUuid
	 * @return
	 * @author LiQiang
	 * @date 2016年11月1日
	 */
	public static SysFile uploadPicture(MultipartFile multipartFile, HttpServletRequest request, String fileUuid,String folderName) {
		SysFile sysFile = new SysFile();
		try {
			SimpleDateFormat yearmonth = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat dayhour = new SimpleDateFormat("ddHH");
			/** 构建图片保存的目录 **/
			String picPathDir = "/files/"+yearmonth.format(new Date())+"/"+folderName+"/" + dayhour.format(new Date());
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String picPath = picPathDir + "/" + uuid + "/";
			/** 得到图片保存目录的真实路径.tomcat下webapp路径 **/
			String picRealPathDir = getRootPath(request) + picPathDir + "/" + uuid + "/";
			/** 根据真实路径创建目录 **/
			File picSaveFile = new File(picRealPathDir);
			if (!picSaveFile.exists())
				picSaveFile.mkdirs();
			/** 页面控件的文件流 **/
			// 原始图片名称
			String picName = multipartFile.getOriginalFilename();
			/** 获取文件的后缀 **/
			String suffix = picName.substring(picName.lastIndexOf("."));
			/** 使用UUID生成文件名称 **/
			String saveFileName = CommonContants.PIC_NAME_SAVE + suffix;// 构建文件名称
			/** 拼成完整的文件保存路径加文件 **/
			String fileName = picRealPathDir + saveFileName;
			File file = new File(fileName);
			multipartFile.transferTo(file);
			/** 其他格式图片存放 **/
			// 小图 640*比例
			String newImgPath3 = picRealPathDir + CommonContants.PIC_NAME_BIG;
			ImageResizer.resizeImage(fileName, newImgPath3, 880.0f, "jpg");
			sysFile.setFileName(picName);
			sysFile.setFilePath(picPath);
			sysFile.setFileUuid(fileUuid);
			sysFile.setSuffix(suffix);
			return sysFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysFile;
	}
	
	/**
	 * 图片上传
	 * 
	 * @param request
	 * @param fileUuid
	 * @return
	 * @author LiQiang
	 * @date 2016年11月1日
	 */
	public static String uploadMiniAppQR(byte[] bytes, HttpServletRequest request) {
		String path = "";
		try {
			SimpleDateFormat yearmonth = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat dayhour = new SimpleDateFormat("ddHH");
			/** 构建图片保存的目录 **/
			String picPathDir = "/files/"+yearmonth.format(new Date())+"/miniAppQR/" + dayhour.format(new Date());
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String picPath = picPathDir + "/" + uuid + "/";
			/** 得到图片保存目录的真实路径.tomcat下webapp路径 **/
			String picRealPathDir = getRootPath(request) + picPathDir + "/" + uuid + "/";
			/** 根据真实路径创建目录 **/
			File picSaveFile = new File(picRealPathDir);
			if (!picSaveFile.exists()){
				picSaveFile.mkdirs();
			}
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);    
	        BufferedImage bi1 =ImageIO.read(bais);
	        String fileName = picRealPathDir + CommonContants.PIC_NAME_BIG;
	        File w2 = new File(fileName);//可以是jpg,png,gif格式    
	        ImageIO.write(bi1, "jpg", w2);//不管输出什么格式图片，此处不需改动 
	        path = picPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 分享活动图片
	 * @param activityId
	 * @param width
	 * @param height
	 * @param request
	 * @return
	 * @author LiQiang
	 * @date 2018年6月19日
	 */
	public static String uploadMiniAppShareActivity(Long offeredId,Integer width,Integer height, HttpServletRequest request) {
		height = 1280;
		width = 720;
		String path = "";
		try {
			Runtime rt = Runtime.getRuntime();
			SimpleDateFormat yearmonth = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat dayhour = new SimpleDateFormat("ddHH");
			/** 构建图片保存的目录 **/
			String folderName = "activityShare";
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String picPathDir = "/files/"+yearmonth.format(new Date())+"/"+folderName+"/" + dayhour.format(new Date());
			String picPath = picPathDir + "/" + uuid + "/";
			String picRealPathDir = FileUpLoad.getRootPath(request) + picPathDir + "/" + uuid + "/";
			File picSaveFile = new File(picRealPathDir);
			if (!picSaveFile.exists())
				picSaveFile.mkdirs();
			/** 页面控件的文件流 **/
			// 原始图片名称
			/** 获取文件的后缀 **/
			String suffix = ".jpg";
			/** 使用UUID生成文件名称 **/
			String saveFileName = CommonContants.PIC_NAME_SAVE + suffix;// 构建文件名称
			/** 拼成完整的文件保存路径加文件 **/
			
			
			String fileName = picRealPathDir + saveFileName;
			String tomcatPath = FileUpLoad.getRootPath(request)+request.getContextPath();
//			String baseUrl = "http://" + request.getServerName()+":8081"+ request.getContextPath();
			String baseUrl = "http://" + request.getServerName()+ request.getContextPath();
//			System.out.println(baseUrl);
			rt.exec(tomcatPath+"/js/canvaspic/phantomjs --ssl-protocol=any --ignore-ssl-errors=true "+tomcatPath+"/js/canvaspic/test.js "+baseUrl+"/html/miniapp/sharePic.html?offeredId="
							+ offeredId + " " + fileName + " " + width
							+ " " + height);
	        path = picPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}

	/**
	 * 文件上传
	 * 
	 * @param multipartFile
	 * @param request
	 * @param fileUuid
	 * @return
	 * @author LiQiang
	 * @date 2016年11月4日
	 */
	public static SysFile uploadFile(MultipartFile multipartFile, HttpServletRequest request, String fileUuid,String folderName) {
		SysFile sysFile = new SysFile();
		try {
			SimpleDateFormat yearmonth = new SimpleDateFormat("yyyyMM");
			SimpleDateFormat dayhour = new SimpleDateFormat("ddHH");
			/** 构建图片保存的目录 **/
			String picPathDir = "/files/"+yearmonth.format(new Date())+"/"+folderName+"/" + dayhour.format(new Date());
			String uuid = UUID.randomUUID().toString().replace("-", "");
			String picPath = picPathDir + "/" + uuid + "/";
			/** 得到图片保存目录的真实路径.tomcat下webapp路径 **/
			String picRealPathDir = getRootPath(request) + picPathDir + "/" + uuid + "/";
			/** 根据真实路径创建目录 **/
			File picSaveFile = new File(picRealPathDir);
			if (!picSaveFile.exists())
				picSaveFile.mkdirs();
			/** 页面控件的文件流 **/
			// 文件名称
			String name = multipartFile.getOriginalFilename();
			/** 获取文件的后缀 **/
			String suffix = name.substring(name.lastIndexOf("."));
			/** 拼成完整的文件保存路径加文件 **/
			String fileName = picRealPathDir + name;
			File file = new File(fileName);
			multipartFile.transferTo(file);
			sysFile.setFileName(name);
			sysFile.setFilePath(picPath);
			sysFile.setFileUuid(fileUuid);
			sysFile.setSuffix(suffix);
			return sysFile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysFile;
	}

	/***
	 * 复制单个文件*
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt*
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt*@return boolean
	 */
	@SuppressWarnings({ "resource", "unused" })
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();
		}
	}
	
	/**
	 * 复制图片文件
	 * @param rootPath
	 * @param oldFilePath
	 * @return
	 * @author LiQiang
	 * @date 2017年2月10日
	 */
	public static String copyPicFolder(String rootPath,String oldFilePath,String folderName){
		SimpleDateFormat yearmonth = new SimpleDateFormat("yyyyMM");
		SimpleDateFormat dayhour = new SimpleDateFormat("ddHH");
		/** 构建图片保存的目录 **/
		String picPathDir = "/files/"+yearmonth.format(new Date())+"/"+folderName+"/" + dayhour.format(new Date());
		String uuid = UUID.randomUUID().toString().replace("-","");
		//记录文件路径
		String filePath = picPathDir + "/" + uuid + "/";
		//真实新文件路径
		String newPath = rootPath + picPathDir + "/" + uuid + "/";
		//真实旧文件路径
		String oldPath = rootPath + oldFilePath;
		copyFolder(oldPath,newPath);
		return filePath;
	};
	
	public static void main(String[] args) {
		copyFile("D:\\Tomcat7.0\\webapps\\files\\pictures\\2017\\01\\09\\11\\f3e1c608d29e4fb7bc0378a23b0e3318\\big.jpg", "D:\\Tomcat7.0\\webapps\\files\\pictures\\2017\\01\\09\\11\\copy.jpg");
	}

	/**
	 * 物理删除文件
	 * 
	 * @param sysFile
	 * @author LiQiang
	 * @date 2016年11月4日
	 */
	public static boolean delete(HttpServletRequest request, String FilePath) {
		String picRealPathDir = getRootPath(request) + FilePath;
		File file = new File(picRealPathDir);
		// 删除物理文件,删除成功，同时删除表数据记录
		if (file.delete()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取服务器root路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRootPath(HttpServletRequest request) {
		/** 得到图片保存目录的真实路径.tomcat下项目路径 **/
		String projectPath = request.getSession().getServletContext().getRealPath("");
		String rootPath = "";
		try {
			projectPath = projectPath.replace('\\', '/');
			rootPath = projectPath.substring(0, projectPath.lastIndexOf("/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rootPath;
	}

}
