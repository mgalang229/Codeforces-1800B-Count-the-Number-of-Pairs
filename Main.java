import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

1
11 2
aAaaBACacbE

a b c d e f g h i j k l m n o p q r s t u v w x y z 
4 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
2 1 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

aaaaa
AA

aaaa
AAA

b
B

c
C

?
E

-----------------------------------

1
5 3
cbccb

a b c d e f g h i j k l m n o p q r s t u v w x y z 
0 2 3 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0

bb
?

b
B

ccc
?

cc
C

freq[lower] -= min(freq[lower], freq[upper])
freq[upper] -= min(freq[lower], freq[upper])

added = min((freq[lower] + freq[upper]) / 2, k)
k -= added

-----------------------------------

1
12 3
bbbBBBBBBBBB

bbb
BBBBBBBBB

bbb
BBB BB BB BB

 */

public class Main {
	
	public static void main(String[] args) {
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int n = fs.nextInt(), k = fs.nextInt();
			char[] s = fs.next().toCharArray();
			int[] lowerFreq = new int[26], upperFreq = new int[26];
			Arrays.fill(lowerFreq, 0);
			Arrays.fill(upperFreq, 0);
			for (char letter : s) {
				if (Character.isLowerCase(letter)) {
					lowerFreq[letter-'a']++;
				} else {
					upperFreq[letter-'A']++;
				}
			}
//			for (int i = 0; i < 26; i++) {
//				System.out.print((char) ((char) 'a' + i) + " ");
//			}
//			System.out.println();
//			for (int x : lowerFreq) {
//				System.out.print(x + " ");
//			}
//			System.out.println();
//			for (int x : upperFreq) {
//				System.out.print(x + " ");
//			}
//			System.out.println();
			long ans = 0;
			for (int i = 0; i < 26; i++) {
				int min = Math.min(lowerFreq[i], upperFreq[i]);
				ans += min;
				lowerFreq[i] -= min;
				upperFreq[i] -= min;
				int added = (lowerFreq[i] + upperFreq[i]) / 2;
				ans += Math.min(added, k);
				k -= Math.min(added, k);
			}
			System.out.println(ans);
		}
		out.close();
	}
	
	static final Random rnd = new Random();
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		double[] readDoubleArray(int n) {
			double[] a = new double[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextDouble();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
