public class LeakyBucket {
 static int min(int x,int y) {
 if(x < y)
 return x;
 else
 return y;
 }
 public static void main(String args[]) {
 int i, process = 2, cap = 5, count = 0, drop = 0;
 int inp[] = {4,5,3,2,6,7,2,3};
 System.out.println("Time\tInput\tOutput\tCount\tDrop");
 for (i = 0; i < inp.length; i++) {
 count += inp[i];
 if(count > cap) {
 drop = count - cap;
 count = cap;
 }
 System.out.print(i + 1);
 System.out.print("\t\t" + inp[i]);
 int mini = min(count, process);
 System.out.print("\t\t" + mini);
 count -= mini;
 System.out.print("\t\t" + count);
 System.out.print("\t\t" + drop);
 drop = 0;
 System.out.println();
 }
 for (; count != 0; i++) {
 if(count > cap) {
 drop = count - cap;
 count = cap;
 }
 System.out.print(i + 1);
 System.out.print("\t\t0");
 int mini = min(count, process);
 System.out.print("\t\t" + mini);
 count -= mini;
 System.out.print("\t\t" + count);
 System.out.print("\t\t" + drop);
 System.out.println();
 }
 }
}
