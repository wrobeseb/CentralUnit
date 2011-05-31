package pwr.tin.tip.sw.pd.cu.core.job;

import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.core.job.repo.BlockedJobsRepository;
import pwr.tin.tip.sw.pd.cu.db.service.IUnitService;
import pwr.tin.tip.sw.pd.cu.jms.core.manager.JMSConnectionManager;
import pwr.tin.tip.sw.pd.cu.jms.model.Job;

@Component(value="jobPoolManager")
public class JobPoolManager {
	
	private final static Logger log = LoggerFactory.getLogger(JobPoolManager.class);
	
	@Autowired(required=true)
	private JMSConnectionManager jmsConnectionManager;
	
	@Autowired(required=true)
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired(required=true)
	private BlockedJobsRepository blockedJobsRepository;
	
	@Autowired(required=true)
	private JobProcessor jobProcessor;

	/**
	 * Metoda wykorzystywana w procesie cyklicznego sprawdzania stanu kolejki blokuj±cej
	 * 
	 * Kiedy kolejka oczekuj±cych na uruchomienie siê przepe³ni, pobieranie komunikatów z ESB 
	 * jest blokowane a komunikat który zosta³ zablokowany zostaje umieszczony w repozytorium 
	 * komunikatów zablokowanych, gdzie bêdzie oczekiwaæ na zwolnienie siê miejsca. Scheduler 
	 * poprzez  uruchomienie  cyklicznie co 1 sekunde sprawdza czy w repozytorium komunikatów 
	 * zablokowanych  s±  jakie¶  wiadomo¶ci  a  kiedy  takie  istniej±  sprawdzi czy kolejka 
	 * oczekuj±cych  zosta³a ju¿ opró¿niona, w przypadku zwolnienia miejsca komunikat zostaje 
	 * zdjêty  z  repozytorium 	komuniktów 	zablokowanych  a  nastêpnie  przekazywany jest do 
	 * uruchomienia.
	 * 
	 */
	public void checkIfQueueIsFull() {
		if (blockedJobsRepository.capacity() != 0) {
			ThreadPoolExecutor executor = taskExecutor.getThreadPoolExecutor();
			if (executor.getQueue().size() == 0) {
				log.info("Miejsce w kolejce oczekuj¹cych zosta³o zwolnione... rozpoczêcie uruchamiania zadañ zablokowanych.");
				Job job;
				while ((job = blockedJobsRepository.pull()) != null) {
					if (executor.getQueue().size() == 0) {
						jobProcessor.launch(job);
					}
					else {
						blockedJobsRepository.put(job);
					}
				}
				if (blockedJobsRepository.capacity() == 0) {
					jmsConnectionManager.startConsumingMessagesFromWorkflow();
				}
			}
		}
	}
}
