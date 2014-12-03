package assign10;

import java.util.ArrayList;

public class DirectMappedCache extends Cache{
	
	int[]Cache;
	int blockSize;
	/**
	 * Constructor to create a DirectMappedCache
	 * @param rows
	 * @param blockSize
	 */
	public DirectMappedCache(int rows, int blockSize)
	{
		Cache = new int[rows];
		for (int i = 0; i < Cache.length; i++)
			Cache[i] = -1;
		this.blockSize = blockSize;
		
	}
	/**
	 * Checks a hit or a miss returns true if hit false if miss
	 */
	@Override
	public boolean check(int n) {
		String binary = convertToBinary(n);
		String tagString = binary.substring(0, 10);
		int tag = Integer.parseUnsignedInt(tagString, 2);
		String rowString = binary.substring(10, 13);
		int row = Integer.parseInt(rowString, 2);
		String offsetString =binary.substring(13, 16);
		int offset = Integer.parseInt(offsetString, 2);
		
		if (Cache[row] == tag + row)
			return true;
		else
		{
			Cache[row] = tag + row;
			return false;
		}
	}

	/**
	 * returns the row the number should reside on
	 */
	@Override
	public int row(int n) {
		
		return Integer.parseInt(convertToBinary(n).substring(10, 13), 2);
	}

}
