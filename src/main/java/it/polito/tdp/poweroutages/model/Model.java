package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model
{
	private PowerOutageDAO dao;
	private long MAX_YEARS;
	private long MAX_HOURS;
	Set<Blackout> blackouts;
	Set<Blackout> best;

	public Model()
	{
		dao = new PowerOutageDAO();
	}

	public List<Nerc> getNercList()
	{
		return dao.getNercList();
	} 
	
	//	
	public void cercaSequenza(Nerc nerc, int maxYears, int maxHours)
	{
		MAX_YEARS = maxYears;
		MAX_HOURS = maxHours;
		
		blackouts = new HashSet<>(dao.getBlackoutByNerc(nerc));
		best = new HashSet<Blackout>(); 
		Set<Blackout> parziale = new HashSet<>();

//		this.cerca(parziale);
	}

	private void cerca(Set<Blackout> parziale, long maxYears, long maxHours)
	{
		long years;
		long hours; 
		
		if (confronta(parziale)) //best
			System.out.println(parziale);
		//
		for (Blackout b : blackouts)
		{
			
			parziale.add(b);
			if (!parziale.contains(b) && valida(parziale))
//				this.cerca(parziale);
			parziale.remove(b);
		}
	}

	private boolean confronta(Set<Blackout> parziale)
	{
		if (calcolaPersone(parziale) > calcolaPersone(best))
		{
			best = new HashSet<Blackout>(parziale);
			return true;
		}
		return false;
	}

	private boolean valida(Set<Blackout> parziale)
	{
		return calcolaOre(parziale) < MAX_HOURS && calcolaAnni(parziale) < MAX_YEARS;
	}

	private int calcolaPersone(Set<Blackout> set)
	{
		int pers = 0;
		for (Blackout b : set)
			pers += b.getAffected();
		System.out.println(pers);
		return pers;
	}

	private double calcolaOre(Set<Blackout> set)
	{
		double oreTot = 0;
		if (!set.isEmpty()) 
			for (Blackout b : set)
				oreTot += Duration.between(b.getInizio(), b.getFine()).toMinutes() / 60;
		return oreTot;
	}
	private int calcolaAnni(Set<Blackout> set)
	{
		int annoInizio = 0;
		int annoFine = 0;
		if (!set.isEmpty()) 
		{
			for (Blackout b : set)
			{
				int annoI = b.getInizio().getYear(); 
				int annoF = b.getFine().getYear(); 
				annoInizio = annoInizio>annoI ? annoI : annoInizio;
				annoFine = annoFine<annoF ? annoF : annoFine;
			} 
		}
		return (annoFine - annoInizio);
	}
}