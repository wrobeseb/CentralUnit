package pwr.tin.tip.sw.pd.cu.db.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import pwr.tin.tip.sw.pd.cu.db.dao.IScenerioDao;

@Repository(value="scenerioDao")
public class ScenerioDaoImpl extends HibernateDaoSupport implements IScenerioDao {

	@Autowired(required=true)
	public ScenerioDaoImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	@Override
	public void save(Object obj) {
		getSession().save(obj);
	}
}
