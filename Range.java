import java.util.ArrayList;


public class Range {
	
	private int start;
	private int end;
	
	public Range(int start, int end)
	{
		this.start = start;
		this.end = end;
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
	
	private boolean isSubrange(Range range) {
		if(range.end <= this.end && range.start >= this.start)
		{
			return true;
		}
		return false;
	}

	private boolean isOverlapping(Range range) {
		if(this.end <= range.start)
		{
			return true;
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
		if(this.start < range2.start && this.end == range2.end || this.start == range2.start && this.end > range2.end)
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
	
	public ArrayList<Range> twoWaySplit(Range range)
	{
		ArrayList<Range> splitted = new ArrayList<Range>();
		splitted.add(new Range(Math.min(this.start,range.start),Math.max(this.start, range.start) - 1));
		splitted.add(new Range(Math.max(this.start, range.start), this.end));
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

}
