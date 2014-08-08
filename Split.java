import java.util.ArrayList;
import java.util.List;

class ValueDenomination
{
	public int value;
	public int denominationValue;
	
	public ValueDenomination(int value, int denominationValue )
	{
		this.value = value;
		this.denominationValue = denominationValue;
	}
	
	public String toString()
	{
		return "["+this.value + "," + this.denominationValue +"]";
	}
}
public class Split {
	public static ArrayList<ValueDenomination> splitInto(int value, List<Integer> denominations)
	{
		if(denominations.size() == 0)
		{
			return null;
		}
		if(denominations.size() == 1)
		{
			ArrayList<ValueDenomination> valueDenoms = new ArrayList<ValueDenomination>();
			valueDenoms.add(new ValueDenomination(value / denominations.get(0), denominations.get(0)));
			return valueDenoms;
		}
		if(value % denominations.get(0) != 0)
		{
			ArrayList<ValueDenomination> valueDenoms =  splitInto(value % denominations.get(0), denominations.subList(1, denominations.size()));
			valueDenoms.add(new ValueDenomination(value / denominations.get(0), denominations.get(0)));
			return valueDenoms;
		}
		return null;
		
	}
	public static void main(String args[])
	{
		ArrayList<Integer> denoms = new ArrayList<Integer>();
		denoms.add(10000);
		denoms.add(1000);
		denoms.add(100);
		denoms.add(1);
		ArrayList<ValueDenomination> splitDenoms = splitInto(3213, denoms);
		System.out.println(splitDenoms);
		
		ArrayList<Integer> al = new ArrayList<Integer>();
	}

}
