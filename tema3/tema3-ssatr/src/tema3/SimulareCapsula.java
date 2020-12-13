package tema3;

import java.util.ArrayList;
import java.util.List;

public class SimulareCapsula {

	public static void main(String[] args) {
		List<String> mesajein= new ArrayList();
		List<String> mesajeout= new ArrayList();
		
		mesajein.add("comandaiesire");
		mesajeout.add("comandaiesire");
		
		Protocol pr1= new Protocol("pr1",mesajein,mesajeout);
		
        Port po1= new Port("po1",pr1);
        Port po2 = new Port("po2",pr1);
        Port po3 = new Port("po3",pr1);
        Port po4= new Port("po4",pr1);
        
        List<Port> porturic1= new ArrayList();
        porturic1.add(po1);
        porturic1.add(po2);
        
        List<Port> porturic2= new ArrayList();
        porturic2.add(po3);
        porturic2.add(po4);
        
        po1.conecteazaporturi(po4);
        po4.conecteazaporturi(po1);
        
        po2.conecteazaporturi(po3);
        po3.conecteazaporturi(po2);
        
        Capsula c1 = new Capsula("Capsula1",1,porturic1);
        Capsula c2 = new Capsula("Capsula2",2,porturic2);
        
        c1.genereazacomportament();
        c2.genereazacomportament();
        
        c1.executacomportament();
        c2.executacomportament();
	}

}
