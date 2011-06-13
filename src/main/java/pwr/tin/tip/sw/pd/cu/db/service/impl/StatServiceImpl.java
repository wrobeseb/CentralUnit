package pwr.tin.tip.sw.pd.cu.db.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pwr.tin.tip.sw.pd.cu.db.dao.IStatDao;
import pwr.tin.tip.sw.pd.cu.db.model.DBStatistics;
import pwr.tin.tip.sw.pd.cu.db.service.IStatService;
import pwr.tin.tip.sw.pd.cu.db.utils.DateTime;

@Component("statService")
public class StatServiceImpl implements IStatService {

	@Autowired(required=false)
	private IStatDao statDao;
	
	@Value("${central.unit.id}")
	private Integer unitId;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void startStat(Integer jobId, Integer sessionId) {
		DBStatistics stat = new DBStatistics();
		stat.setJobId(jobId);
		stat.setSessionId(sessionId);
		stat.setUnitId(unitId);
		stat.setProcessStartMili(DateTime.nowInMillis());
		statDao.setStartStat(stat);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void endStat(Integer jobId, Integer sessionId) {
		Long end = DateTime.nowInMillis();
		DBStatistics stat = statDao.getStat(jobId, sessionId);
		stat.setProcessEndMili(end);
		stat.setInterval(stat.getProcessEndMili() - stat.getProcessStartMili());
		statDao.setEndStat(stat);
	}

}
