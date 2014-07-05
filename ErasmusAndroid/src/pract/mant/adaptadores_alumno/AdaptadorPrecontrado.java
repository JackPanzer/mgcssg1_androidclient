package pract.mant.adaptadores_alumno;

import java.util.HashMap;
import java.util.List;

import pract.mant.auxiliares_alumno.Precontrato;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class AdaptadorPrecontrado extends BaseExpandableListAdapter{

	private Context _context;
	private List<String> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<Precontrato>> _listDataChild;

	public AdaptadorPrecontrado(Context context, List<String> listDataHeader,
			HashMap<String, List<Precontrato>> listDataChild) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listDataChild;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		
		
		Precontrato p = (Precontrato) getChild(groupPosition, childPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.precontrato, null);
			
			
			
		}
		
		TextView nombre = (TextView) convertView.findViewById(R.id.nombre);
		TextView telefono = (TextView) convertView.findViewById(R.id.telefono);
		TextView titulacion = (TextView) convertView.findViewById(R.id.titulacion);
		TextView poblacion = (TextView) convertView.findViewById(R.id.poblacion);
		TextView idioma = (TextView) convertView.findViewById(R.id.idioma);
		
		nombre.setText(p.getNombre());
		telefono.setText(p.getTelefono());
		titulacion.setText(p.getTitulacion());
		poblacion.setText(p.getPoblacion());
		idioma.setText(p.getIdioma());
		
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.destinos3, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.destinos3);
		lblListHeader.setTypeface(null, Typeface.BOLD);
		lblListHeader.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
