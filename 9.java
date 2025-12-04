import java.util.*;
public class rsa {
 static int gcd(int a,int b){while(b!=0){int r=a%b;a=b;b=r;}return a;}
 static int powMod(int base,int exp,int mod){
 int res=1; base%=mod;

while(exp>0){if((exp&1)==1)res=(res*base)%mod;base=(base*base)%mod;exp>>=1
;}
 return res;
 }
 public static void main(String[] args){
 Scanner sc=new Scanner(System.in);
 System.out.print("Enter message: ");
 String msg=sc.nextLine();
 System.out.print("Enter p and q (prime): ");
 int p=sc.nextInt(), q=sc.nextInt();
 int n=p*q, phi=(p-1)*(q-1);
 int e; for(e=2;e<phi;e++) if(gcd(e,phi)==1) break;
 int d=0; for(int k=1;k<phi;k++) if((k*e)%phi==1){d=k;break;}
 int len=msg.length();
 int[] enc=new int[len], dec=new int[len];
 for(int i=0;i<len;i++) enc[i]=powMod(msg.charAt(i),e,n);
 System.out.print("Encrypted: ");
 for(int x:enc) System.out.print(x+" ");
 System.out.println();
 for(int i=0;i<len;i++) dec[i]=powMod(enc[i],d,n);
 System.out.print("Decrypted: ");
 for(int x:dec) System.out.print((char)x);
 System.out.println();
 sc.close();
 }
}
