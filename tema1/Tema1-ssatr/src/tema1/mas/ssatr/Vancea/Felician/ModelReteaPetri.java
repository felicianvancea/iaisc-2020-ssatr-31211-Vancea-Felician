package mas.ssatr.Vancea.Felician;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelReteaPetri {
	    private int timpCurent;
	    private List<Locatie> locatii;
	    private List<Tranzitie> tranzitii;

	   public ModelReteaPetri(List<Locatie> locatii, List<Tranzitie> tranzitii)
	   {
		   this.locatii=locatii;
		   this.tranzitii=tranzitii;
		   timpCurent=0;
	   }
public void modificaTimpCurent() {
    timpCurent ++;
}


public List<Tranzitie> getTranzitiiExecutabile() {
    List<Tranzitie> tranzitiiExecutabile = new ArrayList<>();
    tranzitii.forEach(tranzitie -> {
        if (tranzitie.isExecutabilaJetoane(locatii)) {
            tranzitiiExecutabile.add(tranzitie);
        }
    });
    return tranzitiiExecutabile;
}

public Map<String, Integer> salveazaMarcajul() {
    Map<String, Integer> marcajCurent = new HashMap<>();
    locatii.forEach(locatie -> marcajCurent.put(locatie.getNumeLocatie(), locatie.getJeton()));
    return marcajCurent;
}

public boolean isBucla(Map<String, Integer> marcajInitial, Map<String, Integer> marcajCurent) {
    return marcajInitial.equals(marcajCurent);
}
public boolean isBlocaj() {
    if (getTranzitiiExecutabile() == null || getTranzitiiExecutabile().size() <= 0)
        return true;
    else
        return false;
}

public void adaugaDateFisierIesire(String rezultatPartial) {
    try (FileWriter fileWriter = new FileWriter("OutputFilePetriNets", true);
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
        bufferedWriter.write(rezultatPartial);
    } catch (IOException e) {
        System.out.println("Eroare la scriere in fisier");
    }
}
public void curataFisierIesireStart() {
    try (FileWriter fileWriter = new FileWriter("OutputFilePetriNets", false)) {
    } catch (IOException e) {
        System.out.println("Fisierul nu poate fi golit");
    }
}
public void simuleazaReteaPetri() {
    curataFisierIesireStart();
    Map<String, Integer> marcajCurent = new HashMap<>();
    Map<String, Integer> marcajInitial = salveazaMarcajul();
    List<Tranzitie> tranzitiiExecutabile;
    System.out.println("Marcaj Initial: " + salveazaMarcajul().toString() + "\n");
    adaugaDateFisierIesire("Marcaj Initial: " + salveazaMarcajul().toString() + "\n");
    
    tranzitii.forEach(tranzitie -> tranzitie.reseteazaTimpAstepare());
    while (!isBucla(marcajInitial, marcajCurent)) {
        tranzitiiExecutabile = getTranzitiiExecutabile();
        tranzitii.forEach(tranzitie -> {
            tranzitie.isExecutabilaJetoane(locatii);
            tranzitie.isExecutabilaTemporizare(locatii);
        });
        if (!isBlocaj()) {
            for (Tranzitie tranzitie : tranzitiiExecutabile) {
                 if (tranzitie.isExecutabilaJetoane(locatii)) {
                    tranzitie.modificaTimpAstepareExecutie();
                } else {
                     adaugaDateFisierIesire("blocaj");
                    System.out.println("blocaj");
                    tranzitie.reseteazaTimpAstepare();
                }
                if (tranzitie.isExecutabilaTemporizare(locatii)) {
                    tranzitie.executaTranzitie(locatii);
                    marcajCurent = salveazaMarcajul();
                    adaugaDateFisierIesire("Tranzitie executata: " + tranzitie.getNumeTranzitie() +
                            ", Timpul la care este executata:" + timpCurent +
                            ", Marcaj obtinut: " + marcajCurent.toString() + "\n");
                    System.out.println("Tranzitie executata: " + tranzitie.getNumeTranzitie() +
                            ", Timpul la care este executata:" + timpCurent +
                            ", Marcaj obtinut: " + marcajCurent.toString() + "\n");
                    tranzitie.reseteazaTimpAstepare();
                    tranzitie.isExecutabilaJetoane(locatii);
                    tranzitie.setEsteExecutabilaTemporizare(false);
                    continue;
                }
            }
        } else {
            adaugaDateFisierIesire("Nu mai exista tranzitii executabile");
            System.out.println("Nu mai exista tranzitii executabile");
            break;
        }
        modificaTimpCurent();
    }
}
}
