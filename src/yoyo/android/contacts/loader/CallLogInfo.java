package yoyo.android.contacts.loader;

import java.util.Date;
import android.provider.CallLog;

public class CallLogInfo {
	public CallLogInfo() {
		amountOfCalls = amountOfIncomCalls = amountOfOutcomCalls = 0;
		amountOfDuration = amountOfIncomeDuration = amountOfOutcomeDuration = 0;
		lastCallDateTime = new Date(0);
	}
	public void aggregate(String phoneNumber, String callType, Date callDateTime, Long callDuration) {
		amountOfCalls++;
		amountOfDuration += callDuration;
		if (callDateTime.after(lastCallDateTime)) recentNumber =  phoneNumber;
		switch (Integer.parseInt(callType)){
		case CallLog.Calls.OUTGOING_TYPE:
			amountOfOutcomCalls++;
			amountOfOutcomeDuration += callDuration;
			if (callDateTime.after(lastCallDateTime)) missedCalls = 0;
			break;
		case CallLog.Calls.INCOMING_TYPE:
			amountOfIncomCalls++;
			amountOfIncomeDuration += callDuration;
			if (callDateTime.after(lastCallDateTime)) missedCalls = 0;
			break;
		case CallLog.Calls.MISSED_TYPE:
			if (callDateTime.after(lastCallDateTime)) missedCalls++;
		}
		if (callDateTime.after(lastCallDateTime)) lastCallDateTime = callDateTime;
	}
	Date lastCallDateTime;
	int amountOfCalls;
	int amountOfIncomCalls;
	int amountOfOutcomCalls;
	long amountOfDuration;
	long amountOfIncomeDuration;
	long amountOfOutcomeDuration;
	int missedCalls; // 回电话后就没有了（0）。
	public String recentNumber;
}
