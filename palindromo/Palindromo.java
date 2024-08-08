import java.util.Scanner;

public class Palindromo {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();

		while (!input.equals("FIM")) {
			System.out.println(isPalindromo(input) ? "SIM" : "NAO");
			input = sc.nextLine();
		}

		sc.close();
	}

	public static boolean isPalindromo(String str)
	{
		int esq = 0;
		int dir = str.length() - 1;
		boolean ret = true;

		while (esq < dir)
			if (str.charAt(esq++) != str.charAt(dir--))
				ret = false;
		return ret;
	}
}
