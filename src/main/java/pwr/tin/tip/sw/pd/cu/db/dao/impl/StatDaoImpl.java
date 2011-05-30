package pwr.tin.tip.sw.pd.cu.db.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pwr.tin.tip.sw.pd.cu.db.dao.IStatDao;
import pwr.tin.tip.sw.pd.cu.db.model.DBStatistics;

@Repository(value="statDao")
public class StatDaoImpl extends HibernateDaoSupport implements IStatDao {

	@Autowired(required=true)
	public StatDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public void setStartStat(DBStatistics stat) {
		SQLQuery query = getSession().createSQLQuery("INSERT INTO statistic (id, unit_id, session_id, job_id, process_start_mili) VALUES (nextval ('statistic_seq'), ?, ?, ?, ?)");
		query.setInteger(0, stat.getUnitId());
		query.setInteger(1, stat.getSessionId());
		query.setInteger(2, stat.getJobId());
		query.setLong(3, stat.getProcessStartMili());
		query.executeUpdate();
	}

	@Override
	public void setEndStat(DBStatistics stat) {
		SQLQuery query = getSession().createSQLQuery("UPDATE statistic SET process_end_mili = ?, interval = ? WHERE id = ?");
		query.setLong(0, stat.getProcessEndMili());
		query.setLong(1, stat.getInterval());
		query.setInteger(2, stat.getId());
		query.executeUpdate();
	}

	@Override
	public DBStatistics getStat(Integer jobId, Integer sessionId) {
		return (DBStatistics) getSession().createCriteria(DBStatistics.class).add(Restrictions.and(Restrictions.eq("jobId", jobId), Restrictions.eq("sessionId", sessionId))).uniqueResult();
	}

}
