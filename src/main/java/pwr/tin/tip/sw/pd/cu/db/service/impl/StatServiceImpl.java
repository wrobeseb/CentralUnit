package pwr.tin.tip.sw.pd.cu.db.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pwr.tin.tip.sw.pd.cu.db.dao.IStatDao;
import pwr.tin.tip.sw.pd.cu.db.model.DBStatistics;
import pwr.tin.tip.sw.pd.cu.db.service.IStatService;

@Component("statService")
public class StatServiceImpl implements IStatService {

	@Autowired(required=false)
	private IStatDao statDao;
	
	@Value("${central.unit.id}")
	private Integer unitId;
	
	@Override
	public void startStat(Integer jobId, Integer sessionId) {
		DBStatistics stat = new DBStatistics();
		stat.setJobId(jobId);
		stat.setSessionId(sessionId);
		stat.setUnitId(unitId);
		stat.setProcessStartMili(Calendar.getInstance().getTimeInMillis());
		statDao.setStartStat(stat);
	}

	@Override
	public void endStat(Integer jobId, Integer sessionId) {
		DBStatistics stat = statDao.getStat(jobId, sessionId);
		stat.setProcessEndMili(Calendar.getInstance().getTimeInMillis());
		stat.setInterval(stat.getProcessEndMili() - stat.getProcessStartMili());
		statDao.setEndStat(stat);
	}

}
