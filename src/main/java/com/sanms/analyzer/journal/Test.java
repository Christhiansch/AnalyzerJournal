package com.sanms.analyzer.journal;

import java.time.LocalTime;

public class Test {

	public static void main(String[] args) {
		
		StringBuilder builder = new StringBuilder();
		
		String header = "TIEMPO INICIAL|TIEMPO FINAL|OPERACION|DIFERENCIA[seg]\n";
		builder.append(header);
		
		
		LocalTime diferencia;
		LocalTime inicio = LocalTime.parse("06:41:17");
		LocalTime end = LocalTime.parse("06:41:19");
		
		diferencia = end;
		diferencia = diferencia.minusHours(inicio.getHour());
		diferencia = diferencia.minusMinutes(inicio.getMinute());
		diferencia = diferencia.minusSeconds(inicio.getSecond());
		
//		Integer demora = diferencia.;
		for(int i=0; i<10; i++)
		{
			builder.append(inicio).append("|").append(end).append("|").append("CONSULTA").append("|").append(diferencia).append("\n");
		}
		
		System.out.println("Diferencia en segundos " + diferencia.getSecond());
		System.out.println(builder.toString());
	}
}
