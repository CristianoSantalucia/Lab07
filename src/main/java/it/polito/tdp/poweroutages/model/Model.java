package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model
{
	PowerOutageDAO dao;

	public Model()
	{
		dao = new PowerOutageDAO();
	}

	public List<Nerc> getNercList()
	{
		return dao.getNercList();
	}
	
	/**
	 * Genera un insieme di lista tra cui scegliere la migliore parziale (max Affected)
	 * @param nerc
	 * @param maxYears
	 * @param maxHours
	 * @return la best parziale
	 */
	public void cercaSequenza(Nerc nerc, int maxYears, int maxHours)
	{
		Set<Blackout> blackouts = new HashSet<>();
		for (Blackout b : dao.getBlackoutByNerc(nerc))
			if(Duration.between(b.getInizio(), b.getFine()).toMinutes() <= maxHours*60)
				blackouts.add(b);
		
		Set<Blackout> parziale = new HashSet<>();
		
		this.cerca(parziale, blackouts);
		
		System.out.println(parziale.size());
		System.out.println(parziale);
	}

	private void cerca(Set<Blackout> parziale,Set<Blackout> blackouts)
	{
		
	}
}
