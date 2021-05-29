package co.edu.uco.sudoku.dominio;

import co.edu.uco.sudoku.negocio.validador.regla.concreta.celda.NumeroCeldaValidarRegla;

public class CeldaDominio {

	private int codigo;
	private int numero;
	private boolean esPista;
	private PosicionDominio posicion;

	private CeldaDominio(int codigo, int numero, boolean esPista, PosicionDominio posicion) {
		setCodigo(codigo);
		setNumero(numero);
		setEspista(esPista);
		setPosicion(posicion);
	}

	public static CeldaDominio crear(int codigo, int numero, boolean esPista, PosicionDominio posicion) {
		return new CeldaDominio(codigo, numero, esPista, posicion);
	}

	public void validarNumeroEntreUnoYNueve() {
		NumeroCeldaValidarRegla.obtenerInstancia().validar(this);
	}

	public CeldaDominio ValidarPista() {
		if (this.esPista) {
			return this;
		}
		return null;
	}

	private void setCodigo(int codigo) {
		this.codigo = codigo;

	}

	private void setNumero(int numero) {
		this.numero = numero;
	}

	private void setEspista(boolean esPista) {
		this.esPista = esPista;
	}

	private void setPosicion(PosicionDominio posicion) {
		this.posicion = posicion;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getNumero() {
		return numero;
	}

	public boolean isEsPista() {
		return esPista;
	}

	public PosicionDominio getPosicion() {
		return posicion;
	}

}
