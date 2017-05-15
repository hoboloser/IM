package org.bin.socket.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public abstract class BaseController {
	protected static final int DEFAULT_PAGESIZE = 10;// 默认每页10条

	protected static final int DEFAULT_PAGENUM = 1;// 默认第一页

	protected static final String ERROR_MSG_KEY = "errMsg";

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected PrintWriter getWriter(HttpServletResponse response) {
		if (response == null)
			return null;

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (Exception e) {
			logger.error("unknow exception", e);
		}

		return writer;
	}

	public void writeAjaxResponse(String returnResult, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		if (writer == null || returnResult == null) {
			return;
		}
		try {
			writer.write(returnResult);
		} finally {
			writer.flush();
			writer.close();
		}
	}

	protected void writeAjaxJSONResponse(Object responseObj, PrintWriter writer) {
		if (writer == null || responseObj == null) {
			return;
		}
		try {
			writer.write(JSON.toJSONString(responseObj, SerializerFeature.DisableCircularReferenceDetect));
		} finally {
			writer.flush();
			writer.close();
		}
	}

	protected void writeAjaxJSONResponse(Object responseObj, HttpServletResponse response, boolean cirReferDetect) {
		PrintWriter writer = getWriter(response);
		if (writer == null || responseObj == null) {
			return;
		}
		try {
			if (!cirReferDetect) {
				writeAjaxJSONResponse(responseObj, writer);
				return;
			}
			writer.write(JSON.toJSONString(responseObj));
		} finally {
			if (writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}

	protected void writeAjaxJSONResponseWithDateFormat(Object responseObj, PrintWriter writer, String dateFormat) {
		if (writer == null || responseObj == null || dateFormat == null) {
			return;
		}

		try {
			writer.write(JSON.toJSONStringWithDateFormat(responseObj, dateFormat, SerializerFeature.DisableCircularReferenceDetect));
		} finally {
			writer.flush();
			writer.close();
		}
	}

	public void writeAjaxJSONResponse(Object responseObj, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies.

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Allow-Headers",
				"Origin,DNT,X-CustomHeader,X-Access-Token,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Accept");

		PrintWriter writer = getWriter(response);
		writeAjaxJSONResponse(responseObj, writer);
	}

	protected void writeAjaxJSONResponseWithDateFormat(Object responseObj, HttpServletResponse response, String dateFormat) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies.
		PrintWriter writer = getWriter(response);
		if (dateFormat != null)
			writeAjaxJSONResponseWithDateFormat(responseObj, writer, dateFormat);
		else
			writeAjaxJSONResponse(responseObj, writer);
	}

	protected void writeAjaxJSONResponseForWeb(Object responseObj, HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies.
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");

		PrintWriter writer = getWriter(response);
		writeAjaxJSONResponse(responseObj, writer);
	}

	protected void writeResponse(Object responseObj, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
																					// 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // Proxies.
		PrintWriter writer = getWriter(response);
		writeResponse(responseObj, writer);
	}

	protected void writeResponse(Object responseObj, PrintWriter writer) {
		if (writer == null || responseObj == null) {
			return;
		}
		try {
			writer.write(responseObj.toString());
		} finally {
			writer.flush();
			writer.close();
		}
	}

	public void writeAjaxByteStreamResponse(byte[] byteStream, HttpServletResponse response) {
		ServletOutputStream out = null;
		try {
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0
			response.setDateHeader("Expires", 0); // Proxies.

			out = response.getOutputStream();
			out.write(byteStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writePicStream(HttpServletResponse response, byte[] byteStream) {
		if (byteStream == null) {
			return;
		}
		ServletOutputStream out = null;
		try {
			Date date = new Date();
			response.setDateHeader("Last-Modified", date.getTime()); // Last-Modified:页面的最后生成时间
			response.setDateHeader("Expires", date.getTime() + 86400000); // Expires:过时期限值
			response.setHeader("Cache-Control", "public, must-revalidate"); // Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
			response.setHeader("Pragma", "Pragma");
			out = response.getOutputStream();
			out.write(byteStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writeBufferedImage(HttpServletResponse response, BufferedImage bufferedImage) {
		Date date = new Date();
		response.setDateHeader("Last-Modified", date.getTime()); // Last-Modified:页面的最后生成时间
		response.setDateHeader("Expires", date.getTime() + 86400000); // Expires:过时期限值
		response.setHeader("Cache-Control", "public, must-revalidate"); // Cache-Control来控制页面的缓存与否,public:浏览器和缓存服务器都可以缓存页面信息；
		response.setHeader("Pragma", "Pragma");

		try {
			OutputStream out = response.getOutputStream();
			ImageIO.write(bufferedImage, "jpeg", out);
		} catch (IOException e) {
			logger.error("writeBufferedImage:", e);
			e.printStackTrace();
		}
	}
}