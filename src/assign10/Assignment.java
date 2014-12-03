package assign10;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Assignment {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		int[] addresses = new int[22];
		int number = 16;
		addresses[0] = number;
		for (int i = 1;i <= 5; i++)
		{
			number += 4;
			addresses[i] = number;
		}
		number += 24;
		addresses[6] = number;
		number += 4;
		addresses[7] = number;
		number -= 8;
		addresses[8] = number;
		for (int i = 9;i <= 13; i++)
		{
			number += 4;
			addresses[i] = number;
		}
		number += 16;
		addresses[14] = number;
		for (int i = 15;i <= 19; i++)
		{
			number += 4;
			addresses[i] = number;
		}
		number += 24;
		addresses[20] = number;
		number += 4;
		addresses[21] = number;
		FACache(addresses);
		DMCache(addresses);
		
	}
	
	/**
	 * 
	 * @param addreses
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void FACache(int[] addreses) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("Full-Assoc Cache.txt", "UTF-8");
		writer.println("Fully Associative Cache with 8 rows and 8 bytes per row:");
		writer.println("Offset address bits: 3");
		writer.println("Bits in the valid bit: 1");
		writer.println("Bits in the data block: 64");
		writer.println("Bits in the tag:   13");
		writer.println("Bits in the LRU: 3");
		writer.println("Total bits used: 648, bits remaining: 252");
		int hitTime, missTime;
		hitTime = 1;
		missTime = 26;
		int cycleTime = 0;
		FACache cache = new FACache(8,64);
		writer.println("Hit time: 1");
		writer.println("Miss time: 26");
		
		for (int i = 0; i < addreses.length; i++)
		{
			writer.print("Accessing: " + addreses[i] + "(tag"+Integer.parseInt(cache.computeTag(cache.convertToBinary(addreses[i]), 13), 2) + "): ");
			if (cache.check(addreses[i]))
			{
				writer.print("hit from row" + cache.row(addreses[i])+ "\n");
				cycleTime++;
			}
			else
			{
				writer.print("miss - cached to row " + cache.row(addreses[i])+ "\n");
				cycleTime += 26;
			}
		}
		writer.println("Cost in cycles for this repetition: " + cycleTime);
		double CPI = cycleTime/addreses.length;
		writer.println("Average CPI: " + CPI);
		
		writer.close();
	}
	
	/**
	 * 
	 * @param addreses
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public static void DMCache(int[] addreses) throws FileNotFoundException, UnsupportedEncodingException
	{
		PrintWriter writer = new PrintWriter("D-mapped Cache.txt", "UTF-8");
		writer.println("Direct-Mapped Cache with 8 rows and 8 bytes per row:");
		writer.println("Offset address bits: 3");
		writer.println("Bits in the valid bit: 1");
		writer.println("Bits in the data block: 64");
		writer.println("Bits in the tag:	10");
		writer.println("Total bits used: 600, bits remaining: 300");
		int hitTime, missTime;
		hitTime = 1;
		missTime = 26;
		int cycleTime = 0;
		DirectMappedCache cache = new DirectMappedCache(8,64);
		writer.println("Hit time:	1");
		writer.println("Miss time:	26");
		
		for (int i = 0; i < addreses.length; i++)
		{
			writer.print("Accessing: " + addreses[i] + "(tag"+Integer.parseInt(cache.computeTag(cache.convertToBinary(addreses[i]), 13), 2) + "): ");
			if (cache.check(addreses[i]))
			{
				writer.print("hit from row" + cache.row(addreses[i])+ "\n");
				cycleTime++;
			}
			else
			{
				writer.print("miss - cached to row " + cache.row(addreses[i])+ "\n");
				cycleTime += 26;
			}
		}
		writer.println("Cost in cycles for this repetition: " + cycleTime);
		double CPI = cycleTime/addreses.length;
		writer.println("Average CPI: " + CPI);
		
		writer.close();
	}


}
