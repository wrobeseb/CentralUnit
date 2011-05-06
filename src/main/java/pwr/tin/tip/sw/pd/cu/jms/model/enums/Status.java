package pwr.tin.tip.sw.pd.cu.jms.model.enums;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Status {
	PROCESSED,
	WARNING,
	ERROR,
	SEVERITY;
}
