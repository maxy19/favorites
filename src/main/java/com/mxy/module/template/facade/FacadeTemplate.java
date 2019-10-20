package com.mxy.module.template.facade;


/**
 * @author maxy26
 */
public class FacadeTemplate {

	public static<T,U> Response<T> execute(ExecuteLogger<U> executeLogger,
										   BizCallback<T> bizCallback) {
		long start = System.currentTimeMillis();
		executeLogger.startLog();
		Response<T> response = Response.getSuccessResponse();
		try {
			response = bizCallback.execute();
		} catch (IllegalArgumentException e) {
			response.setReturnCode(ExceptionEnum.PARAM_ERROR.name());
			response.setReturnMessage(e.getMessage());
			executeLogger.printMsgLog(e);
		} catch (BusinessException e) {
			response.setReturnCode(e.getErrorCode());
			response.setReturnMessage(e.getMessage());
			executeLogger.printLog(e);
		} catch (Exception e) {
			response.setReturnCode(ExceptionEnum.SYS_ERROR.name());
			response.setReturnMessage(e.getMessage());
			executeLogger.printLog(e);
		}
		executeLogger.endLog(response, System.currentTimeMillis() - start);
		return response;
	}
	

	public static<U,T> Response<T> execute(ExecuteLogger<U> executeLogger,
										   ParamValidator validator, BizCallback<T> bizCallback) {
		long start = System.currentTimeMillis();
		executeLogger.startLog();
		Response<T> response = Response.getSuccessResponse();
		try {
			if(validator != null) {
			   validator.validate();
			}
			response = bizCallback.execute();
		} catch (IllegalArgumentException e) {
			response.setReturnCode(ExceptionEnum.PARAM_ERROR.name());
			response.setReturnMessage(e.getMessage());
			executeLogger.printMsgLog(e);
		}catch (BusinessException e) {
			response.setReturnCode(e.getErrorCode());
			response.setReturnMessage(e.getMessage());
			executeLogger.printLog(e);
		}catch (Exception e) {
			response.setReturnCode(ExceptionEnum.SYS_ERROR.name());
			response.setReturnMessage(e.getMessage());
			executeLogger.printLog(e);
		}
		executeLogger.endLog(response, System.currentTimeMillis() - start);
		return response;
	}
}
