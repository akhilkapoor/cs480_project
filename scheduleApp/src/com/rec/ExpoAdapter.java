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
	private ArrayList<ArrayList<String>> children;
        
	public ExpoAdapter(Context context, ArrayList<String> group, ArrayList<ArrayList<String>> children) {
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
        String data = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.childrow, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.shiftData);
        tv.setText("   " + data);

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
	
	public void addShift(String Shift) {
        if (!groups.contains(Shift)) {
            groups.add(Shift);
            children.add(new ArrayList<String>());
        }
    }
	
	public void addShiftData(String Shift, String data) {
		int index = groups.indexOf(Shift);
		if (index >= 0) {
			children.get(index).add(data);
		}
	}
 }