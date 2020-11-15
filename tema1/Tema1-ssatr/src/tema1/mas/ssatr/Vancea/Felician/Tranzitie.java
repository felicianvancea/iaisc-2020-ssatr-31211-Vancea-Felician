package mas.ssatr.Vancea.Felician;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tranzitie {
private String numeTranzitie;
private Integer temporizareMaxima;	
private Integer temporizareMinima;
private List<String> deLaLocatie;
private List<String> spreLocatie;
private boolean EsteexecutbailalaJeton;
private boolean EsteexecutabilaTemporizare;
private Integer timpAsteptareTotal;
private Integer timpAsteptare;

public Tranzitie(String nume, Integer temporizaremax,Integer temporizaremin, List<String> delaLocatie, List<String> spreLocatie)
{
	this.numeTranzitie=nume;
	this.temporizareMaxima=temporizaremax;
	this.temporizareMinima=temporizaremin;
	this.deLaLocatie=delaLocatie;
	this.spreLocatie=spreLocatie;
}

public String getNumeTranzitie() {
	return this.numeTranzitie;
}

public void setEsteExecutabilaTemporizare(boolean b) {
	this.EsteexecutabilaTemporizare=b;
	
}
public int genereazaTemporizare()
{
	 Random random = new Random();
     if (temporizareMaxima != temporizareMinima) {
         timpAsteptareTotal = random.nextInt((temporizareMaxima - temporizareMinima) +1) + temporizareMinima;
     } else {
         timpAsteptareTotal = temporizareMaxima;
     }
     return timpAsteptareTotal;
}
public void modificaTimpAstepareExecutie() {
    timpAsteptare++;
}

public void reseteazaTimpAstepare() {
    timpAsteptare = 0;
    timpAsteptareTotal = genereazaTemporizare();
}

public boolean isExecutabilaJetoane(List<Locatie> locatii) {
    List<Locatie> l = new ArrayList();
    for (Locatie locatie : locatii) {
        if (deLaLocatie.contains(locatie.getNumeLocatie())) {
            l.add(locatie);
        }
    }
    if (l.stream().allMatch(locatie -> locatie.getJeton() > 0))
        EsteexecutbailalaJeton = true;
    else
        EsteexecutbailalaJeton = false;
    return EsteexecutbailalaJeton;
}

public void executaTranzitie(List<Locatie> locatii) {
    locatii.forEach(locatie -> {
        if (deLaLocatie.contains(locatie.getNumeLocatie()))
            locatie.scadejeton();
        if (spreLocatie.contains(locatie.getNumeLocatie()))
            locatie.cresterejeton();
    });
}

public boolean isExecutabilaTemporizare(List<Locatie> locatii) {
    List<Locatie> l = new ArrayList();
    for (Locatie locatie : locatii) {
        if (deLaLocatie.contains(locatie.getNumeLocatie())) {
            l.add(locatie);
        }
    }
    if (l.stream().allMatch(locatie -> locatie.getJeton() > 0) && timpAsteptare >= timpAsteptareTotal)
        EsteexecutabilaTemporizare = true;
    else
        EsteexecutabilaTemporizare = false;
    return EsteexecutabilaTemporizare;
}




}
