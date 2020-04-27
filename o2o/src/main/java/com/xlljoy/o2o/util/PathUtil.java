package com.xlljoy.o2o.util;

public class PathUtil {
	private static String separator = System.getProperty("file.separator");
	public static String getImgBasePath() {
		String oString = System.getProperty("os.name");
		String basePath = "";
		if (oString.toLowerCase().startsWith("win")) {
			basePath = "D:/project/images";
		} else {
			basePath = "/home/jli/Pictures";
		}
		basePath = basePath.replace("/", separator);
		return basePath;
	}
	
	public static String getShopImgPath(long shopId) {
		String imgPath =  "/upload/item/shop/" + shopId + "/";
		return imgPath.replace("/", separator);
	}
	
	public static String getProductImgPath(long productId, long shopId) {
		String imgPath =  "/upload/item/shop/" + shopId + "/product/"+ productId + "/";
		return imgPath.replace("/", separator);
	}
	
	public static String getDailyNewImgPath() {
		String imgPath =  "/upload/item/dailyNew/";
		return imgPath.replace("/", separator);
	}
	
	public static String getUserImgPath(long userId) {
		String imgPath =  "/upload/item/user/" + userId + "/";
		return imgPath.replace("/", separator);
	}
}
