import java.io.*;
import java.util.*;


public class Tracking {

	private String statusCode;
	private int trackingNumber;
	private Range range;
	
	public Tracking()
	{
		
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
		if(trackingObj1.statusCode.equals(trackingObj1.statusCode) && trackingObj2.trackingNumber  == trackingObj2.trackingNumber)
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
			for(int j = 0; j < minimalTrackingList.size(); j++)
			{
			if(isStatusCodeAndTrackingNumSame(trackingInputList.get(i), minimalTrackingList.get(j)))
			{
				if(trackingInputList.get(i).range.isMergeRequired(minimalTrackingList.get(j).range))
				{
					trackObj = new Tracking();
					trackObj.range = trackingInputList.get(i).range.merge(trackingInputList.get(i-1).range);
					trackObj.statusCode = trackingInputList.get(i).statusCode;
					trackObj.trackingNumber =  trackingInputList.get(i).trackingNumber;
					minimalTrackingList.add(j, trackObj);
				}
				else
				{
					//minimalTrackingList = placeAtAppropriatePostion(minimalTrackingList, trackingInputList.get(i));
					
				}
			}
			else
			{
				if(trackingInputList.get(i).range.isSplitRequired(minimalTrackingList.get(j).range))
				{
					ArrayList<Range> splitRanges = trackingInputList.get(i).range.split(minimalTrackingList.get(j).range);
					ArrayList<Tracking> trackingAssignedList = assignStatusAndTrackNumberToRange(splitRanges, trackingInputList.get(i).range, minimalTrackingList.get(j).range);
					minimalTrackingList.addAll(j, trackingAssignedList);
				}
				else
				{
					//minimalTrackingList = placeNonSplittedListinMinimal(minimalTrackingList, trackingInputList.get(i));
				}
			}
				
			}
		}
		return minimalTrackingList;
		
	}
	
	private ArrayList<Tracking> assignStatusAndTrackNumberToRange(
			ArrayList<Range> splitRanges, Range range2, Range range3) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String [] args) throws IOException {
		List<Tracking> trackingInputList = readInput("trackingInput");
		
	}
	
}
