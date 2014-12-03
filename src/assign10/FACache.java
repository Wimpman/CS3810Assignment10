package assign10;

import java.util.ArrayList;

/**
 * Fully Associative Cache used to insert items in a cache and calculate the hit or miss rate
 * @author Justin
 *
 */
public class FACache extends Cache{
	
	// Array of FARow's
	private ArrayList <FARow> Cache;
	private int blockSize;
	private int[] LRU;
	public int lastRow = 0;
	
	/**
	 * constructor to create the Fully Associative Cache.
	 * @param rows
	 * @parm blocksize in bits
	 */
	public FACache(int rows, int blockSize)
	{
		this.blockSize = blockSize;
		LRU = new int[rows];
		for(int i = 0; i < LRU.length; i++)
			LRU[i] = -1;
		Cache = new ArrayList<FARow> (rows);
		for (int i = 0; i < rows; i++)
		{
			FARow temp = new FARow();
			Cache.add(temp);
		}
		
	}
	
	/**
	 * Updates the LRU's of the table
	 * @param n
	 */
	private void updateLRU(int n)
	{
		for (int i = 0; i < LRU.length; i++)
		{
			if (LRU[i] < n && i != lastRow && LRU[i] != -1)
			{
				LRU[i]++;
			}
		}
	}
	
	/**
	 * Checks to see if the address is a hit or a miss
	 * @param returns true if hit false if miss
	 */
	@Override
	public boolean check(int n) {
		String binary = convertToBinary(n);
		String tag = binary.substring(0, 13);
		String offset = binary.substring(13, 16);
		lastRow = 0;
		for (FARow temp: Cache)
		{
			if (temp.valid == true)
			{
				if (temp.tag == Integer.parseInt(tag, 2))
				{
					int last = LRU[lastRow];
					LRU[lastRow] = 0;
					updateLRU(last);
					return true;
				}
			}
			else
			{
				temp.tag= Integer.parseInt(tag, 2);
				temp.valid = true;
				LRU[lastRow] = 0;
				updateLRU(LRU.length);
				return false;
				
			}
			lastRow++;
		}
		for (int i = 0; i < LRU.length; i++)
		{
			if (LRU[i] == LRU.length - 1)
			{
				FARow temp = Cache.get(i);
				temp.tag= Integer.parseInt(tag, 2);
				temp.valid = true;
				LRU[i] = 0;
				lastRow = i;
				updateLRU(LRU.length);
				return false;
			}
		}
		
		return false;
	}

	/**
	 * Only call this to get the last row of what was called previously.  This will not find the row in the table
	 */
	@Override
	public int row(int n) 
	{
		return lastRow;
	}
}

