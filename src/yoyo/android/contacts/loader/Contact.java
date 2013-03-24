package yoyo.android.contacts.loader;

import java.util.ArrayList;
import java.util.List;

import org.appcelerator.titanium.TiBlob;


public class Contact implements Comparable<Contact> {
	private static final int OUT_IN_RATIO = 2;
	
	public String id;
	public String name;
	public List<String> phones;
	public TiBlob avatar;
	public CallLogInfo callInfo;
	public int rankScore;

	public Contact() {
		phones = new ArrayList<String>();
		callInfo = new CallLogInfo();
	}

	@Override
	public int compareTo(Contact arg) {
		return arg.rankScore - this.rankScore;
	}

	public void updateRankScore() {
		// 暂时简单的做法，没有考虑missedCalls。
		rankScore = callInfo.amountOfOutcomCalls * OUT_IN_RATIO + callInfo.amountOfIncomCalls;
	}
}
