package pwr.tin.tip.sw.pd.cu.db.dao;

import pwr.tin.tip.sw.pd.cu.db.model.DBStatistics;

public interface IStatDao {
	public DBStatistics getStat(Integer jobId, Integer sessionId);
	public void setStartStat(DBStatistics stat);
	public void setEndStat(DBStatistics stat);
}
