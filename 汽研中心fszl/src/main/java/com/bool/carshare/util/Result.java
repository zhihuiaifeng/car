package com.bool.carshare.util;

public final class Result {
	// S0000
	private String messageCode;
	// List<UserInfo>
	private Object result;
	
	// 执行是否成功
	private boolean executeResult;
	
	

	public Result() {
		super();
	}

	

	public Result(String messageCode, Object result, boolean executeResult) {
		super();
		this.messageCode = messageCode;
		this.result = result;
		this.executeResult = executeResult;
	}



	public String getMessageCode() {
		return messageCode;
	}



	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}



	public boolean isExecuteResult() {
		return executeResult;
	}

	public void setExecuteResult(boolean executeResult) {
		this.executeResult = executeResult;
	}


	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
	public static class ResultBuilder{
		
		
		public static Result buildSuccessResult(String messageCode,Object result){
			return new Result(messageCode, result, true);
		}
		
		public static Result buildFailerResult(String messageCode,Object result){
			return new Result(messageCode,result,false);
		}
		
	}


	@Override
	public String toString() {
		return "Result [messageCode=" + messageCode + ", result=" + result + ", executeResult=" + executeResult + "]";
	}
	

}
