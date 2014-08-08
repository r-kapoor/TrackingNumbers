import java.util.ArrayList;


public class Range {
	
	private int start;
	private int end;
	
	public Range(int start, int end)
	{
		this.start = start;
		this.end = end;
	}
	
	public String toString()
	{
		return "["+start + ":"+end+"]";
	}
	
	public boolean isLesserRangeThan(Range range)
	{
		if(this.end < range.start)
		{
			return true;
		}
		return false;
	}
	public boolean isMergeRequired(Range range2)
	{
		if(this.end == range2.start - 1 || range2.end == this.start - 1)
		{
			return true;
		}
		else if(isOverlapping(range2) || isSubrange(range2))
		{
			return true;
		}
		return false;
	}
	
	public boolean isSplitRequired(Range range)
	{
		if(isTwoWaySplit(range) || isThreeWaySplit(range))
		{
			return true;
		}
		if(isOverlapping(range))
		{
			return true;
		}
		return false;
	}
	
	private boolean isSubrange(Range range) {
		if(range.end <= this.end && range.start >= this.start)
		{
			return true;
		}
		return false;
	}

	private boolean isOverlapping(Range range) {
		if(!isSubrange(range) && !range.isSubrange(this))
		{
			if((this.end >= range.start && this.end <= range.end) || (range.end >= this.start && range.end <= this.end))
			{
				return true;
			}
		}
		return false;
	}

	public boolean isThreeWaySplit(Range range2)
	{
		if(this.start < range2.start && this.end > range2.end)
		{
			return true;
		}
		return false;
	}
	
	public boolean isTwoWaySplit(Range range2)
	{
		if((this.start < range2.start && this.end == range2.end) || (this.start == range2.start && this.end > range2.end))
		{
			return true;
		}
		return false;
	}
	
	public Range merge(Range range2)
	{
		Range range = new Range(Math.min(this.start,range2.start), Math.max(this.end, range2.end));
		return range;
	}
	
	public ArrayList<Range> split(Range range)
	{
		if(isTwoWaySplit(range))
		{
			return twoWaySplit(range);
		}
		if(isThreeWaySplit(range))
		{
			return threeWaySplit(range);
		}
		if(isOverlapping(range))
		{
			return overlapSplit(range);
		}
		return null;
	}
	
	private ArrayList<Range> overlapSplit(Range range) {
		ArrayList<Range> splitted = new ArrayList<Range>();
		if(this.start < range.start)
		{
			splitted.add(new Range(this.start,range.start - 1));
			splitted.add(new Range(range.start,range.end));
		}
		else
		{
			splitted.add(new Range(range.start, range.end));
			splitted.add(new Range(range.end+1,this.end));
		}
		return splitted;
	}

	public ArrayList<Range> twoWaySplit(Range range)
	{
		ArrayList<Range> splitted = new ArrayList<Range>();
		if(this.end == range.end)
		{
			splitted.add(new Range(Math.min(this.start,range.start),Math.max(this.start, range.start) - 1));
			splitted.add(new Range(Math.max(this.start, range.start), this.end));
		}
		else
		{
			splitted.add(new Range(this.start,Math.min(this.end, range.end)));
			splitted.add(new Range(Math.min(this.end, range.end)+1, this.end));
		}
		return splitted;
	}
	
	public ArrayList<Range> threeWaySplit(Range range)
	{
		ArrayList<Range> splitted = new ArrayList<Range>();
		splitted.add(new Range(this.start,range.start - 1));
		splitted.add(new Range(range.start, range.end));
		splitted.add(new Range(range.end + 1, this.end));
		return splitted;
	}
	
	public static void main(String args[])
	{
		Range range1 = new Range(1, 100);
		Range range2 = new Range(120, 150);
		Range range3 = new Range(101, 200);
		Range range4 = new Range(130, 200);
		Range range5 = new Range(1,300);
		System.out.println(range1);
		System.out.println(range1.isMergeRequired(range2));
		System.out.println(range1.isMergeRequired(range3));
		System.out.println(range1.isMergeRequired(range4));
		System.out.println(range5.isSplitRequired(range1));
		System.out.println(range5.split(range1));
		System.out.println(range4.isSplitRequired(range2));
		System.out.println(range4.split(range2));
	}

}
