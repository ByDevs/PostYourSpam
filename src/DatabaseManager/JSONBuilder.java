package DatabaseManager;

/**
 * useful class that generate JSON String
 * @author AlessioTranzocchi and Tranzocchi Alessio
 */
public class JSONBuilder {
	
	private String json;
	private int size;
	
	/**
	 * The constructor initializate the string and the size
	 */
	public JSONBuilder() {
		json = "{ ";
		size = 0;
	}

	/**
	 * @param key : the key of the JSON element
	 * @param value : the value of the JSON element
	 * @return the new JSONBuilder configuration
	 */
	public JSONBuilder append(String key,String value) {
		json+="\""+key+"\":\""+value+"\",";
		size++;
		return this;
	}

	/**
	 * @return the final JSON String 
	 */
	public String build() {
		return json+"\"jsonSize\":\""+size+"\" }";
	}

}
