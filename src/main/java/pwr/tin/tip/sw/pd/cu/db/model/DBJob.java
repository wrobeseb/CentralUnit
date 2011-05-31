package pwr.tin.tip.sw.pd.cu.db.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import pwr.tin.tip.sw.pd.cu.jms.model.Job;

@Entity
@Table(name="cu_job")
@SequenceGenerator(name="sequence", allocationSize=1, sequenceName="cu_job_seq")
public class DBJob {

	private Integer idJob;
	private Integer cuId;
	private Integer sessionId;
	private Integer msgId;
	private String  requestMsgBody;
	private Date    requestMsgArrivalDt;
	private String  responseMsgBody;
	private Date    responseMsgSentDt;
	
	public DBJob() {}
	
	public DBJob(Job job) {
		// TODO
	}
	
	@Id
	@Column(name="id_job")
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	public Integer getIdJob() {
		return idJob;
	}
	public void setIdJob(Integer idJob) {
		this.idJob = idJob;
	}

	@Column(name="cu_id")
	public Integer getCuId() {
		return cuId;
	}
	public void setCuId(Integer cuId) {
		this.cuId = cuId;
	}
	
	@Column(name="session_id")
	public Integer getSessionId() {
		return sessionId;
	}
	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
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

	@Column(name="request_msg_arrival_dt")
	public Date getRequestMsgArrivalDt() {
		return requestMsgArrivalDt;
	}
	public void setRequestMsgArrivalDt(Date requestMsgArrivalDt) {
		this.requestMsgArrivalDt = requestMsgArrivalDt;
	}

	@Column(name="response_msg_body")
	public String getResponseMsgBody() {
		return responseMsgBody;
	}
	public void setResponseMsgBody(String responseMsgBody) {
		this.responseMsgBody = responseMsgBody;
	}

	@Column(name="response_msg_sent_dt")
	public Date getResponseMsgSentDt() {
		return responseMsgSentDt;
	}
	public void setResponseMsgSentDt(Date responseMsgSentDt) {
		this.responseMsgSentDt = responseMsgSentDt;
	}
}
