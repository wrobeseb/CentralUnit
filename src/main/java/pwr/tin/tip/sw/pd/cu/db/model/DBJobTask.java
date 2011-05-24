package pwr.tin.tip.sw.pd.cu.db.model;

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
	private Integer jobId;
	private Integer sessionId;
	private Integer cuId;
	private Integer msgId;
	private String  requestMsgBody;
	private Date    requestMsgSentDt;
	private String  responseMsgBody;
	private Date    responseMsgArrivalDt;
	
	public DBJobTask() {}
	
	public DBJobTask(JobTask jobTask) {
		// TODO
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

	@Column(name="job_id")
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
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

	@Column(name="msg_id")
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
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
