package com.itcast.zxd.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
/**
 * 处理用户验证码的控制器
 * 可以对于注册进行验证码的验证
 * 使用awt生成一张图片，同时使用一个文件的成语文件作为内容
 */
@WebServlet("/imageCode")
public class imageCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private List<String> words = new ArrayList<String>();
	private static int width = 180;
	private static int height = 40;
	private static Random rand = new Random();
	
    public imageCode() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	//使用转换流进行转换
    	BufferedReader bufread = null;
    	if(words.size()==0){
	    	
	    	String path = getServletContext().getRealPath("/WEB-INF/newwords.txt");
	    	System.out.println("资源目录"+path);
	    	try {
				bufread = new BufferedReader(new FileReader(new File(path)));
				
				String str;
				while((str=bufread.readLine())!=null){
					words.add(str);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if(null != bufread)
					try {
						bufread.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
    	}
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置进制缓存
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		//使用RGB颜色编码,在内存当中缓存一张内存图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//绘制背景颜色
		Graphics graphic = image.getGraphics();
		//设置画笔的颜色
		graphic.setColor(getRandColor(200, 250));
		//绘制边框
		graphic.fillRect(0, 0, width, height);
		graphic.drawRect(0, 0, width-1, height-1);
		//产生四个随机字符
		Graphics2D graphics2d = (Graphics2D) graphic;
		graphics2d.setFont(new Font("宋体", Font.BOLD, 18));
		int index = rand.nextInt(words.size());
		//获得集合内部对应的成语
		String word = words.get(index);
		//定义x坐标
		int x = 10;
		
		for(int i = 0; i<word.length(); i++){
			//随机颜色
			graphics2d.setColor(new Color(20 + rand.nextInt(110),
					20 + rand.nextInt(110), 20 + rand.nextInt(110)));
			//旋转度数
			int angle = 30 - rand.nextInt(15);
			//换算弧度
			double theta = angle * Math.PI / 180;
			//获取字母数字
			char c = word.charAt(i);
			//将字母输出到图片
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 40;
		}
		
		//将内容放入session域当中
		request.getSession().setAttribute("checkout_imagecode", word);
		//绘制干扰线
		graphic.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for(int i=0; i<30; i++){
			x1 = rand.nextInt(width);
			x2 = rand.nextInt(12);
			y1 = rand.nextInt(height);
			y2 = rand.nextInt(12);
			graphic.drawLine(x1, y1, x1+x2, y1+y2);
		}
		// 将上面图片输出到浏览器 ImageIO
		//释放资源
		graphic.dispose();
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
	
	/**
	 * 取其某一范围的color
	 * @param fc   int 范围参数1
	 * @param bc   int 范围参数2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc){
		if(fc > 255)
			fc = 255;
		if(bc > 255)
			bc = 255;
		int r = fc + rand.nextInt(bc - fc);
		int g = fc + rand.nextInt(bc - fc);
		int b = fc + rand.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
