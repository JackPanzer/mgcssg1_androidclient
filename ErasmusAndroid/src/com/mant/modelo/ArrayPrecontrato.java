package com.mant.modelo;

import java.util.Vector;

import org.ksoap2.serialization.SoapObject;

public class ArrayPrecontrato {
	private int errno;
	private Vector<ComplexPrecontrato> precontratos;

	public ArrayPrecontrato(SoapObject obj) {

		if (obj != null) {
			precontratos = new Vector<ComplexPrecontrato>();

			/**
			 * La primera properti es el errno, es lo mismo que poner: String
			 * _errno = obj.getProperty(0)
			 * */
			String _errno = obj.getPrimitivePropertyAsString("errno");
			if (_errno.equals("")) {
				errno = -2;
			} else {
				errno = Integer.parseInt(_errno);
			}

			// Obtengo el numero de filas del "array bidimensional"
			int nFil = obj.getPropertyCount();

			for (int i = 1; i < nFil; i++) {
				SoapObject f = (SoapObject) obj.getProperty(i);

				/**
				 * Creamos un nuevo destino y lo añadimos a la lista
				 * */
				ComplexPrecontrato auxPrecontrato = new ComplexPrecontrato(f);
				precontratos.add(auxPrecontrato);

			}
		}
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}

	public Vector<ComplexPrecontrato> getPrecontrato() {
		return precontratos;
	}

	public void setPrecontrato(Vector<ComplexPrecontrato> precontratos) {
		this.precontratos = precontratos;
	}
}
