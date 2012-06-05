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
	private Context context;
	private ArrayList<String> groups;
	private ArrayList<ArrayList<Shift>> children;
        
	public ExpoAdapter(Context context, ArrayList<String> group, ArrayList<ArrayList<Shift>> children) {
		this.context = context;
		this.groups = group;
		this.children = children;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return children.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
		return children.get(groupPosition).size();
	}

	public TextView getGenericView() {
		// Layout parameters for the ExpandableListView
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 64);

		TextView textView = new TextView(context);
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
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.childrow, null);
        }
        TextView tvLocation = (TextView) convertView.findViewById(R.id.location);
        TextView tvPerson = (TextView) convertView.findViewById(R.id.person);
        TextView tvState = (TextView) convertView.findViewById(R.id.shiftState);
        TextView tvNotes = (TextView) convertView.findViewById(R.id.shiftNotes);
        
        tvLocation.setText(data.getLocation());
        tvPerson.setText(data.getPerson());
        tvState.setText(data.getShiftState());
        tvNotes.setText(data.getShiftNotes());

        return convertView;
    }

	public Object getGroup(int groupPosition) {
		return groups.get(groupPosition);
	}

	public int getGroupCount() {
		return groups.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
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
        if (!groups.contains(shift)) {
            groups.add(shift);
            children.add(new ArrayList<Shift>());
        }
    }
	
	public void addShiftData(String shiftTime, Shift data) {
		int index = groups.indexOf(shiftTime);
		if (index >= 0) {
			children.get(index).add(data);
		}
	}
 }