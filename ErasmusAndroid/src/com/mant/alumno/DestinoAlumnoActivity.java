package com.mant.alumno;

import java.util.ArrayList;

import com.example.erasmusandroid.R;
import com.example.erasmusandroid.R.id;
import com.example.erasmusandroid.R.layout;
import com.example.erasmusandroid.R.menu;
import com.mant.auxiliares_alumno.Destinos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class DestinoAlumnoActivity extends Activity {

	//Se crea un ArrayList de tipo Dias//
		ArrayList<Destinos> todos_destinos = new ArrayList<Destinos>();
		//Se crea una objeto tipo ListView
		ListView lstLista;
		//Se crea un objeto de tipo AdaptadorDias
		Adaptadordestinos adaptador;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_destino_alumno);
		
		lstLista = (ListView) findViewById(R.id.listView1);
		
		todos_destinos.add(new Destinos("Destino 1", false));
		todos_destinos.add(new Destinos("Destino 2", false));
		todos_destinos.add(new Destinos("Destino 3", false));
		todos_destinos.add(new Destinos("Destino 4", false));
		
		//Se define un nuevo adaptador de tipo AdaptadorDias donde se le pasa como argumentos el contexto de la actividad y el arraylist de los dias
				adaptador = new Adaptadordestinos(this, todos_destinos);

				//Se establece el adaptador en la Listview
				lstLista.setAdapter(adaptador);

				//Esto es mas que nada es a nivel de diseño con el objetivo de crear unas lineas mas anchas entre item y item
				lstLista.setDividerHeight(3);

				//Se le aplica un Listener donde ira lo que tiene que hacer en caso de que sea pulsado
				lstLista.setOnItemClickListener(new OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

						//En caso de que la posicion seleccionada gracias a "arg2" sea true que lo cambie a false 
						if (todos_destinos.get(arg2).isChekeado()) {
							todos_destinos.get(arg2).setChekeado(false);
						} else {
							//aqui al contrario que la anterior, que lo pase a true.
							todos_destinos.get(arg2).setChekeado(true);
						}
						//Se notifica al adaptador de que el ArrayList que tiene asociado ha sufrido cambios (forzando asi a ir al metodo getView())
						adaptador.notifyDataSetChanged();

					}
				});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.destino_alumno, menu);
		return true;
	}

	public void clickVolver(View v){
		finish();
		
	}
	
	public void clickAceptar(View v){
		Intent act = new Intent(this, DestinoAsignaturaActivity.class);
		startActivity(act);
		
	}
	
}

//Esta clase extiende de ArrayAdapter para poder personalizarla a nuestro gusto 
class Adaptadordestinos extends ArrayAdapter<Destinos> {

	Activity contexto;
	ArrayList<Destinos> mis_destinos;

	//Constructor del AdaptadorDias donde se le pasaran por parametro el contexto de la aplicacion y el ArrayList de los dias 
	public Adaptadordestinos(Activity context, ArrayList<Destinos> mis_destinos) {
		//Llamada al constructor de la clase superior donde requiere el contexto, el layout y el arraylist
		super(context, R.layout.destinos, mis_destinos);
		this.contexto = context;
		this.mis_destinos = mis_destinos;

	}

	//Este metodo es el que se encarga de dibujar cada Item de la lista
	//y se invoca cada vez que se necesita mostrar un nuevo item.
	//En el se pasan parametros como la posicion del elemento mostrado, la vista (View) del elemento mostrado y el conjunto de vistas
	public View getView(int position, View convertView, ViewGroup parent) {
		View item = convertView;

		//Creamos esta variable para almacen posteriormente en el la vista que ha dibujado
		VistaItem vistaitem;

		//Si se decide que no existe una vista reutilizable para el proximo item entra en la condicion.
		//De este modo tambien ahorramos tener que volver a generar vistas
		if (item == null) {

			//Obtenemos una referencia de Inflater para poder inflar el diseño
			LayoutInflater inflador = contexto.getLayoutInflater();

			//Se le define a la vista (item) el tipo de diseño que tiene que tener
			item = inflador.inflate(R.layout.destinos, null);

			//Creamos un nuevo vistaitem que se almacenara en el tag de la vista
			vistaitem = new VistaItem();

			//Almacenamos en el objeto la referencia del TextView buscandolo por ID
			vistaitem.nombre = (TextView) item.findViewById(R.id.txtDia);

			//tambien almacenamos en el objeto la referencia del CheckBox buscandolo por ID
			vistaitem.chkEstado = (CheckBox) item.findViewById(R.id.chkEstado);

			//Ahora si, guardamos en el tag de la vista el objeto vistaitem 
			item.setTag(vistaitem);

		} else {
			//En caso de que la vista sea ya reutilizable se recupera el objeto VistaItem almacenada en su tag
			vistaitem = (VistaItem) item.getTag();
		}

		//Se cargan los datos desde el ArrayList
		vistaitem.nombre.setText(mis_destinos.get(position).getDia());
		vistaitem.chkEstado.setChecked(mis_destinos.get(position).isChekeado());
		
		//Se devuelve ya la vista nueva o reutilizada que ha sido dibujada
		return (item);
	}


	//Esta clase se usa para almacenar el TextView y el CheckBox de una vista y es donde esta el "truco" para que las vistas se guarden
	static class VistaItem {
		TextView nombre;
		CheckBox chkEstado;

	}

}

