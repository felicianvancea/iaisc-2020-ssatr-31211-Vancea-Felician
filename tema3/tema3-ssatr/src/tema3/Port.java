package tema3;

public class Port {
	private String nume;
	private Protocol protocol;
	private String mesaj;
	private Port PortConectat;
	
public Port(String nume, Protocol protocol)
{
	this.nume=nume;
	this.protocol=protocol;
	mesaj="";
	PortConectat=null;
}

public void conecteazaporturi(Port port)
{
	PortConectat=port;
}
public void trimitemesaj(String mesaj)
{
	if(protocol.getmesajeout().contains(mesaj))
	{
		     System.out.println("Portul "+nume+ " a trimis mesajul:"+mesaj);
		     PortConectat.receptiemesaj(mesaj); 	
	         }        
	else
	{
		System.out.println("Nu se poate trimite mesajul:"+mesaj);
	}
}
public void receptiemesaj(String mesaj)
{
	if(protocol.getmesajein().contains(mesaj))
	{
	          this.mesaj=mesaj;
	          System.out.println("Portul "+nume+ " a primit mesajul:"+mesaj);
	}
	else
	{
		System.out.println("Nu se poate primi mesajul:"+mesaj);
	}
	}
public String getmesaj()
{
	return mesaj;
}
}
