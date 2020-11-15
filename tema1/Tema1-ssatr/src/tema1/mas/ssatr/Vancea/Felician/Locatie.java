package mas.ssatr.Vancea.Felician;

public class Locatie {
	private String numeLocatie;
	private Integer marcaj;
	
	public Locatie(String numeLocatie, Integer jeton) {
		this.numeLocatie = numeLocatie;
		this.marcaj = jeton;
	}

	public void cresterejeton() {
		marcaj++;
	}

	public void scadejeton() {
		marcaj--;
	}
	
	public String getNumeLocatie()
	{
		return this.numeLocatie;
	}
	public Integer getJeton()
	{
		
		return this.marcaj;
	}
	
}
