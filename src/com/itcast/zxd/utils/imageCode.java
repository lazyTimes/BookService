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
 * �����û���֤��Ŀ�����
 * ���Զ���ע�������֤�����֤
 * ʹ��awt����һ��ͼƬ��ͬʱʹ��һ���ļ��ĳ����ļ���Ϊ����
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
    	//ʹ��ת��������ת��
    	BufferedReader bufread = null;
    	if(words.size()==0){
	    	
	    	String path = getServletContext().getRealPath("/WEB-INF/newwords.txt");
	    	System.out.println("��ԴĿ¼"+path);
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
		//���ý��ƻ���
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		//ʹ��RGB��ɫ����,���ڴ浱�л���һ���ڴ�ͼƬ
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//���Ʊ�����ɫ
		Graphics graphic = image.getGraphics();
		//���û��ʵ���ɫ
		graphic.setColor(getRandColor(200, 250));
		//���Ʊ߿�
		graphic.fillRect(0, 0, width, height);
		graphic.drawRect(0, 0, width-1, height-1);
		//�����ĸ�����ַ�
		Graphics2D graphics2d = (Graphics2D) graphic;
		graphics2d.setFont(new Font("����", Font.BOLD, 18));
		int index = rand.nextInt(words.size());
		//��ü����ڲ���Ӧ�ĳ���
		String word = words.get(index);
		//����x����
		int x = 10;
		
		for(int i = 0; i<word.length(); i++){
			//�����ɫ
			graphics2d.setColor(new Color(20 + rand.nextInt(110),
					20 + rand.nextInt(110), 20 + rand.nextInt(110)));
			//��ת����
			int angle = 30 - rand.nextInt(15);
			//���㻡��
			double theta = angle * Math.PI / 180;
			//��ȡ��ĸ����
			char c = word.charAt(i);
			//����ĸ�����ͼƬ
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 40;
		}
		
		//�����ݷ���session����
		request.getSession().setAttribute("checkout_imagecode", word);
		//���Ƹ�����
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
		// ������ͼƬ���������� ImageIO
		//�ͷ���Դ
		graphic.dispose();
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
	
	/**
	 * ȡ��ĳһ��Χ��color
	 * @param fc   int ��Χ����1
	 * @param bc   int ��Χ����2
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
