package com.example.customespineradapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.imagesqldata.R;
import com.example.model.PersonDetailsItem;
import com.example.model.SpinerItem;

public class CustomeSpinerAdapter extends ArrayAdapter<SpinerItem> {
	private List<SpinerItem> spinerData;
	private Context context;
	private LayoutInflater inflater;

	public CustomeSpinerAdapter(Context context, int resource,
			List<SpinerItem> spinerData) {
		super(context, resource, spinerData);
		// TODO Auto-generated constructor stub

		this.context = context;
		this.spinerData = spinerData;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);

	}

	public CustomeSpinerAdapter(Context context, int resource) {
		super(context, resource);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return getCustomView(position, convertView, parent);

	}

	static class ViewHolder {
		public TextView label;

	}

	public View getCustomView(int position, View rowView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (rowView == null) {

			rowView = inflater.inflate(R.layout.custom_spiner_row, null);
			viewHolder = new ViewHolder();

			viewHolder.label = (TextView) rowView
					.findViewById(R.id.txtSpinerRow);

			rowView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) rowView.getTag();
		}
		SpinerItem item = spinerData.get(position);
		viewHolder.label.setText(item.getSpinerName());
		return rowView;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return spinerData.size();
	}

	@Override
	public SpinerItem getItem(int position) {
		// TODO Auto-generated method stub
		return spinerData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public List<SpinerItem> getList() {
		return spinerData;
	}
}
