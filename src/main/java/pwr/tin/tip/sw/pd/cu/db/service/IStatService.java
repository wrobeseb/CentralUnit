package pwr.tin.tip.sw.pd.cu.db.service;

public interface IStatService {
	public void startStat(Integer jobId, Integer sessionId);
	public void endStat(Integer jobId, Integer sessionId);
}
