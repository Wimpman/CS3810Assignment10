package assign10;

/**
 * Wrapper class to hold a Fully Associative Cache row If values are -1 they have never been used
 * @author Justin
 *
 */
public class FARow {
	
	
	public boolean valid;
	public int tag;
	public int block;
	public int LRU;
	
	public FARow()
	{
		valid = false;
		tag = -1;
		block = -1;
		LRU = -1;
	}
}
