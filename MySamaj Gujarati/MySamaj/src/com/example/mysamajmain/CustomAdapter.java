package com.example.mysamajmain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.databasehandler.DBHelper;
import com.example.imagesqldata.R;
import com.example.imagload.ImageLoader;
import com.example.model.PersonDetailsItem;
import com.example.model.SpinerItem;

public class CustomAdapter extends ArrayAdapter<PersonDetailsItem> implements
		Filterable {

	Context context;
	private SharedPreferences prefs; // shared preferences
	private ArrayList<PersonDetailsItem> listdata;
	private ArrayList<PersonDetailsItem> Filterpersonlistdata;
	private Filter personFilter;
	private LayoutInflater inflater;
	private int loader;
	private Calendar myCalendar;
	private Typeface myFont;
	private Typeface myFontBold;
	private DBHelper dbHelperRelation;
	private ArrayList<SpinerItem> dbrelation;

	public CustomAdapter(Context context, int resource,
			ArrayList<PersonDetailsItem> listdata) {
		super(context, resource);
		this.context = context;
		this.listdata = new ArrayList<PersonDetailsItem>();
		this.listdata.addAll(listdata);
		this.Filterpersonlistdata = new ArrayList<PersonDetailsItem>();
		this.Filterpersonlistdata.addAll(listdata);
		inflater = LayoutInflater.from(context);
		myCalendar = Calendar.getInstance();
		myFont = Typeface.createFromAsset(context.getAssets(), "Shruti.ttf");
		myFontBold = Typeface.createFromAsset(context.getAssets(),
				"Shrutib.ttf");
		dbHelperRelation = new DBHelper(context.getApplicationContext());
	}

	public CustomAdapter(Context context, int resource) {
		super(context, resource);
	}

	static class ViewHolder {
		public TextView fullNameEN, intAge, intGender, intMaritalStatus,
				strEducationEN, strJobEducationEN, strJobDetailsEN, strMobile,
				strMobile2, strProfileImage, strPersonId, strRelation;
		public ImageView img;
	}

	public void resetData() {
		Filterpersonlistdata = listdata;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		/*
		 * inflater = (LayoutInflater) context
		 * .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 */

		// TODO Auto-generated method stub
		// older holder = new Holder();
		// View rowView;
		View rowView = inflater.inflate(R.layout.list_row, null, true);
		ViewHolder viewHolder = new ViewHolder();

		viewHolder.fullNameEN = (TextView) rowView
				.findViewById(R.id.fullNameEN);
		viewHolder.strRelation = (TextView) rowView
				.findViewById(R.id.relationStatus);
		viewHolder.strMobile = (TextView) rowView.findViewById(R.id.strMobile);
		viewHolder.strMobile2 = (TextView) rowView
				.findViewById(R.id.otherMobileNoMain);
		viewHolder.intAge = (TextView) rowView.findViewById(R.id.intAge);
		viewHolder.intGender = (TextView) rowView.findViewById(R.id.intGender);
		viewHolder.intMaritalStatus = (TextView) rowView
				.findViewById(R.id.intMaritalStatus);
		viewHolder.strEducationEN = (TextView) rowView
				.findViewById(R.id.strEducationEN);
		viewHolder.strJobDetailsEN = (TextView) rowView
				.findViewById(R.id.strJobDetailEN);

		viewHolder.strPersonId = (TextView) rowView
				.findViewById(R.id.hidden_value);
		viewHolder.img = (ImageView) rowView.findViewById(R.id.rowImageView);

		PersonDetailsItem personItem = (PersonDetailsItem) getItem(position);

		viewHolder.fullNameEN.setTypeface(myFontBold);
		viewHolder.intMaritalStatus.setTypeface(myFont);
		viewHolder.intGender.setTypeface(myFont);
		viewHolder.strJobDetailsEN.setTypeface(myFont);
		viewHolder.strRelation.setTypeface(myFont);
		viewHolder.strEducationEN.setTypeface(myFont);
		
		viewHolder.fullNameEN.setText(personItem.getStrSurnameGJ() + " "
				+ personItem.getStrNameGJ() + " "
				+ personItem.getStrFatherNameGJ());

		dbrelation = dbHelperRelation.listSelectAllRelation();
		for (int i = 0; i < dbrelation.size(); i++) {
			SpinerItem spItem = dbrelation.get(i);
			String item = spItem.getSpinerId();
			if (Integer.parseInt(item) == Integer.parseInt(personItem
					.getIntRelationID())) {
				viewHolder.strRelation.setText(spItem.getSpinerName());
				break;
			}
		}
		/*
		 * viewHolder.intAge.setText(personItem.getIntAge()+
		 * dateConvert(personItem.getDtBirthDate()) ); String
		 * dob=personItem.getDtBirthDate();
		 */

		if (personItem.getDtBirthDate() == "null") {
			viewHolder.intAge.setText(personItem.getIntAge());
		} else {
			viewHolder.intAge.setText(dateConvert(personItem.getDtBirthDate()));
		}
		viewHolder.strEducationEN.setText(personItem.getStrEducationEN());
		viewHolder.strJobDetailsEN.setText(personItem.getStrJobDetailGJ());
		viewHolder.strMobile.setText(personItem.getStrMobile());
		viewHolder.strMobile2.setText("," + personItem.getStrMobile2());
		viewHolder.strPersonId.setText(personItem.getPersonId());
		String intAddressID = personItem.getIntAddressID();
		String imageData = personItem.getStrProfileImage();
		switch (Integer.parseInt(personItem.getIntGender())) {
		case 1:
			viewHolder.intGender.setText("પુરુષ");
			loader = R.drawable.male;
			break;
		case 2:
			viewHolder.intGender.setText("સ્ત્રી");
			loader = R.drawable.female;
			break;

		default:
			break;
		}

		switch (Integer.parseInt(personItem.getIntMaritalStatus())) {
		case 1:
			viewHolder.intMaritalStatus.setText("પરણીત");
			break;
		case 2:
			viewHolder.intMaritalStatus.setText("અપરિણિત");
			break;
		case 3:
			viewHolder.intMaritalStatus.setText("અન્ય");
			break;
		case 4:
			viewHolder.intMaritalStatus.setText("ગંગા. સ્વ.");
			break;
		case 5:
			viewHolder.intMaritalStatus.setText("વિધુર");
			break;
		case 6:
			viewHolder.intMaritalStatus.setText("વિવાહિત");
			break;
		case 7:
			viewHolder.intMaritalStatus.setText("વિધવા");
			break;

		default:
			break;
		}

		prefs = context.getSharedPreferences("spa", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("intAddressID", intAddressID); // or you can use
														// putInt, putBoolean
														// ...
		editor.commit();

		String imageURL = "http://mysamaj.co.in/" + imageData;
		ImageLoader imgLoader = new ImageLoader(context);

		// whenever you want to load an image from url
		// call DisplayImage function
		// url - image url to load
		// loader - loader image, will be displayed before getting image
		// image - ImageView
		imgLoader.DisplayImage(imageURL, loader, viewHolder.img);

		return rowView;
	}

	private String dateConvert(String getDate) {
		// TODO Auto-generated method stub
		String formatedDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
				Locale.US);
		try {
			myCalendar.setTime(format.parse(getDate));

			String myFormat = "dd/MM/yyyy"; // In which you need put here
			SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
			formatedDate = sdf.format(myCalendar.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return formatedDate;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Filterpersonlistdata.size();
	}

	@Override
	public PersonDetailsItem getItem(int position) {
		// TODO Auto-generated method stub
		return Filterpersonlistdata.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public ArrayList<PersonDetailsItem> getList() {
		return Filterpersonlistdata;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		if (personFilter == null)
			personFilter = new PersonFilter();

		return personFilter;
	}

	private class PersonFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			constraint = constraint.toString().toLowerCase();
			FilterResults result = new FilterResults();
			if (constraint != null && constraint.toString().length() > 0) {
				ArrayList<PersonDetailsItem> filteredItems = new ArrayList<PersonDetailsItem>();

				for (int i = 0, l = listdata.size(); i < l; i++) {
					PersonDetailsItem country = listdata.get(i);
					if (country.toString().toLowerCase().contains(constraint))
						filteredItems.add(country);
				}
				result.count = filteredItems.size();
				result.values = filteredItems;
			} else {
				synchronized (this) {
					result.values = listdata;
					result.count = listdata.size();
				}
			}
			return result;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			// Now we have to inform the adapter about the new list filtered
			Filterpersonlistdata = (ArrayList<PersonDetailsItem>) results.values;
			notifyDataSetChanged();
			clear();
			for (int i = 0, l = Filterpersonlistdata.size(); i < l; i++)
				add(Filterpersonlistdata.get(i));
			notifyDataSetInvalidated();
		}

	}
}
