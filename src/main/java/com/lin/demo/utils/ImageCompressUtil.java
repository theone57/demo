package com.lin.demo.utils;

import com.sun.imageio.plugins.jpeg.JPEGImageWriter;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wangjun on 2016/10/14.
 * EDIT BY wangjun 修复连接
 * 重要工具类 请不要删除！！！！！！！
 */
public class ImageCompressUtil {

	public static String downloadImageFromUrl(String url, String filePath, String fileName, String prefix) {
		String finalName = "";
		InputStream is = null;
		OutputStream os = null;
		HttpURLConnection httpUrlConnection = null;
		try {
			URL imageUrl = new URL(url);
			URLConnection con = imageUrl.openConnection();
			httpUrlConnection = (HttpURLConnection) con;
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			con.setConnectTimeout(10 * 1000);
			is = httpUrlConnection.getInputStream();
			byte[] bs = new byte[1024];
			int len;
			File sf = new File(filePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			if (!StringUtils.hasText(fileName)) {
				fileName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
			}
			if (prefix == null) {
				prefix = url.substring(url.lastIndexOf(".") + 1, url.length());
			}
			StringBuilder sb = new StringBuilder();
			finalName = sb.append(filePath).append(fileName).append("_normal").append(".").append(prefix).toString();
			os = new FileOutputStream(finalName);
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (httpUrlConnection != null) {
			httpUrlConnection.disconnect();
		}
		return finalName;
	}

	public static String imgCompressFromUrl(String url, String filePath, String fileName, String prefix, float JPEGcompression, int dpi) {
		String finalName = "";
		FileOutputStream fos = null;
		try {
			URL imageUrl = new URL(url);
			BufferedImage bufferedImage = ImageIO.read(imageUrl);
			int new_w = bufferedImage.getWidth();
			int new_h = bufferedImage.getHeight();
			if (new_w > 750) {
				BigDecimal scale = new BigDecimal(new_w).divide(new BigDecimal(750), 2, BigDecimal.ROUND_DOWN);
				BigDecimal new_h_after = new BigDecimal(new_h).divide(scale, 0, BigDecimal.ROUND_DOWN);
				new_w = 750;
				new_h = new_h_after.intValue();
			}
			BufferedImage image_to_save = new BufferedImage(new_w, new_h, bufferedImage.getType());
			Graphics2D g = image_to_save.createGraphics();
			g.drawImage(bufferedImage.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
			if (!filePath.endsWith(File.separator)) {
				filePath = filePath + File.separator;
			}
			if (!StringUtils.hasText(fileName)) {
				fileName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
			}
			if (prefix == null) {
				prefix = url.substring(url.lastIndexOf(".") + 1, url.length());
			}
			StringBuilder sb = new StringBuilder();
			finalName = sb.append(filePath).append(fileName).append("_small").append(".").append(prefix).toString();

			fos = new FileOutputStream(finalName);
			saveAsJPEG(dpi, image_to_save, JPEGcompression, fos);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return finalName;
	}

	public static void saveAsJPEG(Integer dpi, BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos) throws IOException {
		JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
		imageWriter.setOutput(ios);
		IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);
		if (dpi != null && !dpi.equals("")) {
			Element tree = (Element) imageMetaData.getAsTree("javax_imageio_jpeg_image_1.0");
			Element jfif = (Element) tree.getElementsByTagName("app0JFIF").item(0);
			jfif.setAttribute("Xdensity", Integer.toString(dpi));
			jfif.setAttribute("Ydensity", Integer.toString(dpi));

		}
		JPEGImageWriteParam jpegParams = null;
		if (JPEGcompression >= 0 && JPEGcompression <= 1f) {
			jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
			jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
			jpegParams.setCompressionQuality(JPEGcompression);
		}
		imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), jpegParams);
		ios.flush();
		ios.close();
		imageWriter.dispose();
	}
}