package pwr.tin.tip.sw.pd.cu.core.job.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pwr.tin.tip.sw.pd.cu.jms.model.JobTaskReplay;

public class JobTaskNotFoundException extends Exception {

	private final static Logger log = LoggerFactory.getLogger(JobTaskNotFoundException.class);
	
	private static final long serialVersionUID = 5220119744019042793L;

	public JobTaskNotFoundException(JobTaskReplay jobTaskReplay) {
		super();
		log.error("Na wiadomosc zwrotna algorytmu id. {} ze scenariusza id. {} nie oczekuje zadne zadanie.");
	}
}
