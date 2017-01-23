/*
*Check if a proposition is a tautology.
@author Group8
*/
package checker;
import java.util.ArrayList;

public class Tautology {

	public static void main(String[] args){
		new Tautology(args);

	}
	Tautology(String[] args){

		for (String s : args){
			final FormulaParser parser = new FormulaParser(s);         // Create a parser for String s.
			final Formula    p = parser.parse();                // Parse all of s into formula p.
			final int n =    parser.numberOfAtoms();        // After parsing, number of atoms is known. 

			array.clear();
			createArg(new boolean[n], n);

			boolean result = true;

			for(boolean[] a : array) {
				boolean b = p.eval(a);
				if(b == false) result = false;

			}

			System.out.println("Formula " + p + " is" + (result ? "" : "n't")+ "a tautology");


		}
	}
	private ArrayList<boolean[]> array=new ArrayList<boolean[]>();
	public void createArg(boolean[] a, int n){
		if(n==0){
			array.add(a);
			return;
		}
		boolean[] a1 = a.clone();
		boolean[] a2 = a.clone();
		a1[n-1] = true;
		a2[n-1] = false;

		createArg(a1, n - 1);
		createArg(a2, n-1);
	}
}
