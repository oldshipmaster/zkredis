<?xml version="1.0" encoding="UTF-8"?>
<%@ page import="net.sf.ehcache.CacheManager"%>
<%@ page import="net.sf.ehcache.Cache" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ehcache</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
body {
	color:#272727;
	font-size: 18px;
	margin: 5;
	padding: 0;
	font-family: "微软雅黑",Helvetica;
	background:#B8D8B8;
}
</style>
<body>
<%
		String context = request.getContextPath();
		if("/".equals(context)) {
			context = "";
		}
		if (null != request.getAttribute("ischeck")) {
			CacheManager cm = (CacheManager) request.getAttribute("cm");
			int cacheSize = cm.getCacheNames().length;
			out.println("CacheName:<br/>");
			for (int i = 0; i < cacheSize; i++) {
				out.println("[<a href=\"list.html?cache="
				+ cm.getCacheNames()[i] + "\">"
				+ cm.getCacheNames()[i] + "</a>] ");
			}
		    out.print("<a href='list.html" + "'>Reload</a>");
		    out.print("<br/>");
			if(null!=request.getParameter("action")){
				request.getSession().removeAttribute("ischeck");
				response.sendRedirect("ehcache/list.html");
			}
		if (null != request.getParameter("cache")) {
			String cachename = request.getParameter("cache");
			Cache cache = cm.getCache(cachename);
			if(null !=cache){
				out.println("<br/>[" + cachename + "] size: "
				+ cache.getSize()
				+ "     <a href=\"list.html?cache=" + cachename
				+ "&amp;rmcache=" + cachename
				+ "\">Delete</a>]<br/>");
				if (null != request.getParameter("rmcache")) {
					cache.removeAll();
					response.sendRedirect(context + "/view/ehcache/list.html?cache=" + cachename);
				}
				for (int i = 0; i < cache.getKeys().size(); i++) {
					if (null != request.getParameter("key")) {
						cache.remove(request.getParameter("key"));
						response.sendRedirect(context + "/view/ehcache/list.html?cache="
						+ cachename);
					}
					out.print(i + 1 + ":"
						+ cache.getKeys().get(i).toString()
						+ "      [<a href=\"list.html?cache="
						+ cachename + "&amp;key="
						+ cache.getKeys().get(i).toString()
						+"\">Delete</a>]<br/>");
				}
			}
		}
	}
%>
</body>
</html>
