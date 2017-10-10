import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ParseException, FileNotFoundException {
	//offy
		Czas planowany = new Czas("2017-10-07 8:04:00::000");
		Czas planowany2 = new Czas("2017-10-07 8:04:00::010");
		
		ArrayList<Wioska> offy = wczyt("offy.txt");
		ArrayList<Wioska> fejkooffy = wczyt("offy.txt");
		ArrayList<Wioska> coffy = wczyt("coffy.txt");
		ArrayList<Wioska> fejki = wczyt("fejki.txt");
		ArrayList<Wioska> cfejki = wczyt("coffy.txt");
		
		System.out.print("Offy = "+offy.size()+" coffy = "+coffy.size()+" fejki = "+fejki.size()+" cfejki = "+cfejki.size());
		System.out.println("cfejki = "+cfejki.get(0).get_wsp().get_wsp());
		ArrayList<Atak> ataki = new ArrayList<Atak>();
				
		ustaw_max(offy, 1);
		ustaw_max(fejkooffy, 0);		
		ustaw_max(coffy, 4);
		ustaw_max(fejki, 1);
		ustaw_max(cfejki, 9);
		
/*		ataki.addAll(tworz_ataki(fejki,cfejki,planowany,1));
		System.out.println("ataki = "+ataki.size());
		ataki.addAll(tworz_ataki(fejkooffy,cfejki,planowany,1));
		System.out.println("ataki = "+ataki.size());
		ataki.addAll(tworz_ataki(offy,coffy,planowany,0));
		System.out.println("ataki = "+ataki.size());*/
		
		
		ataki.addAll(tworz_ataki(fejki,coffy,planowany2,1));
		ataki.addAll(tworz_ataki(offy,coffy,planowany,0));
		
		
		System.out.println("max pol = "+fejki.get(0).max_pol+" pol = "+fejki.get(0).polaczone.size());
		
		Collections.sort(ataki);
		
		
		wypisz_ataki(ataki);
		
		/*Wioska[] wioseczki = {new Wioska("357|389", 0),new Wioska("354|390", 1)};
		Atak nowy = new Atak(wioseczki[0],"2017-10-03 14:18:38",wioseczki[1],0);*/
	}
	
	static void ustaw_max(ArrayList<Wioska> wioski, int ile) {
		for (int i = 0; i < wioski.size(); i++) {
			wioski.get(i).set_max_pol(ile);
		}
	}
	
	static ArrayList<Wioska> wczyt(String dir) throws FileNotFoundException{
		ArrayList<Wioska> wioski = new ArrayList<Wioska>();
		String[] wsp = wczyt_plik(dir);
		for (int i = 0; i < wsp.length; i++) {
			wioski.add(new Wioska(wsp[i],1));
		}
		
		return wioski;
	}

	static String[] wczyt_plik(String dir) throws FileNotFoundException{
		
		File file = new File(dir);
		Scanner in = new Scanner(file);
		String wioski = in.nextLine();
		in.close();
		String[] parts = wioski.split(";");
		
		return parts;
	}

	static ArrayList<Atak> tworz_ataki(ArrayList<Wioska> pochodzenie, ArrayList<Wioska> cel, Czas planowany, int typ) {
		ArrayList<Atak> ataki = new ArrayList<Atak>();
		Wioska wioska, nowa;
		for (int i = 0; i < pochodzenie.size(); i++) {
			wioska = pochodzenie.get(i);
			//System.out.println(" i = "+i+" wsp =  "+wioska.get_wsp().get_wsp());
			do {
				//System.out.println(i);
				nowa = wioska.znajdz_najblizsza(cel);
/*				System.out.println(nowa.get_wsp().get_wsp());
				System.out.println(wioska.polaczone.size() < wioska.max_pol);
				System.out.println(!nowa.equals(new Wioska("000|000",1)));*/
				//System.out.println("tutaj sprawdza pierwszy raz");
				if (wioska.spr(nowa)) {
					//System.out.println("tutaj sprawdza pierwszy raz");
					//System.out.println("nowa = "+nowa.get_wsp().get_wsp()+" wioska = "+wioska.get_wsp().get_wsp()+ " bool ="+ wioska.spr(nowa));
					//System.out.println(wioska.polaczone.size());
					ataki.add(new Atak(nowa, planowany, wioska, typ));
					//System.out.println("w p�tli do while = "+wioska.polaczone.size());
				}}while(wioska.polaczone.size() < wioska.max_pol && !nowa.equals(new Wioska("000|000",1)));
		}
		return ataki;
	}
	
	static void wypisz_ataki(ArrayList<Atak> ataki) throws FileNotFoundException {
		String str = "";
		for (int i = 0; i < ataki.size(); i++) {
			
			str += ataki.get(i).cel.get_wsp().get_wsp()+"|"+ataki.get(i).pochodzenie.get_wsp().get_wsp()+"|"+ataki.get(i).get_time(true,true)+"|"+ataki.get(i).typ+";";
		System.out.println("Cel = "+ataki.get(i).cel.get_wsp().get_wsp()+" Poch = "+ataki.get(i).pochodzenie.get_wsp().get_wsp()+" Czas = "+ataki.get(i).get_time(true,true)+" Typ = "+ataki.get(i).typ+" pol = "+ataki.get(i).pochodzenie.polaczone.size()+" max_pol = "+ataki.get(i).pochodzenie.max_pol+" pol = "+ataki.get(i).cel.polaczone.size()+" max_pol = "+ataki.get(i).cel.max_pol);
		//System.out.println(ataki.get(i).cel.get_wsp().get_wsp()+"|"+ataki.get(i).pochodzenie.get_wsp().get_wsp()+"|"+ataki.get(i).get_time(true,true)+"|"+ataki.get(i).typ+";");
		}	
		PrintWriter zapis = new PrintWriter("ataki.txt");
		zapis.println(str);
		zapis.close();
	}
	
}

