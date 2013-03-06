package com.lurencun.service.autoupdate;


/**
 * 将服务端响应解析数据为Version对象
 * 
 * @author ilovedeals
 * 
 */
public interface ResponseParser {
	Version parser(String response);
}