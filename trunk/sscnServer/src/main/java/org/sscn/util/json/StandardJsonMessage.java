package org.sscn.util.json;

import java.util.Map;

public class StandardJsonMessage {
	public StandardJsonMessage(int result, Object data, Map<String, Object> listData, String message) {
		super();
		this.result = result;
		this.data = data;
		this.listData = listData;
		this.message = message;
	}
	private int result;
	private Object data;
	private Map<String, Object> listData;
	private String message;
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * @return the listData
	 */
	public Map<String, Object> getListData() {
		return listData;
	}
	/**
	 * @param listData the listData to set
	 */
	public void setListData(Map<String, Object> listData) {
		this.listData = listData;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
