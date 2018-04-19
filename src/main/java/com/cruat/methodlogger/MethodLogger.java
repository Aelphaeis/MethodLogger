package com.cruat.methodlogger;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.StackLocatorUtil;

public class MethodLogger extends Logger {

	private static String getCallingMethodName(int level) {
		return Thread.currentThread().getStackTrace()[level].getMethodName();
	}

	private static String resolveName() {
		StringBuilder builder = new StringBuilder();
		Class<?> cls = StackLocatorUtil.getCallerClass(3);
		builder.append(cls.getCanonicalName());
		builder.append('.');
		builder.append(getCallingMethodName(4));
		return builder.toString();
	}

	private static MessageFactory initMsgFactory() {
		try {
			return DEFAULT_MESSAGE_FACTORY_CLASS.newInstance();
		} catch (final InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	public MethodLogger() {
		this(LoggerContext.getContext(), resolveName(), initMsgFactory());
	}

	protected MethodLogger(LoggerContext context, String name,
			MessageFactory messageFactory) {
		super(context, name, messageFactory);
	}
}
