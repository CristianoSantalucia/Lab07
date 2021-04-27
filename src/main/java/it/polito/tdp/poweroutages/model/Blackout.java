package it.polito.tdp.poweroutages.model;

import java.time.*;

public class Blackout
{
	private int id;
	private LocalDateTime inizio, fine;
	private int affected;
	
	public Blackout(int id, LocalDateTime inizio, LocalDateTime fine, int affected)
	{
		this.id = id;
		this.inizio = inizio;
		this.fine = fine;
		this.affected = affected;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public LocalDateTime getInizio()
	{
		return inizio;
	}
	public void setInizio(LocalDateTime inizio)
	{
		this.inizio = inizio;
	}
	public LocalDateTime getFine()
	{
		return fine;
	}
	public void setFine(LocalDateTime fine)
	{
		this.fine = fine;
	}
	public int getAffected()
	{
		return affected;
	}
	public void setAffected(int affected)
	{
		this.affected = affected;
	}
	@Override public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Blackout other = (Blackout) obj;
		if (id != other.id) return false;
		return true;
	}
	@Override public String toString()
	{
		return "Blackout [id=" + id + ", inizio=" + inizio + ", fine=" + fine + ", affected=" + affected + "]\n";
	}
}