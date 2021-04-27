package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO
{
	public List<Nerc> getNercList()
	{
		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next())
			{
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public Set<Blackout> getBlackoutByNerc(Nerc nerc)
	{
		String sql = "SELECT id, date_event_began,date_event_finished,customers_affected "
					+ "FROM poweroutages "
					+ "WHERE poweroutages.nerc_id = ?";
		
		Set<Blackout> set = new HashSet<>();

		try
		{
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next())
			{
				int id = res.getInt("id");
				LocalDateTime inizio = res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime fine = res.getTimestamp("date_event_finished").toLocalDateTime();
				int affected = res.getInt("customers_affected");
				
				Blackout b = new Blackout(id, inizio, fine, affected);
				set.add(b);
			}
			
			conn.close();
			
			System.out.println(set.size());
			
			return set;
		}
		catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
}
