package com.all.lin.utils;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;

/**
 * Created by parkYD on 2016/7/15.
 */
public class ExtractZIP {

	/**
	 * 解压静态方法
	 *
	 * @param zipFileName
	 * @param outputDirectory
	 * @throws Exception
	 */
	public static void extract(String zipFileName, String outputDirectory) throws Exception {
		try {
			ZipFile zipFile = new ZipFile(zipFileName, "GBK");
			Enumeration e = zipFile.getEntries();

			ZipEntry zipEntry = null;

			while (e.hasMoreElements()) {
				zipEntry = (ZipEntry) e.nextElement();
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					mkDirs(outputDirectory + File.separator + name);

				} else {
					String name = zipEntry.getName();
					String dir = null;
					if (name.indexOf("/") < 0) {
						dir = "/";
					} else {
						dir = name.substring(0, name.lastIndexOf("/"));
					}
					mkDirs(outputDirectory + File.separator + dir);
					File f = new File(outputDirectory + File.separator + zipEntry.getName());
					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);
					int c;
					byte[] by = new byte[1024];
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 创建目录，包括子目录
	 *
	 * @param dir
	 * @throws Exception
	 */
	private static void mkDirs(String dir) throws Exception {
		if (dir == null || dir.equals("")) {
			return;
		}
		File f1 = new File(dir);
		if (!f1.exists()) {
			f1.mkdirs();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			extract("c:\\b.zip", "c:\\开源项目\\a");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}