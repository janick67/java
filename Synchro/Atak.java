import java.text.SimpleDateFormat;

public class Atak  implements Comparable<Atak> {
Wioska cel, pochodzenie;
Czas dotarcie, start;
String trwanie;
int typ; //0 - off, 1 - fejk off, 2 - fejk def

Atak(Wioska cel, Czas dotarcie, Wioska pochodzenie, Czas start, int typ) {
	this.cel = cel;
	this.dotarcie = dotarcie;
	this.pochodzenie = pochodzenie;
	this.start = start;
	this.typ = typ;
	trwanie = Jednostki.czas_jednostki(Jednostki.TARAN,cel.get_odl(pochodzenie));
	ustaw_pol();
}

/*Atak(Wioska cel, Czas dotarcie, Wioska pochodzenie, Czas start, int typ) {
	this.cel = cel;
	this.dotarcie = dotarcie;
	this.pochodzenie = pochodzenie;
	this.start = start;
	this.typ = typ;
	trwanie = Jednostki.czas_jednostki(Jednostki.TARAN,cel.get_odl(pochodzenie));
}
*/
Atak(Wioska cel, Wioska pochodzenie, Czas start, int typ) {
	this.cel = cel;
	this.dotarcie = new Czas("");
	this.pochodzenie = pochodzenie;
	this.start = start;
	this.typ = typ;
	trwanie = Jednostki.czas_jednostki(Jednostki.TARAN,cel.get_odl(pochodzenie));
	ustaw_pol();
}

Atak(Wioska cel, Czas dotarcie, Wioska pochodzenie, int typ) {
	this.cel = cel;
	this.dotarcie = dotarcie;
	this.pochodzenie = pochodzenie;
	this.start = new Czas("");
	this.typ = typ;
	trwanie = Jednostki.czas_jednostki(Jednostki.TARAN,cel.get_odl(pochodzenie));
	ustaw_pol();
}

Atak(Wioska cel, String dotarcie, Wioska pochodzenie, String start, int typ) {
	this.cel = cel;
	this.dotarcie = new Czas(dotarcie);
	this.pochodzenie = pochodzenie;
	this.start = new Czas(start);
	this.typ = typ;
	trwanie = Jednostki.czas_jednostki(Jednostki.TARAN,cel.get_odl(pochodzenie));
	ustaw_pol();
}

Atak(Wioska cel, String dotarcie, Wioska pochodzenie, int typ) {
	this.cel = cel;
	this.dotarcie = new Czas(dotarcie);
	this.pochodzenie = pochodzenie;
	this.start = new Czas("");
	this.typ = typ;
	//System.out.println("tu jestem " + cel.wsp.x);
	trwanie = Jednostki.czas_jednostki(Jednostki.SZLACHCIC,cel.get_odl(pochodzenie));
	System.out.println("trwanie = "+trwanie);
	ustaw_pol();
}

Atak(Wioska cel, Wioska pochodzenie, String start, int typ) {
	this.cel = cel;
	this.dotarcie = new Czas("");
	this.pochodzenie = pochodzenie;
	this.start = new Czas(start);
	this.typ = typ;
	trwanie = Jednostki.czas_jednostki(Jednostki.TARAN,cel.get_odl(pochodzenie));
	ustaw_pol();
}

Czas get_time(boolean dotarcie_albo_start) {
	if (!dotarcie_albo_start) {
		return dotarcie;
	}
	else {
		return start;	
	}}


String get_time(boolean dotarcie_albo_start, boolean string) {
	if (!dotarcie_albo_start) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss::SSS MM-dd");
		return dateFormat.format(dotarcie.get().getTime());
	}else {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss::SSS MM-dd");
		return dateFormat.format(start.get().getTime());
	}}

void set_cel(Wioska cel) {
	this.cel = cel;
}

void set_pochodzenie(Wioska pochodzenie) {
	this.pochodzenie = pochodzenie;
}

void oblicz(int flag) {//0 - domy�lnie, 1 - na podstawie planowanego czasu dotarcia, 2 - na podstawie planownaego czasu wyruszenia
	if (flag == 0) {
		if (dotarcie.get().getTimeInMillis()>10) {
			oblicz(1);
		}else {
			oblicz(2);
		}}
	if (flag == 1) {
		start.set(dotarcie.odejmij(trwanie));
	}else{
		dotarcie.set(start.dodaj(trwanie));
	}
}

void ustaw_pol() {
	oblicz(0);
	//System.out.println("powinno doda� pochodzenie do celu");
	cel.dodaj_polaczona(pochodzenie);
	//System.out.println("powinno doda� cel do pochodzenia");
	pochodzenie.dodaj_polaczona(cel);
	//System.out.println("w ustwa pole = "+pochodzenie.polaczone.size());
	//System.out.println("size pochodzenie = "+pochodzenie.polaczone.size()+" size cel = "+cel.polaczone.size());
}

@Override
public int compareTo(Atak o) {
    int porownaneNazwiska = (int) (start.get().getTimeInMillis()-o.start.get().getTimeInMillis());
        return porownaneNazwiska;
}

}



