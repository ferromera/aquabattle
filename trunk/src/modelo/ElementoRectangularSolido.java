package modelo;
/*
 * Representa todos los elementos del modelo que pueden chocar
 * entre ellos.
 */
public abstract class ElementoRectangularSolido extends ElementoRectangular implements Impactable {
	public abstract void recibirImpacto(int fuerza);
}
