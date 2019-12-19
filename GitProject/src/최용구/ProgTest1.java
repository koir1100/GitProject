package 최용구;

public class ProgTest1 {
	public static int cVar = 0;
	String[] iStr = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	double iDouble;
	boolean iBool = false;
	char iChar = 'a';

	String getGugudan(int dan) {
		String result = "";
		result = dan + "단 :\r\n";
		for (int i = 1; i <= 9; i++)
			result += dan + "*" + i + "=" + dan * i + "\t";
		return result;
	}
}