package com.project3gabs.action;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

public class DownloadFile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		javax.servlet.ServletOutputStream out = response.getOutputStream();
		String filepath = request.getRealPath("/") + "uploadfile/";
		String filename = new String(request.getParameter("filename").getBytes(
				"ISO8859-1"), "UTF-8").toString();
		//String filename = java.net.URLDecoder.decode(request.getParameter("filename"));
		System.out.println("DownloadFile filepath:" + filepath);
		System.out.println("DownloadFile filename:" + filename);
		java.io.File file = new java.io.File(filepath + filename);
		if (!file.exists()) {
			System.out.println(file.getAbsolutePath() + " �ļ�������!");
			return;
		}
		// ��ȡ�ļ���
		java.io.FileInputStream fileInputStream = new java.io.FileInputStream(
				file);
		// �����ļ�
		// ������Ӧͷ�����ر�����ļ���
		if (filename != null && filename.length() > 0) {
			response.setContentType("application/x-msdownload");
			response
					.setHeader("Content-Disposition", "attachment; filename="
							+ new String(filename.getBytes("gb2312"),
									"iso8859-1") + "");
			if (fileInputStream != null) {
				int filelen = fileInputStream.available();
				// �ļ�̫��ʱ�ڴ治��һ�ζ���,Ҫѭ��
				byte a[] = new byte[filelen];
				fileInputStream.read(a);
				out.write(a);
			}
			fileInputStream.close();
			out.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN>");
		out.println("<HTML>");
		out.println(" <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println(" <BODY>");
		out.print(" This is ");
		out.print(this.getClass().getName());
		out.println(", using the POST method");
		out.println(" </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
}
