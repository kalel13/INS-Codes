import java.util.*;
class Prog5
{
	public static double Alice(int p, int g, double inp)
	{
		double x = 0;
		int a = 0;
		System.out.println("// ALICE //");
		if(inp == -1)
		{
			Scanner src = new Scanner(System.in);
			System.out.println("Enter a: ");
			a = src.nextInt();
			x = Math.pow(g,a)%p;
			System.out.println("Ra : "+x);
			//Bob(p, g, x);
		}
		else
			x = Math.pow(inp,a)%p;
		
		return x;
	}
	
	public static double Bob(int p, int g, double inp)
	{
		double x = 0;
		int b = 0;
		System.out.println("// BOB //");
		if(inp == -1)
		{
			Scanner src = new Scanner(System.in);
			System.out.println("Enter b: ");
			b = src.nextInt();
			x = Math.pow(g,b)%p;
			System.out.println("Rb : "+x);
			//Alice(p, q, x);
		}
		else
			x = Math.pow(inp,b)%p;
		
		return x;
	}
	public static int generate(int p)
	{
		
		double gen[][] = new double[p][p];
		for(int i=2; i<p; i++)
		{
			for(int j=1; j<p; j++)
				gen[i][j] = (Math.pow(i,j))%p;
		}
		
		for(int i=2; i<p; i++)                                                                                            
		{
			for(int j=1; j<p; j++)
				System.out.print((int)gen[i][j]+"   ");
			System.out.println();
			System.out.println();
		}
		
		int flag = 0;
		int generator = 0;
		int count[] = new int[p];
		for(int x=0; x<count.length; x++)
			count[x] = 0;
		int i=0, j=0;
		for(i=2; i<p; i++)
		{
			for(j=1; j<p; j++)
				count[(int)gen[i][j]]++;
		
			 m:for(int k=1; k<p; k++)
				if(count[k] == 1)
					flag = 0;
				else
				{
					flag = 1;
					break m;
				}
			for(int x=0; x<count.length; x++)
				count[x] = 0;
		
			if(flag == 1)
				continue; 
		
			if(flag == 0)
			{ 
				generator = i;
				break;
			}
			
		}
		return generator;
	}
	public static void main(String args[])
	{
		Scanner src = new Scanner(System.in);
		System.out.println("Enter prime no (p): ");
		int p = src.nextInt();
		int g = generate(p);
		
		System.out.println("Generator (g): "+g);
		
		//int b = src.nextInt();
		double Ra = Alice(p, g, -1);
		double Rb = Bob(p, g, -1);
		
		double Ka = Alice(p, g, Rb);
		double Kb = Bob(p, g, Ra);
		
		System.out.println("Ka : "+Ka+"\nKb : "+Kb);
	}
}