import java.util.*;

class Prog4
{
	public static void encryption(int msg, int e, int n1)
	{
		double n = (double)n1;
		double M = (double)msg;
		M = (Math.pow(M,e))%n;
		System.out.println("C : "+(int)M);
	}
	
	public static void decryption(int msg, int d, int n1)
	{
		int res = 1;
		for (int i = 0; i < (d / 2); i++)
	    	{
	        res *= ((msg * msg) % n1);
	        res %= n1;
		}

	    if (d % 2 == 1)
        	res *=msg;

        res %=n1; 
		
		/* double n = (double)n1;
		double C = (double)msg;
		double M = Math.pow(C,d)%n; */
     
        System.out.println("M : "+res);
	}
	public static int findgcd(int x, int y)
	{
		if(y!=0)
			return findgcd(y,x%y);
		else
			return x;
	}

	public static int[] rsakey(int p, int q)
	{
		int key[] = new int[2];
		int n = p*q;
		int phi = (p-1)*(q-1);
		int flag = 0;
		int temp[] = new int[phi];
		int k=0;
		int c=0;
		
		for(int i=2; i<phi; i++)
		{
			flag = findgcd(i,phi);
			if(flag==1)
			{
				c++;
				temp[k] = i;
				k++;
			}
		}
		
		int e[] = new int[c];
		for(int i=0, l=0; i<temp.length; i++)
		{
			if(temp[i]!=0)
			{
				e[l] = temp[i];
				l++;
			}
			else 
				continue;
		}
		
		int l = 0;
		int d[] = new int[e.length];
		l:for(int i = 0; i<e.length; i++)
		{
			for(int j=0; j<phi; j++)
			{
				if((e[i]*j)%phi==1)
				{
					d[l] = j;
					l++;
					continue l;
				}
			}
		}
		
		int same = 0;
		for(int i=0; i<e.length; i++)
			if(e[i]==d[i])
				same++;
		
		if(same!=e.length)
		{
			int tempE[] = new int[e.length-same];
			int tempD[] = new int[d.length-same];
			int u=0;
			for(int i=0; i<e.length; i++)
				if(e[i]==d[i])
					continue;
				else
				{
					tempE[u] = e[i];
					tempD[u] = d[i];
					u++;
				}

			int diff[] = new int[tempE.length];
			for(int i=0; i<tempE.length; i++)
				diff[i] = Math.abs(tempD[i]-tempE[i]);

			int min = diff[0];
			for(int i=1; i<diff.length; i++)
				if(diff[i]<min)
					min = diff[i];

			for(int i=0; i<diff.length; i++)
				if(min==diff[i])
				{
					key[0] = tempE[i];
					key[1] = tempD[i];
					break;
				}
				else
					continue;

			System.out.println("Selected E : "+key[0]+"\nSelected D : "+key[1]);	
		}
		else
		{
			key[0] = -1;
			key[1] = -1;
		}	
		return key;	
	}

	public static int checkPrimes(int p, int q)
	{
		int c1 = 0, c2 = 0;
		for(int i=1; i<=p; i++)
			if(p%i==0)
				c1++;

		for(int i=1; i<=q; i++)
			if(q%i==0)
				c2++;

		if( c1!=2 || c2!=2 )
			return -1;
		else
			return 1;
	}



	public static void main(String args[])
	{
		Scanner src = new Scanner(System.in);
		int err1 = 0, err2 = 0, err3 = 0, err4 = 0;
		String choice, decision;
		String p, q, msg;
		int pi = 0, qi = 0;
		int choiceint = 0, decint = 0,msgint = 0;
		do
		{
			do
			{
				System.out.println("What do you want to perform?\n1. Encryption \t 2. Decryption \t 3. Exit");
				decision = src.next();
				if(!decision.matches("-?\\d+"))
				{
					err3 = 1;
					System.out.println("Enter integer decision only!");
				}
				else
				{
					decint = Integer.parseInt(decision);
					if(decint>=1 && decint<=3)
					{
						err3 = 0;
						if(decint == 3)
							System.exit(0);
					}
					else
					{
						err3 = 1;
						System.out.println("Enter a valid decsion!");
					}
				}
			}while(err3 == 1);


			do
			{
				do
				{
					System.out.println("Enter 2 prime nos :");
					p = src.next();
					q = src.next();
					if(!p.matches("-?\\d+") || !q.matches("-?\\d+"))
					{
						err1 = 1;
						System.out.println("Enter only integer");
					}
					else
					{
						pi = Integer.parseInt(p);
						qi = Integer.parseInt(q);
						if( pi == qi )
						{
							System.out.println("Enter 2 different numbers!");
							err1 = 1;
						}
						else
						{
							if(checkPrimes(pi, qi) == 1)
								err1 = 0;
							else
							{
								err1 = 1;
								System.out.println("Enter prime numbers only!");
							}
						}
					}
				}while(err1 == 1);

				int n=pi*qi;
				int keys[] = rsakey(pi,qi);
				int phi = (pi-1)*(qi-1);

				if(keys[0]==-1 || keys[0]==-1)
				{
					err2 = 1;
					System.out.println("The pair of prime numbers isn't valid because all the values of E & D are same!");
				}
				else
				{
					System.out.println("Phi : "+phi);
					err2 = 0;
					System.out.println("Public Key : ("+keys[0]+","+n+")");
					System.out.println("Private Key : ("+keys[1]+","+n+")");	

					do
					{
						System.out.println("Enter text : ");
						msg = src.next();
						
						if(!msg.matches("-?\\d+"))
						{
							err4 = 1;
							System.out.println("Enter only integer for text");
						}
						else
						{
							msgint = Integer.parseInt(msg);
							if(msgint<phi)
								err4 = 0;
							else
							{
								System.out.println("Your text's length should be less than PHI!!");
								err4 = 1;
							}
						}
						
					}while(err4 == 1);

					switch(decint)
					{
						case 1: encryption(msgint, keys[0], n);
						break;
						case 2: decryption(msgint, keys[1], n);
						break;
						default:
					}	
				}

			}while(err2==1);
		
			
			do
			{
				System.out.println("Do you want to continue?\n1.Yes\t2.No");
				choice = src.next();
				if(!choice.matches("-?\\d+"))
				{
					err1 = 1;
					System.out.println("Enter only integer");
				}
				else
				{
					choiceint = Integer.parseInt(choice);
					if(choiceint==1 || choiceint==2)
						err1 = 0;
					else
					{
						err1 = 1;
						System.out.println("Enter a valid choice");
					}
				}
			
			}while(err1 == 1);
			
		}while(choiceint==1);	
	}
}