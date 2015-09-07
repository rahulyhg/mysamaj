package com.example.searchdetails;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.imagesqldata.R;
import com.example.model.PeopleDetailsItem;

public class CustomSearchListAdapter extends ArrayAdapter<PeopleDetailsItem>
		implements Filterable {

	Context context;
	private ArrayList<PeopleDetailsItem> peoplelistdata;
	private ArrayList<PeopleDetailsItem> Filterpeoplelistdata;
	private Filter planetFilter;
	private LayoutInflater inflater;

	public CustomSearchListAdapter(Context context, int resource,
			ArrayList<PeopleDetailsItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.peoplelistdata = new ArrayList<PeopleDetailsItem>();
		this.peoplelistdata.addAll(objects);
		this.Filterpeoplelistdata = new ArrayList<PeopleDetailsItem>();
		this.Filterpeoplelistdata.addAll(objects);

		inflater = LayoutInflater.from(context);
	}

	static class ViewHolder {
		public TextView personId, intFamilyNo, intIsFamilyHead, strFullName,
				intAge, intGender, intAddressID, intRelationID,
				intMaritalStatus, intShakhID, intWardID, intMosalEN,
				strMosalOtherEN, strEducationEN, intSem, intJobEN,
				strJobDetailEN, strMobile, strMobile2, strFbLink, dtBirthDate,
				strEmailid, strProfileImage;

	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View rowView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		View view = rowView;

		if (view == null) {
			// viewHolder = new ViewHolder();

			view = inflater.inflate(R.layout.people_list_row, null);

			// View view = inflater.inflate(R.layout.people_list_row, null);
			viewHolder = new ViewHolder();

			viewHolder.strFullName = (TextView) view
					.findViewById(R.id.peoplefullNameEN);
			viewHolder.intGender = (TextView) view
					.findViewById(R.id.peopleintGender);
			viewHolder.intRelationID = (TextView) view
					.findViewById(R.id.peoplerelation);
			viewHolder.strMobile = (TextView) view
					.findViewById(R.id.peoplestrMobile);
			viewHolder.strMobile2 = (TextView) view
					.findViewById(R.id.peopleotherMobile);
			viewHolder.strEducationEN = (TextView) view
					.findViewById(R.id.peoplestrEducationEN);
			viewHolder.intAge = (TextView) view.findViewById(R.id.peopleintAge);
			viewHolder.strJobDetailEN = (TextView) view
					.findViewById(R.id.peoplestrJobDetailEN);
			viewHolder.intMosalEN = (TextView) view
					.findViewById(R.id.peoplemosal);
			viewHolder.intAddressID = (TextView) view
					.findViewById(R.id.peopleaddress);
			viewHolder.intMaritalStatus = (TextView) view
					.findViewById(R.id.peopleintMaritalStatus);

			view.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) view.getTag();

		}

		PeopleDetailsItem peopleItem = (PeopleDetailsItem) getItem(position);

		viewHolder.strFullName.setText(peopleItem.getNameEN());
		viewHolder.intGender.setText(peopleItem.getGender());
		viewHolder.intRelationID.setText(peopleItem.getRelationEN());
		viewHolder.strMobile.setText(peopleItem.getMobile());
		viewHolder.strMobile2.setText(peopleItem.getMobile2());
		viewHolder.strEducationEN.setText(peopleItem.getStrEducationEN());
		viewHolder.intAge.setText(peopleItem.getAGE());

		viewHolder.strJobDetailEN.setText(peopleItem.getJobDetailEN()
				.replaceAll("<br>", "-"));
		viewHolder.intMosalEN.setText(peopleItem.getMosalEN());
		viewHolder.intAddressID.setText(peopleItem.getAddressEN());
		viewHolder.intMaritalStatus.setText(peopleItem.getMaritalStatusEN());
		return view;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Filterpeoplelistdata.size();
	}

	@Override
	public PeopleDetailsItem getItem(int position) {
		// TODO Auto-generated method stub
		return Filterpeoplelistdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public void resetData() {
		Filterpeoplelistdata = peoplelistdata;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		if (planetFilter == null)
			planetFilter = new PlanetFilter();

		return planetFilter;
	}

	private class PlanetFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			constraint = constraint.toString().toLowerCase();
			FilterResults result = new FilterResults();
			if (constraint != null && constraint.toString().length() > 0) {
				ArrayList<PeopleDetailsItem> filteredItems = new ArrayList<PeopleDetailsItem>();

				for (int i = 0, l = peoplelistdata.size(); i < l; i++) {
					PeopleDetailsItem country = peoplelistdata.get(i);
					if (country.toString().toLowerCase().contains(constraint))
						filteredItems.add(country);
				}
				result.count = filteredItems.size();
				result.values = filteredItems;
			} else {
				synchronized (this) {
					result.values = peoplelistdata;
					result.count = peoplelistdata.size();
				}
			}
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			Filterpeoplelistdata = (ArrayList<PeopleDetailsItem>) results.values;
			notifyDataSetChanged();
			clear();
			for (int i = 0, l = Filterpeoplelistdata.size(); i < l; i++)
				add(Filterpeoplelistdata.get(i));
			notifyDataSetInvalidated();
		}

	}
}
