package com.mant.administrador;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends Activity {

			// Adaptador que usamos para indicar al Spinner dónde obtiene las opciones
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);
		
		final String[] orden = new String[]{"Select","Delete","Create","Drop Table"};
		final String[] tabla = new String[]{"Alumnos","Asignaturas","Matricula","*"};
		final String[] parametros = new String[]{"Nombre","Apellidos","Edad","Idiomas"};
		final String[] condicion = new String[]{"Edad", "Nombre","Apellidos","Idiomas"};
		final String[] operador = new String[]{">", "<","==",">=","<="};
		final String[] valor = new String[]{"1","2","3","4"};
		
		ArrayAdapter<String> adaptador_orden = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, orden);
		ArrayAdapter<String> adaptador_tabla = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tabla);
		ArrayAdapter<String> adaptador_parametros = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, parametros);
		ArrayAdapter<String> adaptador_condicion = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, condicion);
		ArrayAdapter<String> adaptador_operador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, operador);
		ArrayAdapter<String> adaptador_valor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valor);
		
		Spinner listado_orden = (Spinner) findViewById(R.id.f_orden);
		Spinner listado_tabla = (Spinner) findViewById(R.id.f_tabla);
		Spinner listado_parametros = (Spinner) findViewById(R.id.f_parametro);
		Spinner listado_condicion = (Spinner) findViewById(R.id.f_condicion);
		Spinner listado_operador = (Spinner) findViewById(R.id.f_operador);
		Spinner listado_valor = (Spinner) findViewById(R.id.f_valor);
		
		
		adaptador_orden.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listado_orden.setAdapter(adaptador_orden);
		
		listado_orden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
					Toast.makeText(getBaseContext(), orden[position], 1).show();
					//resultado.setText("Opción seleccionada: " + (position+1));
					}
					// Si no se selecciona nada limpiamos la etiqueta. En este tipo de
					// componentes siempre se selecciona una opción por lo que no se verá
					// este evento.
					public void onNothingSelected(AdapterView<?> adapterView) {
					//resultado.setText("");
					}
			
		});
		
		adaptador_tabla.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listado_tabla.setAdapter(adaptador_tabla);
		
		listado_orden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
					Toast.makeText(getBaseContext(), tabla[position], 1).show();
					//resultado.setText("Opción seleccionada: " + (position+1));
					}
					// Si no se selecciona nada limpiamos la etiqueta. En este tipo de
					// componentes siempre se selecciona una opción por lo que no se verá
					// este evento.
					public void onNothingSelected(AdapterView<?> adapterView) {
					//resultado.setText("");
					}
			
		});
		
		adaptador_parametros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listado_parametros.setAdapter(adaptador_parametros);
		
		listado_parametros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
					Toast.makeText(getBaseContext(), parametros[position], 1).show();
					//resultado.setText("Opción seleccionada: " + (position+1));
					}
					// Si no se selecciona nada limpiamos la etiqueta. En este tipo de
					// componentes siempre se selecciona una opción por lo que no se verá
					// este evento.
					public void onNothingSelected(AdapterView<?> adapterView) {
					//resultado.setText("");
					}
			
		});
		
		
		adaptador_condicion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listado_condicion.setAdapter(adaptador_condicion);
		
		listado_condicion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
					Toast.makeText(getBaseContext(), condicion[position], 1).show();
					//resultado.setText("Opción seleccionada: " + (position+1));
					}
					// Si no se selecciona nada limpiamos la etiqueta. En este tipo de
					// componentes siempre se selecciona una opción por lo que no se verá
					// este evento.
					public void onNothingSelected(AdapterView<?> adapterView) {
					//resultado.setText("");
					}
			
		});
		
		
		adaptador_operador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listado_operador.setAdapter(adaptador_operador);
		
		listado_orden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
					Toast.makeText(getBaseContext(), operador[position], 1).show();
					//resultado.setText("Opción seleccionada: " + (position+1));
					}
					// Si no se selecciona nada limpiamos la etiqueta. En este tipo de
					// componentes siempre se selecciona una opción por lo que no se verá
					// este evento.
					public void onNothingSelected(AdapterView<?> adapterView) {
					//resultado.setText("");
					}
			
		});
		
		
		adaptador_valor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		listado_valor.setAdapter(adaptador_valor);
		
		listado_valor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			public void onItemSelected(AdapterView<?> adapterView, View view,
					int position, long id) {
					Toast.makeText(getBaseContext(), valor[position], 1).show();
					//resultado.setText("Opción seleccionada: " + (position+1));
					}
					// Si no se selecciona nada limpiamos la etiqueta. En este tipo de
					// componentes siempre se selecciona una opción por lo que no se verá
					// este evento.
					public void onNothingSelected(AdapterView<?> adapterView) {
					//resultado.setText("");
					}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.formulario, menu);
		return true;
	}

	public void clickVolver(View v){
		finish();
		
	}
}
