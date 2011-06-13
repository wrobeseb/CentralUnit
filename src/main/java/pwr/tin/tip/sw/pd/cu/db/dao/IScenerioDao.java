package pwr.tin.tip.sw.pd.cu.db.dao;

import pwr.tin.tip.sw.pd.cu.db.model.DBJob;
import pwr.tin.tip.sw.pd.cu.db.model.DBJobTask;

public interface IScenerioDao {
	public void save(Object obj);
	public DBJob getJobById(Integer id);
	public DBJobTask getJobTaskBySessionIdAndTaskId(Integer sessionId, Integer taskId);
}
