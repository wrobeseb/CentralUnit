package pwr.tin.tip.sw.pd.cu.db.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pwr.tin.tip.sw.pd.cu.db.dao.IUnitDao;
import pwr.tin.tip.sw.pd.cu.db.model.DBUnit;
import pwr.tin.tip.sw.pd.cu.db.model.enums.UnitType;
import pwr.tin.tip.sw.pd.cu.db.service.IUnitService;
import pwr.tin.tip.sw.pd.cu.db.utils.AddressUtils;
import pwr.tin.tip.sw.pd.cu.db.utils.DateTime;

@Component("unitService")
public class UnitServiceImpl implements IUnitService {

	@Autowired(required=true)
	private IUnitDao unitDao;
	
	@Value("${central.unit.id}")
	private Integer unitId;
	
	@Value("${thread.pool.max.tasks}")
	private Integer maxProcessNo;

	@Override
	@PostConstruct
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void registerUnit() {
		DBUnit unit = new DBUnit();
		unit.setIdUnit(unitId);
		unit.setAddressIp(AddressUtils.getLocalIpAddress());
		unit.setMaxProcessNo(maxProcessNo);
		unit.setOverloadFlg(false);
		unit.setType(UnitType.CU);
		unit.setLastUpdateDt(DateTime.now());
		unitDao.save(unit);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void setOverload() {
		unitDao.setOverload(unitId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void setFree() {
		unitDao.setFree(unitId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void ping() {
		unitDao.ping(unitId);
	}

	@Override
	@PreDestroy
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void removeUnit() {
		unitDao.removeUnit(unitId);
	}
	
	
}
