import java.util.*;
import java.lang.*;
class Knapsack
{
	public static int isPrime(int n)
	{
		int count = 0;
		for(int i=1; i<=n; i++)
			if(n%i==0)
				count++;
		
		if(count==2)
			return 1;
		else 
			return 0;
	}
	
	public static char[] convertBinary(int n, int sk)
	{
		String b = "";
		int a = 0;
		while(n>0)
		{
			a = n%2;
			b = b+""+a;
			n=n/2;		
		}
		int l = sk - b.length();
		for(int i = 0; i<l; i++)
			b=b+"0";
		//System.out.println("\n"+b);
		char temp[] = b.toCharArray();
		char bin[] = new char[temp.length];
		
		for(int i=temp.length-1, j=0; i>=0; i--, j++)
			bin[j] = temp[i];
		//System.out.println(Arrays.toString(bin));
		
		return bin;
	}


	public static void main(String args[])
	{
		Scanner src = new Scanner(System.in);
		int sk[] = {2,3,7,14,30,57,120,251}; //SuperIncreasing Knapsack//
		int gk[] = new int[sk.length];
		int sum=0, flag1 = 0, flag2 = 0, flag3 = 0, nextPrime = 0, minv = 1,plaintext = 0, cipher1 = 0, pl2 = 0;
		
		int m = 3; // CoPrime w.r.t "nextPrime" //
		
		for(int i=0; i<sk.length; i++)
			sum+=sk[i];
		
		System.out.println("SuperIncreasing Knapsack : "+Arrays.toString(sk));
		System.out.println("Sum of SuperIncreasing Knapsack : "+sum);
		
		// Cacluating next prime number after sum (n)//
		do
		{
			sum++;
			if(isPrime(sum)==1)
			{
				nextPrime = sum;
				flag1 = 1;
			}
			else
				flag1 = 0;
		}while(flag1!=1);
		
		System.out.println("Next Prime to sum is : "+nextPrime);
		
		// Calculating m inverse //
		do
		{
			if((m*minv)%nextPrime==1)
				flag2 = 1;
			else
			{
				flag2 = 0;
				minv++;
			}	
		}while(flag2!=1);
		
		System.out.println("m : "+m+"\nm inverse : "+minv);  
		
		// Forming General Knapsack //
		for(int i=0; i<gk.length; i++)
			gk[i] = (sk[i]*m)%nextPrime;
		
		System.out.println("General Knapsack : "+Arrays.toString(gk));
		
		
		// Encryption //
		System.out.println("ENCRYPTION");
		
		int err = 0;
		do
		{
			System.out.println("Enter plain-text : ");
			plaintext = src.nextInt();
			if(plaintext >= Math.pow(2,sk.length))
			{
				System.out.println("Plaintext is not from valid range! It should range between 0 & "+(Math.pow(2,sk.length)-1));
				err = 1;
			}
			else
				err = 0;
		}
		while(err==1);
			
		
		char bin[] = new char[sk.length];
		bin = convertBinary(plaintext,sk.length); // Function to convert given number to binary. Refer function. //
		
		System.out.println("Binary : "+Arrays.toString(bin));
		
		// Encrypting cipher using binary number & General Knapsack //
		for(int i=0; i<bin.length; i++)
			if(bin[i]=='1')
				cipher1 += gk[i];
			
		System.out.println("Cipher Text : "+cipher1);
		
		// Decryption //
		System.out.println("DECRYPTION");
		
		System.out.println("Enter Cipher Text : ");
		int cipher2 = src.nextInt();
		
		// Calculating "M" required for decrypting plaintext. M = (C*minv)mod(n) //
		pl2 = (cipher2*minv)%nextPrime;
		//System.out.println("No for decryption : "+pl2);
		
		// Decrypting using M from SuperIncreasing Knapsack //
		int newsum = 0;
		int dbin[] = new int[sk.length];
		int c = sk.length-1;
		int t = pl2;
		
		//Logic of adding numbers which should be equal to M //
		l:for(int i=sk.length-1; i>=0; i--)
		{
			if(sk[i]<=pl2)
			{
				newsum+=sk[i];
				pl2 -= sk[i];
				dbin[i] = 1;
				if(newsum == t)
					break l;
			}
			else
			{
				dbin[i] = 0;
				continue;
			}
		}
			
		//System.out.println("Decrypted Binary : "+Arrays.toString(dbin));	
		
		int aureknewsum = 0;
		for(int i=sk.length-1,k=0; i>=0; i--,k++)
			if(dbin[i]==1)
				aureknewsum += Math.pow(2,k);
		System.out.println("Decrypted Message : "+aureknewsum);
	}
}