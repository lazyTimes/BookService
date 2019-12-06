package com.itcast.zxd.Controller.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itcast.zxd.Service.UserAlreadyExisit;
import com.itcast.zxd.Service.UserRegistService;
import com.itcast.zxd.domain.User;
import com.itcast.zxd.utils.ActiveCode;
import com.itcast.zxd.utils.BeanUtil;
import com.itcast.zxd.utils.Mailutils;

/**
 * �����û�ע��Ŀ�����
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//����Service����ɲ���
	private UserRegistService registservi = new UserRegistService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * �����û��ļ�������бȶԺʹ���
	 * ����ó��˽������ȷ����
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//������֤��Ĵ�������
		String activeCode = request.getParameter("activeCode");
		
		if(activeCode != null && !activeCode.equals("")){
			String result = registservi.completeRegistActiveCode(activeCode);
			//����û�������
			if(result!=null){
				//���ո�ע����û�������
				request.setAttribute("registerNow", result);
				//��ת���û���¼����
				request.getRequestDispatcher("/WEB-INF/admin/login/login.jsp").forward(request, response);
				return;
			}else{
				request.setAttribute("errormessage", "���ǷǷ�ע���û�����");
				request.getRequestDispatcher("/WEB-INF/admin/login/register.jsp").forward(request, response);
				return;
			}
		} 
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		BeanUtil.polular(user, request.getParameterMap());
		//��װ������
		user.setActivecode(ActiveCode.createActiveCode());
		
		
		boolean bool = registservi.completeRegistOpration(user);
		//�ɹ�ҳ���·��
		
		//�ж��Ƿ�ɹ�
		if(bool){
			//�ɹ�֮����һ���ʼ�(������֤��������������ȡ��)
//			String mailmessage = "��л����ע�ᣬ�뵥��"+
//					"<a href='http://localhost:8080/BookService/activeUser?activeCode="+
//					user.getActivecode()+"'> ���� </a>��ʹ��";
//			try {
//				Mailutils.sendMail(user.getEmail(), mailmessage);
//			} catch(Exception e){
//				e.printStackTrace();
//				System.out.println("ע��ʧ��");
//				return;
//			}
			String successpath = "/WEB-INF/admin/successPage/registerSuccessPage.jsp";
			request.setAttribute("ActiveCode", user.getActivecode());
			request.getRequestDispatcher(successpath).forward(request, response);
			return;
		}else{
			request.setAttribute("errormessage", "��������æ�����Ժ�����ע��");
			request.getRequestDispatcher("/WEB-INF/admin/login/register.jsp").forward(request, response);
			return;
		}
	}

}
