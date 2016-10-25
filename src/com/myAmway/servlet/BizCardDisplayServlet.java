package com.myAmway.servlet;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.sun.image.codec.jpeg.*;//sun公司仅提供了jpg图片文件的编码api
import javax.imageio.stream.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BizCardDisplayServlet extends HttpServlet {

	private static final String GIF = "image/gif;charset=UTF8";// 设定输出的类型
	private static final String JPG = "image/jpeg;charset=UTF8";

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String imagePath = request.getParameter("bcPath");// 输出图片的类型的标志
		
		if(imagePath==null||imagePath.length()==0) return;
		
		imagePath = "E:/bizCard/" + imagePath;
		
		OutputStream output = response.getOutputStream();// 得到输出流
		if (imagePath.toLowerCase().endsWith(".jpg"))// 使用编码处理文件流的情况：
		{
			response.setContentType(JPG);// 设定输出的类型
			// 得到图片的真实路径
//			imagePath = getServletContext().getRealPath(imagePath);
			// 得到图片的文件流
			InputStream imageIn = new FileInputStream(new File(imagePath));
			// 得到输入的编码器，将文件流进行jpg格式编码
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
			// 得到编码后的图片对象
			BufferedImage image = decoder.decodeAsBufferedImage();
			// 得到输出的编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(output);
			encoder.encode(image);// 对图片进行输出编码
			imageIn.close();// 关闭文件流
		}
		if (imagePath.toLowerCase().endsWith(".gif"))// 不使用编码处理文件流的情况：
		{
			response.setContentType(GIF);
			ServletContext context = getServletContext();// 得到背景对象
			InputStream imageIn = context.getResourceAsStream(imagePath);// 文件流
			BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
			BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
			byte data[] = new byte[4096];// 缓冲字节数
			int size = 0;
			size = bis.read(data);
			while (size != -1) {
				bos.write(data, 0, size);
				size = bis.read(data);
			}
			bis.close();
			bos.flush();// 清空输出缓冲流
			bos.close();
		}
		output.close();

	}
}
