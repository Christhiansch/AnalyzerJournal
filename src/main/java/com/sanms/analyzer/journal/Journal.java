package com.sanms.analyzer.journal;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Journal
{

	private List<LocalTime> inits;
	private List<LocalTime> endts;
	private List<Integer> differences;
	private List<String> operaciones;
	private List<String> codigoAutorizaciones;

	public Journal()
	{
		inits = new ArrayList<>();
		endts = new ArrayList<>();
		differences = new ArrayList<>();
		operaciones = new ArrayList<>();
		codigoAutorizaciones = new ArrayList<>();
	}

	public List<LocalTime> getInits()
	{
		return inits;
	}

	public void setInits(List<LocalTime> inits)
	{
		this.inits = inits;
	}

	public List<LocalTime> getEndts()
	{
		return endts;
	}

	public void setEndts(List<LocalTime> endts)
	{
		this.endts = endts;
	}

	public List<Integer> getDifferences()
	{
		return differences;
	}

	public void setDifferences(List<Integer> differences)
	{
		this.differences = differences;
	}

	public List<String> getOperaciones()
	{
		return operaciones;
	}

	public void setOperaciones(List<String> operaciones)
	{
		this.operaciones = operaciones;
	}

	public List<String> getCodigoAutorizaciones()
	{
		return codigoAutorizaciones;
	}

	public void setCodigoAutorizaciones(List<String> codigoAutorizaciones)
	{
		this.codigoAutorizaciones = codigoAutorizaciones;
	}

	public void tiempoDemora(int index)
	{
		LocalTime demora = endts.get(index);
		demora = demora.minusHours(inits.get(index).getHour());
		demora = demora.minusMinutes(inits.get(index).getMinute());
		demora = demora.minusSeconds(inits.get(index).getSecond());
		differences.add(demora.getSecond());
	}

	public void agregarTiempoOperacion(String line, int indexOperacion)
	{
		LocalTime init = LocalTime.parse(line.substring(9, 18).trim());
		String operacion = line.substring(indexOperacion, line.length() - 1).trim();

		inits.add(init);
		operaciones.add(operacion);
	}

	public void agregarTiempoAutorizacion(String line)
	{
		LocalTime endt = LocalTime.parse(line.substring(9, 18).trim());
		endts.add(endt);
	}
	
	public void agregarCodigoAutorizaciones(String line) 
	{
		String lineaAutorizacion[] = line.split(":");
		String codigoAutorizacion = lineaAutorizacion[3].trim();
		codigoAutorizaciones.add(codigoAutorizacion);
			
	}

}
