package pwr.tin.tip.sw.pd.cu.core;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Messages {

	private MessageSource messageSource;
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	public String getMessage(String key, Object[] args) {
		return messageSource.getMessage(key, args, Locale.getDefault());
	}
}
