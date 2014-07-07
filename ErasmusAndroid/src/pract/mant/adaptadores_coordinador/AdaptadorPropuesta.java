package pract.mant.adaptadores_coordinador;

import java.util.HashMap;
import java.util.List;

import pract.mant.auxiliares_alumno.Precontrato;
import pract.mant.auxiliares_coordinador.Propuesta;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erasmusandroid.R;

public class AdaptadorPropuesta extends BaseExpandableListAdapter{

	private Context _context;
	private List<String> cabecera_propuesta;
    private HashMap<String, List<Propuesta>> contenido_propuesta;

	public AdaptadorPropuesta(Context context, List<String> cabecera_propuesta,
			HashMap<String, List<Propuesta>> contenido_propuesta) {
		this._context = context;
		this.cabecera_propuesta = cabecera_propuesta;
		this.contenido_propuesta = contenido_propuesta;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.contenido_propuesta.get(this.cabecera_propuesta.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		
		
		final Propuesta p = (Propuesta) getChild(groupPosition, childPosition);
		
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.contenido_propuesta, null);
			
			
			
		}
		
		TextView nombre = (TextView) convertView.findViewById(R.id.cp_nombre);
		TextView destino = (TextView) convertView.findViewById(R.id.cp_destino);
		CheckBox aceptado = (CheckBox)convertView.findViewById(R.id.cp_check);
		
		
		//ListView lstLista=(ListView) convertView.findViewById(R.id.cp_lista_asig);
				
		/*AdaptadorPropuestaAsignaturas adaptador;
		
		adaptador = new AdaptadorPropuestaAsignaturas(_context, p.getAsignaturas());*/
		
		nombre.setText(p.getNombre());
		destino.setText(p.getDestino());
		aceptado.setChecked(p.getAceptado());
		
		//lstLista.setAdapter(adaptador);
		
		aceptado.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				p.setAceptado(isChecked);
			}
		});
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.contenido_propuesta.get(this.cabecera_propuesta.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.cabecera_propuesta.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.cabecera_propuesta.size();
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
			convertView = infalInflater.inflate(R.layout.propuesta, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.txt_cabecera_propuesta);
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
