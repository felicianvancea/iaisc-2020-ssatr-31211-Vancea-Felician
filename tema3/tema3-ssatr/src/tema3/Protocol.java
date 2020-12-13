package tema3;

import java.util.List;

public class Protocol {
	private String nume;
	private List<String> mesajein;
	private List<String> mesajeout;
 public Protocol(String nume, List<String> mesajein, List<String> mesajeout)
 {
	 this.nume=nume;
	 this.mesajein=mesajein;
	 this.mesajeout=mesajeout;
 }
 
 public List<String> getmesajein()
 {
	 return mesajein;
 }
 
 public List<String> getmesajeout()
 {
	 return mesajeout;
 }
}
