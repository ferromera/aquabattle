package misc;

public class ContadorDeInstancias {
	private static long cuenta=0;
	public final static String TAG_ID="id";
	public static long getId(){
		return cuenta++;
	}
}
