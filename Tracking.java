import java.io.*;
import java.util.*;


public class Tracking {

	private String statusCode;
	private int trackingNumber;
	private Range range;
	
	public Tracking()
	{
		
	}
	public String toString()
	{
		return range + " " + statusCode + " " + trackingNumber;
	}
	
	public Tracking(String statusCode, int trackingNumber, Range range){
		this.statusCode = statusCode;
		this.trackingNumber = trackingNumber;
		this.range = range;
	}
	
	public static List<Tracking> readInput(String filename) throws IOException {
		List<Tracking> trackingList = new ArrayList<Tracking>();
		Scanner sc = new Scanner(new FileReader(filename));
		String currentLine;
		while(sc.hasNext()){
			currentLine = sc.nextLine();
			String[] splitLine = currentLine.split(" ");
			Range range = new Range(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]));
			Tracking tracking = new Tracking(splitLine[2], Integer.parseInt(splitLine[3]), range);
			trackingList.add(tracking);	
		}
		return trackingList;
	}
	
	public boolean isStatusCodeAndTrackingNumSame(Tracking trackingObj1, Tracking trackingObj2)
	{
		if(trackingObj1.statusCode.equals(trackingObj2.statusCode) && trackingObj1.trackingNumber  == trackingObj2.trackingNumber)
			return true;
		return false;
	}
	public List<Tracking> generateMinimalTrackingList(List<Tracking> trackingInputList) {
		Range range;
		List <Tracking> minimalTrackingList = new ArrayList<Tracking>();
		List<Range> rangeList = new ArrayList<Range>();
		Tracking trackObj;
		minimalTrackingList.add(trackingInputList.get(0));
		for(int i = 1; i < trackingInputList.size(); i++){
			System.out.println(i);
			for(int j = 0; j < minimalTrackingList.size(); j++)
			{
				System.out.println(minimalTrackingList);
				//System.out.println(trackingInputList);
				System.out.println(i + ":" + j);
				if(trackingInputList.get(i).range.isLesserRangeThan(minimalTrackingList.get(j).range))
				{
					System.out.println("enters2");
					minimalTrackingList.add(j, trackingInputList.get(i));
					break;
				}
				else if(isStatusCodeAndTrackingNumSame(trackingInputList.get(i), minimalTrackingList.get(j)))
				{
					System.out.println("enters1");
					if(minimalTrackingList.get(j).range.isMergeRequired(trackingInputList.get(i).range))
					{
						System.out.println("enters");
						trackObj = new Tracking();
						trackObj.range = trackingInputList.get(i-1).range.merge(trackingInputList.get(i).range);
						trackObj.statusCode = trackingInputList.get(i).statusCode;
						trackObj.trackingNumber =  trackingInputList.get(i).trackingNumber;
						minimalTrackingList.add(j, trackObj);
						break;
					}
				}
				else
				{
					if(minimalTrackingList.get(j).range.isSplitRequired(trackingInputList.get(i).range))
					{
						ArrayList<Range> splitRanges = minimalTrackingList.get(j).range.split(trackingInputList.get(i).range);
						ArrayList<Tracking> trackingAssignedList = assignStatusAndTrackNumberToRange(splitRanges, trackingInputList.get(i).range, minimalTrackingList.get(j).range);
						minimalTrackingList.addAll(j, trackingAssignedList);
						break;
					}
				}
			}
		}
		return minimalTrackingList;
		
	}
	
	private static ArrayList<Tracking> assignStatusAndTrackNumberToRange(ArrayList<Range> splitRanges, Tracking trackingObj1, Tracking trackingObj2) {
		ArrayList<Tracking> trackingAssignedList = new ArrayList<Tracking>();
		Tracking tracking = new Tracking();
		
		for(int i = 0; i < splitRanges.size(); i++){
			if(trackingObj1.range.isSubrange(splitRanges.get(i)))
			{
				tracking = new Tracking(trackingObj1.statusCode, trackingObj1.trackingNumber, splitRanges.get(i));
			}
			if(trackingObj2.range.isSubrange(splitRanges.get(i)))
			{
				tracking = new Tracking(trackingObj2.statusCode, trackingObj2.trackingNumber, splitRanges.get(i));
			}
			trackingAssignedList.add(tracking);
		}
 		return trackingAssignedList;
	}
	
	public static void main(String [] args) throws IOException {
		List<Tracking> trackingInputList = readInput("trackingInput");
		//System.out.println(trackingInputList);
		Tracking tracking = new Tracking();
		List<Tracking> minimalTrackingList = tracking.generateMinimalTrackingList(trackingInputList);
		System.out.println("Ans:"+minimalTrackingList);
	}
	
}
