package pwr.tin.tip.sw.pd.cu.db.dao.impl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pwr.tin.tip.sw.pd.cu.db.dao.IScenerioDao;
import pwr.tin.tip.sw.pd.cu.db.model.DBJob;
import pwr.tin.tip.sw.pd.cu.db.model.DBJobTask;

@Repository(value="scenerioDao")
public class ScenerioDaoImpl extends HibernateDaoSupport implements IScenerioDao {

	@Autowired(required=true)
	public ScenerioDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public void save(Object obj) {
		getSession().saveOrUpdate(obj);
	}

	@Override
	public DBJob getJobById(Integer id) {
		return (DBJob)getSession().get(DBJob.class, id);
	}

	@Override
	public DBJobTask getJobTaskBySessionIdAndTaskId(Integer sessionId,
			Integer taskId) {
		return (DBJobTask)getSession().createCriteria(DBJobTask.class).add(Restrictions.and(Restrictions.eq("sessionId", sessionId), Restrictions.eq("taskId", taskId))).uniqueResult();
	}
}
