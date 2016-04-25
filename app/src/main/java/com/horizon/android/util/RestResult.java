package com.horizon.android.util;

import java.io.Serializable;

public class RestResult<T> implements Serializable {
	
	private static final long serialVersionUID = 3545938112462081930L;

	public static enum ResultFlag {
		
		SUCCEED(1),
		FAILED(0);
		
		private final int value;
		
		private ResultFlag(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
		
		public static ResultFlag valueOf(int value){
			for(ResultFlag inst : values()){
				if(value==inst.value)
					return inst;
			}
			throw new IllegalArgumentException("不支持的常量" + value);
		}
		
	}
	
	/**
	 * 返回�?
	 */
	private int ret_flag;
	/**
	 * 错误代码
	 */
	private String error_code;
	/**
	 * 返回信息
	 */
	private String ret_msg;

	private T data;

	public RestResult() {
		this.markSucceed();
	}

	public boolean isSucceed() {
		return ResultFlag.SUCCEED.getValue() == ret_flag;
	}

	public boolean isFailed() {
		return ResultFlag.FAILED.getValue() == ret_flag;
	}

	public final void markSucceed() {
		this.ret_flag = ResultFlag.SUCCEED.getValue();
	}

	public final void markAsfailed() {
		this.ret_flag = ResultFlag.FAILED.getValue();
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getRet_msg() {
		return ret_msg;
	}

	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}

	public Integer getRet_flag() {
		return ret_flag;
	}

	public void setRet_flag(Integer ret_flag) {
		this.ret_flag = ret_flag;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RestResult [ret_flag=" + ret_flag + ", error_code="
				+ error_code + ", ret_msg=" + ret_msg + ", data=" + data + "]";
	}

}
