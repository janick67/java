
public class Jednostki {
	public static int[] czasy_jednostek = {18,22,18,18,9,10,10,11,35,30,10,35};

	public static final int PIK = 0;
	public static final int MIECZ = 1;
	public static final int TOPOR = 2;
	public static final int LUK = 3;
	public static final int ZWIAD = 4;
	public static final int LK = 5;
	public static final int MLUK = 6;
	public static final int CK = 7;
	public static final int TARAN = 8;
	public static final int KATA = 9; 
	public static final int RYCERZ = 10;
	public static final int SZLACHCIC = 11;
	
	public static String czas_jednostki(int jednostka, double odl) {
		//System.out.println("jed = "+jednostka+ " odl = "+odl);
		String str = "";
		double czas = czasy_jednostek[jednostka] * odl;
		int hour = (int) Math.floor(czas/60);
		int min = (int)  Math.floor(czas-hour*60);
		//System.out.println(czas);
		int sec = (int) Math.round((czas-Math.floor(czas))*60);
		str = hour+":"+min+":"+sec;
		//System.out.println("trwanie123 = " +str);
		return str;
	}
	
	public static String czas_jednostki(int jednostka) {
		return czas_jednostki(jednostka, 1);
		}
}
