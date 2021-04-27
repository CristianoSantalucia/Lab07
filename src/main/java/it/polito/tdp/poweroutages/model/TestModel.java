package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.cercaSequenza(new Nerc(8, null), 1, 1);
	}

}
