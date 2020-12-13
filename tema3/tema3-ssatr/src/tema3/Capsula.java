package tema3;

import java.util.ArrayList;
import java.util.List;

public class Capsula {
	private String numeCapsula;
	private List<Masinadestare> comportamente;
	private List<Port> porturi;
	private int nrcomportamente;
public Capsula(String numeCapsula, int nrcomportamente, List<Port> porturi)
{
	this.numeCapsula=numeCapsula;
	this.nrcomportamente=nrcomportamente;
	this.porturi=porturi;
}

public void genereazacomportament()
{
	comportamente=new ArrayList();
	for(int i=0;i<nrcomportamente;i++)
	{
		comportamente.add(new Masinadestare("Comportamentul "+i+ " al capsulei cu numele:"+numeCapsula,porturi));
	}
}
public void executacomportament()
{
	for(Masinadestare com:comportamente)
	{
		com.start();
	}
}
}
