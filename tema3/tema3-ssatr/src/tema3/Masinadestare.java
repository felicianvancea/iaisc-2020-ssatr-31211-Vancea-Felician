package tema3;

import java.util.List;

public class Masinadestare extends Thread {
private String numemasinastare;
private List<Port> porturi;
public Masinadestare(String nume, List<Port> porturi)
{
	numemasinastare=nume;
	this.porturi=porturi;
}
 public void run()
 {
	 System.out.println("Se executa " +numemasinastare);
	 trimitemesajPort("comandaiesire",porturi.get(0));
 }
 
 public void trimitemesajPort(String mesaj, Port p)
 {
	 p.trimitemesaj(mesaj);
 }
}
