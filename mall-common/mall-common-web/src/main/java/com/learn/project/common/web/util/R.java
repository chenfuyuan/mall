/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.learn.project.common.web.util;


import com.learn.project.common.web.constant.BaseErrorInfoInterface;
import com.learn.project.common.web.constant.ResponseCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author Mark sunlightcs@gmail.com
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	private R() {

	}

	public static R error() {
		return error(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getResultCode(), ResponseCodeEnum.INTERNAL_SERVER_ERROR.getResultMsg());
	}
	
	public static R error(String msg) {
		return error(ResponseCodeEnum.INTERNAL_SERVER_ERROR.getResultCode(), msg);
	}
	
	public static R error(String code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R error(BaseErrorInfoInterface errorInfo){
		R r = new R();
		r.put("code", errorInfo.getResultCode());
		r.put("msg", errorInfo.getResultMsg());
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("code", ResponseCodeEnum.SUCCESS.getResultCode());
		r.put("msg", msg);
		return r;
	}
	
	public static R ok(Map<String, Object> map) {
		R r = ok();
		r.putAll(map);
		return r;
	}
	
	public static R ok() {
		R r = new R();
		r.put("code", ResponseCodeEnum.SUCCESS.getResultCode());
		r.put("msg", ResponseCodeEnum.SUCCESS.getResultMsg());
		return r;
	}

	@Override
	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
