import java.util.Scanner;

public class Debut {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Entrez votre phrase:");
		String phrase = scanner.nextLine();
		
		Huffman huff = new Huffman(phrase);
		huff.start();
	}

}
