import java.util.ArrayList;

public class Wioska {
Punkt wsp;
int typ; //0 - cel, 1 - off, 2 - fejk off, 3 - fejk def
ArrayList<Wioska> polaczone = new ArrayList<Wioska>();
int max_pol;

Wioska(Punkt wsp, int typ) {
	this.wsp = wsp;
	this.typ = typ;
}

Wioska(String wsp, int typ){
	set(wsp, typ);
}

Wioska(Punkt wsp) {
	set(wsp);
}

Wioska(int x, int y) {
	set(x, y);
}

Wioska(int x, int y, int typ) {
	set(x, y, typ);
}

void set(String wsp) {
	this.wsp = new Punkt(wsp);
}

void set(String wsp, int typ) {
	//System.out.println("elo "+wsp);
	this.wsp = new Punkt(wsp);
	//System.out.println("elo2 "+this.wsp.x);
	this.typ = typ;
}

void set(Punkt wsp) {
	this.wsp = wsp;
}

void set(int x, int y) {
	wsp = new Punkt(x, y);
}

void set(int x, int y, int typ) {
	wsp = new Punkt(x, y);
	this.typ = typ;
}

void set_max_pol(int ile) {
	max_pol = ile;
}

Punkt get_wsp() {
	return wsp;
}

double get_odl(Wioska wioska) {
	double odl = wsp.getodl(wioska.get_wsp());
	return odl;
}

double get_odl(int x, int y) {
	double odl = wsp.getodl(x, y);
	return odl;
}

int add(Wioska inna){
	polaczone.add(inna);
	return polaczone.size();
}

int ile_polaczonych(){
	return polaczone.size();
}

Wioska znajdz_najblizsza(ArrayList<Wioska> lista) {
	int id = -1;
	double min = 100000;
	//System.out.println("tutaj"+lista.size());
	for (int i = 0; i < lista.size(); i++) {
		if (get_odl(lista.get(i)) < min) {
			//System.out.println("w wiosce = "+lista.get(i).get_wsp().get_wsp()+" przy id ="+i+" polsize = "+lista.get(i).polaczone.size()+"max pol = "+lista.get(i).max_pol);
			if(spr(lista.get(i))) {
			//System.out.println("tutawfasj"+lista.size());
			min = get_odl(lista.get(i));
			id = i;
			}}}
	if (id != -1)return lista.get(id);
	else return new Wioska("000|000",1);
}

boolean spr(Wioska wioska) {
	//System.out.println("polaczone.size() < max_pol = "+(polaczone.size() < max_pol)+"polaczone.indexOf(wioska) == -1 = "+(polaczone.indexOf(wioska) == -1)+" !wioska.equals(new Wioska(\"000|000\",1)) = "+(!wioska.equals(new Wioska("000|000",1)))+ " !this.equals(new Wioska(\"000|000\",1)) = "+(!this.equals(new Wioska("000|000",1))) );
	if (polaczone.size() < max_pol && polaczone.indexOf(wioska) == -1 && !wioska.equals(new Wioska("000|000",1)) && !this.equals(new Wioska("000|000",1)))	return true;
	else return false;
	
}

int dodaj_polaczona(Wioska wioska) {
	if (spr(wioska)) {
		polaczone.add(wioska);
		return max_pol - polaczone.size();
	}
	return -1;
}

@Override
public boolean equals(Object obj) {
	 if (obj == null) {
         return false;
     }
     if (obj instanceof Wioska) {
         Wioska other = (Wioska) obj;
         return wsp.get_wsp().equals(other.wsp.get_wsp()) &&
                typ == other.typ;
     }
     return false;
 }

}
