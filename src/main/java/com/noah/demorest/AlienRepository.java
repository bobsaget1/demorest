package com.noah.demorest;


import java.util.Arrays;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class AlienRepository
{
	List<Alien> aliens;
	
	Connection con = null;
	
	public AlienRepository()
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restdb", "root","superdogpete");
		}
		catch(Exception e)
		{}
		aliens = new ArrayList<>();
		
		Alien a1 = new Alien();
		a1.setId(1);
		a1.setName("Noah");
		a1.setPoints(20);
		
		Alien a2 = new Alien();
		a2.setId(2);
		a2.setName("Dad");
		a2.setPoints(70);
		
		aliens.add(a1);
		aliens.add(a2);
	}
	
	public List<Alien> getAliens()
	{
		List<Alien> aliens = new ArrayList<>();
		String sql = "Select * from alien";
		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next())
		{
			Alien a = new Alien();
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
			
			aliens.add(a);
		}
		}
		catch (Exception e)
		{}
		return aliens;
	}
	
	public Alien getAlien(int id)
	{
		String sql = "Select * from alien where id=" + id;
		Alien a = new Alien();

		try {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if (rs.next())
		{
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
			
			aliens.add(a);
		}
		}
		catch (Exception e)
		{}
		return a;
	}
	public void create(Alien a1)
	{
		String sql = "insert into alien values (?,?,?);";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,a1.getId());
			st.setString(2,a1.getName());
			st.setInt(3,a1.getPoints());
			st.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	public void update(Alien a1)
	{
		String sql = "update  alien  set name = ?, points = ? where id = ?;";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,a1.getId());
			st.setString(2,a1.getName());
			st.setInt(3,a1.getPoints());
			st.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	public void delete(int id)
	{
		String sql = "delete from  alien  where id = ?;";
		try
		{
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
}
