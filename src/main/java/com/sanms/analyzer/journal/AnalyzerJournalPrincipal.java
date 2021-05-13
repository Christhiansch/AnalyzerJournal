package com.sanms.analyzer.journal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sanms.analyzer.journal.util.Util;

/**
 * Hello world!
 *
 */
public class AnalyzerJournalPrincipal
{
	public static void main(String[] args)
	{

		String line;
		Journal journal = new Journal();
		StringBuilder builder = new StringBuilder();

		Path pathJournal = Paths.get("C:\\csandoval\\BBVA\\ANALISISATM\\1376\\1376EJ202103040001.TXT");
		Path pathJournalResult = Paths.get("C:\\csandoval\\BBVA\\ANALISISATM\\1376\\1376EJ202103040001.csv");
		
		try
		{

			BufferedReader journalRead = Files.newBufferedReader(pathJournal, StandardCharsets.ISO_8859_1);

			while ((line = journalRead.readLine()) != null)
			{

				if (line.contains(Util.TKN_JRNALSOLICITUD))
				{
					journal.agregarTiempoOperacion(line, Util.INDEX_ENVIOSOLI);
				}
				
				if (line.contains(Util.TKN_JRNALAUTORIZADA))
				{
					journal.agregarTiempoAutorizacion(line);
				}
				
				if(line.contains(Util.TKN_TIMEOUTRECEPTIONHOST))
				{
					journal.agregarTiempoAutorizacion(line);
				}

				if (line.contains(Util.TKN_JRNALENVIOANUL))
				{
					journal.agregarTiempoOperacion(line, Util.INDEX_ENVIOANUL);
				}
			}

			// CREACION ARCHIVO DE SALIDA
			Path fileResult = Files.createFile(pathJournalResult);
			BufferedWriter journalResult = new BufferedWriter(new FileWriter(fileResult.toFile()));

			// CALCULO DIFERENCIA DE TIEMPOS
			for(int index=0; index < journal.getInits().size(); index++)
			{
				journal.tiempoDemora(index);
			}
			
			// ARCHIVO DE SALIDA TIEMPOS JOURNAL
			builder.append(Util.CSV_HEADER);
			for (int i = 0; i < journal.getInits().size(); i++)
			{				
				builder
						.append(journal.getInits().get(i)).append(Util.TKN_SEPARATOR)
						.append(journal.getEndts().get(i)).append(Util.TKN_SEPARATOR)
						.append(journal.getOperaciones().get(i)).append(Util.TKN_SEPARATOR)						
						.append(journal.getDifferences().get(i)).append(Util.TKN_LINEBREAK);
			}

			journalResult.write(builder.toString());
			journalResult.close();			
			

		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
