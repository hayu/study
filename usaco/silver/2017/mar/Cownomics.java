package mar;

import java.io.*;
import java.util.*;

public class Cownomics {
	public static void main(String[] args) throws IOException {
		cownomics();
	}

	public static void cownomics() throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")))) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			HashSet<String> spotty = new HashSet<>();
			TreeSet<String> prefixes = null;
			TreeSet<String> suffixes = null;
			for (int m = 0; m < N; m++) {
				String input = br.readLine();
				for (int i = 0; i < M - 3; i++) {
					String current = input.substring(i);
					prefixes = pairs(current, 0, current.length()-2);
					
					int j = 0;
					for (String prefix : prefixes) {
						System.out.println(prefix);
						suffixes = new TreeSet<>();
						for (int k = 0; k < current.length() - j - 2; k++) {
							String remainder = current.substring(prefix.length() + k + j);
							suffixes.addAll(pairs(remainder, 0, remainder.length()));
						}
						System.out.println(prefix + ":"+i+": " + suffixes);
						System.out.println();
						
						for (String suffix : suffixes) {
							spotty.add(prefix + suffix);
						}
						j++;
					}
					
					System.out.println();
				}
			}
			
			System.out.println(spotty);
			System.out.println(spotty.size());
			
			for (int m = 0; m < N; m++) {
				String input = br.readLine();
				for (int i = 0; i < M - 3; i++) {
					String current = input.substring(i);
					prefixes = pairs(current, 0, current.length()-2);
					
					int j = 0;
					for (String prefix : prefixes) {
						System.out.println(prefix);
						suffixes = new TreeSet<>();
						for (int k = 0; k < current.length() - j - 2; k++) {
							String remainder = current.substring(prefix.length() + k + j);
							suffixes.addAll(pairs(remainder, 0, remainder.length()));
						}
						System.out.println(prefix + ":"+i+": " + suffixes);
						System.out.println();
						
						for (String suffix : suffixes) {
							spotty.remove(prefix + suffix);
						}
						j++;
					}
					
				}
			}
			
			System.out.println(spotty);
			System.out.println(spotty.size());
			System.out.println();

		}
	}
	
	private static TreeSet<String> pairs(String input, int idx, int stop) {
		if (input.length() < 2) {
			return new TreeSet<>();
		}
		
		TreeSet<String> rslt = new TreeSet<>();
		for (int i = idx+1; i < stop; i++) {
			rslt.add(input.charAt(idx) + "" + input.charAt(i));
		}
		System.out.println(input + ": " + idx + ", " + stop + ": " + rslt);
		return rslt;
	}
//	public static void cownomics() throws IOException {
//		try (BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
//				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")))) {
//
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int N = Integer.parseInt(st.nextToken());
//			int M = Integer.parseInt(st.nextToken());
//			
//			List<String> prefixes = null;
//			List<String> suffixes = null;
//			String input = "ABCDEFGH";
//			for (int i = 0; i < M - 3; i++) {
//				String current = input.substring(i);
//				prefixes = pairs(current, 0, current.length()-2);
//				
//				for (int j = 0; j < prefixes.size(); j++) {
//					String prefix = prefixes.get(j);
//					System.out.println(prefix);
//					suffixes = new ArrayList<>();
//					for (int k = 0; k < current.length() - j - 2; k++) {
//						String remainder = current.substring(prefix.length() + k + j);
//						suffixes.addAll(pairs(remainder, 0, remainder.length()));
//					}
//					System.out.println(prefix + ":"+i+": " + suffixes);
//					System.out.println();
//				}
//				
//				System.out.println();
//			}
//		}
//	}
//	
//	private static List<String> pairs(String input, int idx, int stop) {
//		if (input.length() < 2) {
//			return Collections.emptyList();
//		}
//		
//		List<String> rslt = new ArrayList<>();
//		for (int i = idx+1; i < stop; i++) {
//			rslt.add(input.charAt(idx) + "" + input.charAt(i));
//		}
//		System.out.println(input + ": " + idx + ", " + stop + ": " + rslt);
//		return rslt;
//	}

}
