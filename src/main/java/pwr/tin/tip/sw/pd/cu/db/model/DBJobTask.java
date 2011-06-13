package pwr.tin.tip.sw.pd.cu.db.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pwr.tin.tip.sw.pd.cu.jms.model.JobTask;

@Entity
@Table(name="cu_job_task")
@SequenceGenerator(name="sequence", allocationSize=1, sequenceName="cu_job_task_seq")
public class DBJobTask {

	private Integer idJobTask;
	private Integer taskId;
	private Integer sessionId;
	private Integer cuId;
	private String  requestMsgBody;
	private Date    requestMsgSentDt;
	private String  responseMsgBody;
	private Date    responseMsgArrivalDt;
	
	public DBJobTask() {}
	
	public DBJobTask(JobTask jobTask) {
		taskId = jobTask.getId();
		sessionId = jobTask.getSessionId();
		cuId = jobTask.getCuId();
		requestMsgBody = jobTask.getXml();
		requestMsgSentDt = Calendar.getInstance().getTime();
	}

	@Id
	@Column(name="id_job_task")
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	public Integer getIdJobTask() {
		return idJobTask;
	}
	public void setIdJobTask(Integer idJobTask) {
		this.idJobTask = idJobTask;
	}
	
	@Column(name="task_id")
	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	@Column(name="session_id")
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name="cu_id")
	public Integer getCuId() {
		return cuId;
	}
	public void setCuId(Integer cuId) {
		this.cuId = cuId;
	}

	@Column(name="request_msg_body")
	public String getRequestMsgBody() {
		return requestMsgBody;
	}
	public void setRequestMsgBody(String requestMsgBody) {
		this.requestMsgBody = requestMsgBody;
	}

	@Column(name="request_msg_sent_dt")
	public Date getRequestMsgSentDt() {
		return requestMsgSentDt;
	}
	public void setRequestMsgSentDt(Date requestMsgSentDt) {
		this.requestMsgSentDt = requestMsgSentDt;
	}

	@Column(name="response_msg_body")
	public String getResponseMsgBody() {
		return responseMsgBody;
	}
	public void setResponseMsgBody(String responseMsgBody) {
		this.responseMsgBody = responseMsgBody;
	}

	@Column(name="response_msg_arrival_dt")
	public Date getResponseMsgArrivalDt() {
		return responseMsgArrivalDt;
	}
	public void setResponseMsgArrivalDt(Date responseMsgArrivalDt) {
		this.responseMsgArrivalDt = responseMsgArrivalDt;
	}
}
