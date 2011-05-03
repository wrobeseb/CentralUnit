package pwr.tin.tip.sw.pd.cu.ti.jms.themes;

public class Themes {

	public final static String esbIn = "<algorithm><sessionId>1</sessionId><algorithmId>1</algorithmId><algorithmEndpoint>eai:TestAlgorithm1</algorithmEndpoint><sourceFilePath>//path/to/file/withName</sourceFilePath><resultFilePath>//path/to/file/withName</resultFilePath></algorithm>";
	public final static String esbOutProcessed = "<algorithm><sessionId>1</sessionId><algorithmId>1</algorithmId><status>PROCESSED</status><startDate>2010-09-20 12:23:45</startDate><endDate>2010-09-20 12:23:46</endDate><time>1234</time></algorithm>";
	public final static String esbOutWithWorning = "<algorithm><sessionId>1</sessionId><algorithmId>1</algorithmId><status>WARNING</status><warningDesc>Komunikat opisuj¹cy zdarzenie</warningDesc><startDate>2010-09-20 12:23:45</startDate><endDate>2010-09-20 12:23:46</endDate><time>1234</time></algorithm>";
	public final static String esbOutWithErrors = "<algorithm><sessionId>1</sessionId><algorithmId>1</algorithmId><status>ERROR</status><errorDesc>Komunikat opisuj¹cy zdarzenie</errorDesc><startDate>2010-09-20 12:23:45</startDate><endDate>2010-09-20 12:23:46</endDate><time>1234</time></algorithm>";
	public final static String fiveBloksParallel = "<scenerio><id>1</id><name>Przyk³ad_2</name><description>Przy³ad scenariusza z przypadkiem zrównoleglenia zadañ.</description><start><next>1</next></start><components><algorithm><id>1</id><sessionId>1</sessionId><next>2</next></algorithm><algorithm><id>2</id><sessionId>1</sessionId><next>3</next><next>4</next></algorithm><algorithm><id>3</id><sessionId>1</sessionId><next>5</next></algorithm><algorithm><id>4</id><sessionId>1</sessionId><next>5</next></algorithm><algorithm><id>5</id><sessionId>1</sessionId></algorithm></components><end><prev>5</prev></end></scenerio>";
}
