package com.wistron.wcd.servlet.study;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo1
 */
@WebServlet("/ServletDemo1")
public class ServletDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		 /**
         * 1.获得客户机信息
         */
        String requestUrl = request.getRequestURL().toString();//得到请求的URL地址
        String requestUri = request.getRequestURI();//得到请求的资源
        String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
        String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
        String remoteHost1 = request.getRemoteHost();
        int remotePort = request.getRemotePort();
        String remoteUser = request.getRemoteUser();
        String method = request.getMethod();//得到请求URL地址时使用的方法
        String pathInfo = request.getPathInfo();
        String localAddr = request.getLocalAddr();//获取WEB服务器的IP地址
        String localName = request.getLocalName();//获取WEB服务器的主机名
        String aD= request.getHeader("AUTH_USER");
        response.setCharacterEncoding("UTF-8");//设置将字符以"UTF-8"编码输出到客户端浏览器
        //通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        response.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write("获取到的客户机信息如下：");
        out.write("<hr/>");
        out.write("请求的URL地址："+requestUrl);
        out.write("<br/>");
        out.write("请求的资源："+requestUri);
        out.write("<br/>");
        out.write("请求的URL地址中附带的参数："+queryString);
        out.write("<br/>");
        out.write("来访者的IP地址："+remoteAddr);
        out.write("<br/>");
        out.write("来访者的主机名："+remoteHost1);
        out.write("<br/>");
        out.write("使用的端口号："+remotePort);
        out.write("<br/>");
        out.write("remoteUser："+remoteUser);
        out.write("<br/>");
        out.write("请求使用的方法："+method);
        out.write("<br/>");
        out.write("pathInfo："+pathInfo);
        out.write("<br/>");
        out.write("localAddr："+localAddr);
        out.write("<br/>");
        out.write("localName："+localName);
        out.write("<br/>");
        out.write("AD賬號："+aD);
        out.write("<br/>");
//        System.out.println(System.getProperty("-----------------------------") );
//        System.out.println(System.getProperty("user.name") );
//        System.out.println(System.getProperty("java.version") );
//        System.out.println(System.getProperty("java.vendor") );
//        System.out.println(System.getProperty("java.vendor.url") );
//        System.out.println(System.getProperty("java.home") );
//        System.out.println(System.getProperty("java.vm.specification.version") );
//        System.out.println(System.getProperty("java.vm.specification.vendor") );
//        System.out.println(System.getProperty("java.vm.specification.name") );
//        System.out.println(System.getProperty("java.vm.version ") );
//        System.out.println(System.getProperty("java.vm.vendor") );
//        System.out.println(System.getProperty("java.vm.name") );
//        System.out.println(System.getProperty("java.specification.version") );
//        System.out.println(System.getProperty("java.specification.vendor") );
//        System.out.println(System.getProperty("java.specification.name") );
//        System.out.println(System.getProperty("java.class.version") );
//        System.out.println(System.getProperty("java.class.path") );
//        System.out.println(System.getProperty("java.library.path") );
//        System.out.println(System.getProperty("java.io.tmpdir") );
//        System.out.println(System.getProperty("java.compiler") );
//        System.out.println(System.getProperty("java.ext.dirs") );
//        System.out.println(System.getProperty("os.name") );
//        System.out.println(System.getProperty("os.arch") );
//        System.out.println(System.getProperty("os.version") );
//        System.out.println(System.getProperty("file.separator") );
//        System.out.println(System.getProperty("path.separator") );
//        System.out.println(System.getProperty("line.separator") );
//        System.out.println(System.getProperty("user.name") );
//        System.out.println(System.getProperty("user.home") );
//        System.out.println(System.getProperty("user.dir") );
        //System.out.println(System.getProperty("user.name") );
        
//如下為獲取Domain，賬號，財編
String auth = request.getHeader("Authorization");
if (auth == null)
{
  response.setStatus(response.SC_UNAUTHORIZED);
  response.setHeader("WWW-Authenticate", "NTLM");
  response.flushBuffer();
  return;
}
if (auth.startsWith("NTLM "))
{
  byte[] msg = new sun.misc.BASE64Decoder().decodeBuffer(auth.substring(5));
  int off = 0, length, offset;
  if (msg[8] == 1)
  {
    byte z = 0;
    byte[] msg1 = {(byte)'N', (byte)'T', (byte)'L', (byte)'M', (byte)'S', (byte)'S', (byte)'P', 
      z,(byte)2, z, z, z, z, z, z, z,(byte)40, z, z, z, 
      (byte)1, (byte)130, z, z,z, (byte)2, (byte)2,
      (byte)2, z, z, z, z, z, z, z, z, z, z, z, z};
    response.setHeader("WWW-Authenticate", "NTLM " + 
       new sun.misc.BASE64Encoder().encodeBuffer(msg1));
    response.sendError(response.SC_UNAUTHORIZED);
    return;
  }
  else if (msg[8] == 3)
  {
    off = 30;
    length = msg[off+17]*256 + msg[off+16];
    offset = msg[off+19]*256 + msg[off+18];
    String remoteHost = new String(msg, offset, length);

    length = msg[off+1]*256 + msg[off];
    offset = msg[off+3]*256 + msg[off+2];
    String domain = new String(msg, offset, length);

    length = msg[off+9]*256 + msg[off+8];
    offset = msg[off+11]*256 + msg[off+10];
    String username = new String(msg, offset, length);

    out.println("Username:"+username+"<BR>");
    out.println("RemoteHost:"+remoteHost+"<BR>");
    out.println("Domain:"+domain+"<BR>");
  }
}

        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
	        out.println("<HTML>");
	        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
	        out.println("  <BODY>");
	        out.print("    This is ");
	        out.print(this.getClass());
	        out.println(", using the POST method");
	        out.println("  </BODY>");
	        out.println("</HTML>");
	        out.flush();
	        out.close();
	}

}
