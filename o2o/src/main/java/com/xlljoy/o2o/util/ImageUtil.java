package com.xlljoy.o2o.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xlljoy.o2o.entity.DailyNew;
import com.xlljoy.o2o.entity.Shop;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static File transferTo(CommonsMultipartFile commonFile) throws IllegalStateException, IOException {
		File file = new File(commonFile.getOriginalFilename());
		commonFile.transferTo(file);
		return file;
	}
	
	// generate img with water mark and return relative path
	public static String generateThumbnail(InputStream imgInputStream, String fileName, String targetAddr) {
		String realFileName = getRandomFileName();
		String fileExtension = getFileExtention(fileName);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName +fileExtension;
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		
		try {
			Thumbnails.of(imgInputStream).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return relativeAddr;
	}
	
	public static String getRandomFileName() {
		int n = new Random().nextInt(8) + 10000;
		return simpleDateFormat.format(new Date()) + n;
	}
	
	public static String getFileExtention (String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	public static void makeDirPath(String targetAddr) {
		String fullPath = PathUtil.getImgBasePath() + targetAddr;
		 File file = new File(fullPath);
		 if (!file.exists()) {
			 file.mkdirs();
		 }
		 
	}
	
	public static String addImgForProduct(ImageUnit img, long shopId, long productId) {
		String targetAddrString = PathUtil.getProductImgPath(productId, shopId);
		String imgPath = ImageUtil.generateThumbnail(img.getImage(), img.getName(), targetAddrString);
		return imgPath;
	}
	
	// another way to convert CommonMultipartFile to File
	public static void inputStreamToFile(CommonsMultipartFile commonsMultipartFile, File file) {
		InputStream ins =  null;
		FileOutputStream os = null;
		try {
			ins = commonsMultipartFile.getInputStream();
		} catch (Exception e) {
			throw new RuntimeException("failed to transfer CommonsMultipartFile to InputStream: " + e.getMessage());
		}
		try {
			os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while((bytesRead = ins.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (Exception e) {
			throw new RuntimeException("FileOutputStream has exception: " + e.getMessage());
		} finally {
			try {
				if (os != null) {
					os.close();
				}
				if (ins != null) {
					ins.close();
				}
			} catch (Exception e) {
				throw new RuntimeException("inputStream closing has exception: " + e.getMessage());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("/home/jli/Pictures/pic1.jpg")).size(200, 200)
			.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
			.outputQuality(0.8f).toFile("/home/jli/Pictures/pic2.jpg");
	}
	
	//check the storePath is the directory or file,
	// if it is file directory, delete this file
	// else if it is repository, delete the whole directory
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if (fileOrPath.exists()) {
			if (fileOrPath.isDirectory()) {
				File[] files = fileOrPath.listFiles();
				for (int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
	
	public static void addImgForDailyNew(ImageUnit img, DailyNew dailyNew) {
		String targetAddrString = PathUtil.getDailyNewImgPath();
		String imgPath = ImageUtil.generateThumbnail(img.getImage(), img.getName(), targetAddrString);
		dailyNew.setImg(imgPath);
	}
}
