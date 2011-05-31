package pwr.tin.tip.sw.pd.cu.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="statistic")
@SequenceGenerator(name="sequence", allocationSize=1, sequenceName="statistic_seq")
public class DBStatistics {
	
	private Integer id;
	private Integer unitId;
	private Integer sessionId;
	private Integer jobId;
	private Long processStartMili;
	private Long processEndMili;
	private Long interval;
	
	@Id
	@Column(name="id")
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="unit_id")
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	@Column(name="session_id")
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}
	
	@Column(name="job_id")
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	
	@Column(name="process_start_mili")
	public Long getProcessStartMili() {
		return processStartMili;
	}
	public void setProcessStartMili(Long processStartMili) {
		this.processStartMili = processStartMili;
	}
	
	@Column(name="process_end_mili")
	public Long getProcessEndMili() {
		return processEndMili;
	}
	public void setProcessEndMili(Long processEndMili) {
		this.processEndMili = processEndMili;
	}
	
	@Column(name="interval")
	public Long getInterval() {
		return interval;
	}
	public void setInterval(Long interval) {
		this.interval = interval;
	}
}
