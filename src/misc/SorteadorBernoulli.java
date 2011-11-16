package misc;

import excepciones.ProbabilidadInvalidaException;

public class SorteadorBernoulli extends SorteadorBinario{
	private double probabilidadExito;

	public double getProbabilidadExito() {
		return probabilidadExito;
	}

	public void setProbabilidadExito(double probabilidadExito) throws ProbabilidadInvalidaException {
		if(probabilidadExito > 1.0)
			throw new ProbabilidadInvalidaException();
		this.probabilidadExito = probabilidadExito;
	}
	public SorteadorBernoulli(){
		probabilidadExito=0.5;
	}
	public SorteadorBernoulli(double probabilidadExito) throws ProbabilidadInvalidaException{
		setProbabilidadExito(probabilidadExito);
	}
	public boolean sortear(){
		return (Math.random() < probabilidadExito);
	}
}
