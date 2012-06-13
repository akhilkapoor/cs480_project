package com.rec;

import java.util.ArrayList;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpoAdapter extends BaseExpandableListAdapter {
	private Context mContext;
	private ArrayList<String> mGroups;
	private ArrayList<ArrayList<Shift>> mChildren;
        
	public ExpoAdapter(Context context, ArrayList<String> group, ArrayList<ArrayList<Shift>> children) {
		this.mContext = context;
		this.mGroups = group;
		this.mChildren = children;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return mChildren.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
		return mChildren.get(groupPosition).size();
	}

	public TextView getGenericView() {
		// Layout parameters for the ExpandableListView
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 64);

		TextView textView = new TextView(mContext);
		textView.setLayoutParams(lp);
		// Center the text vertically
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		// Set the text starting position
		textView.setPadding(36, 0, 0, 0);
		return textView;
	}

	@Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        Shift data = (Shift) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.childrow, null);
        }
        TextView tvLocation = (TextView) convertView.findViewById(R.id.location);
        TextView tvPerson = (TextView) convertView.findViewById(R.id.person);
        TextView tvState = (TextView) convertView.findViewById(R.id.shiftState);
        TextView tvNotes = (TextView) convertView.findViewById(R.id.shiftNotes);
        
        tvLocation.setText(data.getLocation());
        if (!data.getPerson().equals(" ")) {
        	tvPerson.setText(data.getPerson());
        }
        else {
        	tvPerson.setVisibility(View.GONE);
        }
        tvState.setText(data.getShiftState());
        tvNotes.setText(data.getShiftNotes());

        return convertView;
    }

	public Object getGroup(int groupPosition) {
		return mGroups.get(groupPosition);
	}

	public int getGroupCount() {
		return mGroups.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.grouprow, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.shiftTime);
        tv.setText(group);
        return convertView;
    }

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}
	
	public void addShift(String shift) {
        if (!mGroups.contains(shift)) {
            mGroups.add(shift);
            mChildren.add(new ArrayList<Shift>());
        }
    }
	
	public void addShiftData(String shiftTime, Shift data) {
		int index = mGroups.indexOf(shiftTime);
		if (index >= 0) {
			mChildren.get(index).add(data);
		}
	}
 }