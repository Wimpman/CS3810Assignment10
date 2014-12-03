package assign10;

public abstract class Cache {
	
	
	public abstract boolean check(int n);
	
	public abstract int row(int n);
	
	
	/**
	 * Used to convert the number to binary
	 * @param n
	 * @return
	 */
	public String convertToBinary(int n)
	{
		String result = "";
		// convert the number into binary
		while (n > 0)
		{
			int bit = n % 2;
			n = n / 2;
			result = bit + result;
		}
		// add the leading 0's to get to 16 bits
		if (result.length() < 16)
		{
			for (int i = result.length(); i < 16; i++)
			{
				result = "0" + result;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @param binary
	 * @return
	 */
	public String computeTag(String binary, int tagSize)
	{
		return binary.substring(0, tagSize);
	}

}
