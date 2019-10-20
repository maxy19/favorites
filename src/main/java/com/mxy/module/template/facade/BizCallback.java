package com.mxy.module.template.facade;

/**
 * @author maxy26
 */
public interface BizCallback<T> {

	Response<T> execute();
}
