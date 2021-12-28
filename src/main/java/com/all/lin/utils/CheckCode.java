package com.all.lin.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckCode {
	// 校验码图片宽    
	private static int width = 100;
	// 校验码图片高    
	private static int height = 30;
	// 校验码长度    
	public static int codeLength = 4;
	//干扰点数量
	private static int lineNum = 130;
	// 混合字母数字数组    
	private static String[] charArray = new String[]{
			"2", "3", "4", "5", "6", "7", "8", "9", "1", "0"
	};
	//产生波形滤镜效果,此值越大，扭曲程度越大 
	//private final static double PI =3.1415926535897932384626433832799;

	@SuppressWarnings("static-access")
	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}

	@SuppressWarnings("static-access")
	public void setWidth(int width) {
		this.width = width;
	}

	@SuppressWarnings("static-access")
	public void setHeight(int height) {
		this.height = height;
	}

	/*
	 * 取得验证码图片
	 */
	public static BufferedImage getImage(HttpServletRequest request) {
		// 在内存中创建图象    
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景色    
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		// 生成随机码,每次一个,直接涂    
		String code = "";
		for (int i = 0; i < codeLength; i++) {
			String rand = getRandomChar();
			//拼接验证码
			code = code + rand;
			// 设定字体 字体样式,字体状态(普通,正常,加粗)   
			g.setFont(getRandomFont());
			//设定颜色
			g.setColor(getRandomColor());
			// rand为要画的字符串,x轴,y轴   
			Random ran = new Random();
			int x = ran.nextInt(7);
			g.drawString(rand, 35 * i + 15, i * x + 60);
		}
		//将拼接的验证码写入session
		HttpSession session = request.getSession(true);
		session.setAttribute("checkCode", code);
		// 随机产生干扰点
		//设定颜色
		g.setColor(getRandomColor());
		Random rand = new Random();
		for (int i = 0; i < lineNum; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		//随机产生干扰线
		//设定颜色
		g.setColor(getRandomColor());
		for (int i = 0; i < 20; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			int xl = rand.nextInt(width);
			int yl = rand.nextInt(height);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 图象生效
		g.dispose();
		//调用正弦曲线处理图片
		//image = TwistImage(image, true, 11, 3);  
		return image;
	}

	/**
	 * 随机生成字符
	 *
	 * @return
	 */
	public static String getRandomChar() {
		Random random = new Random();
		String randChar = charArray[random.nextInt(charArray.length)];
		return randChar;
	}

	/**
	 * 获得随机颜色
	 *
	 * @return 随即颜色
	 */

	public static Color getRandomColor() {
		Random random = new Random();
		int R = random.nextInt(255), G = random.nextInt(255), B = random.nextInt(255);
		return new Color(R, G, B);
	}

	/**
	 * 随机生成字体、文字大小
	 *
	 * @return
	 * @createDate 2010-8-23 上午10:44:22
	 * @author hoojo
	 */
	public static Font getRandomFont() {
		String[] fonts = {"Georgia", "Verdana", "Arial", "Tahoma", "Time News Roman", "Courier New", "Arial Black", "Quantzite"};
		int fontIndex = (int) Math.round(Math.random() * (fonts.length - 1));
		int fontSize = (int) Math.round(Math.random() * 3 + 40);
		return new Font(fonts[fontIndex], Font.LAYOUT_NO_START_CONTEXT, fontSize);
	}
}