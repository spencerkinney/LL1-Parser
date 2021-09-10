import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LL1 {

	List<String> tokens = new ArrayList<String>();
	List<String> temp_tokens;
	List<String> TARGET_ARRAY = new ArrayList<String>();

	public LL1(String expression) {
		tokenize(expression);
		TARGET_ARRAY.add("E");
	}

	public void tokenize(String expression) {
		StringTokenizer st = new StringTokenizer(expression, "*/-+()", true);
		while (st.hasMoreTokens()) {
			tokens.add(st.nextToken());
		}
		temp_tokens = tokens;
	}

	public void T() {
		for (int i = 0; i < tokens.size(); i++) {
			try {
				Double.parseDouble(tokens.get(i));
				temp_tokens.set(i, "T");
			} catch (NumberFormatException e) {
			}
		}
	}

	public void E() {
		for (int i = 0; i < temp_tokens.size() - 2; i++) {
			if ((temp_tokens.get(i).equals("T") || temp_tokens.get(i).equals("E"))
					&& (temp_tokens.get(i + 1).equals("+") || temp_tokens.get(i + 1).equals("-")
							|| temp_tokens.get(i + 1).equals("*") || temp_tokens.get(i + 1).equals("/"))
					&& (temp_tokens.get(i + 2).equals("T") || temp_tokens.get(i + 2).equals("E"))) {

				temp_tokens.set(i, "E");
				temp_tokens.remove(i + 1);
				temp_tokens.remove(i + 1);
			}
		}
	}

	public void EP() {
		for (int i = 0; i < temp_tokens.size() - 2; i++) {
			if (temp_tokens.get(i).equals("(")
					&& (temp_tokens.get(i + 1).equals("E") || temp_tokens.get(i + 1).equals("T"))
					&& temp_tokens.get(i + 2).equals(")")) {

				temp_tokens.set(i, "E");
				temp_tokens.remove(i + 1);
				temp_tokens.remove(i + 1);
			}
		}
	}

	public void algorithm() {
		T();
		for (int i = 0; i < temp_tokens.size() / 2; i++) {
			E();
			EP();
			if (temp_tokens.equals(TARGET_ARRAY)) {
				System.out.println("valid");
				return;
			}
		}
		if (!temp_tokens.equals(TARGET_ARRAY)) {
			System.out.println("invalid");
			return;
		}
	}

	public void printTokens() {
		System.out.println(tokens);
	}

	public static void main(String[] args) {
		if(args.length == 0 || args[0].length() < 3) {
			System.out.println("You must provide a valid expression as an argument");
			System.exit(1);
		}
		String input = args[0];
		input.replaceAll("\\s+","");
		LL1 parse = new LL1(input);
		parse.algorithm();
	}
}