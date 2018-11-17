package varios;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class GeneradorReportes {

	public void generarReporte(HttpServletResponse response, URL reportPath, String format, Map<?, ?> parameters,
			Connection connection, String NameReport, String OnOffLine) throws JRException, SQLException, IOException {

		byte[] fichero = generarReporte2(reportPath, format, parameters, connection);

		ServletOutputStream out = null;

		try {

			String mime = "application/pdf";

			String extension = ".pdf";

			if (format.equalsIgnoreCase("xls")) {

				mime = "application/x-ms-excel";

				response.setContentType("application/download");

				response.addHeader("Content-Disposition", "attachment;filename=" + NameReport + ".xls");

				extension = ".xls";

			} else if (format.equalsIgnoreCase("rtf")) {

				mime = "text/rtf";

				response.addHeader("Content-Disposition", "attachment;filename=" + NameReport + ".rtf");

				extension = ".rtf";

			} else

			if (format.equalsIgnoreCase("html")) {

				mime = "text/html";
				extension = ".html";

			}

			response.setContentType(mime);

			response.setHeader("Content-disposition", OnOffLine + "; filename=" + NameReport + extension);

			response.setContentLength(fichero.length);

			out = response.getOutputStream();

			out.write(fichero, 0, fichero.length);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			// connection.close();

			out.flush();

			out.close();

		}

	}

	/**
	 * Devuelve el reporte en un array[] de byte
	 * 
	 * @param reportPath
	 * @param format
	 * @param parameters
	 * @param connection
	 * @return
	 * @throws JRException
	 * @throws SQLException
	 */
	public byte[] generarReporte2(URL reportPath, String format, Map<?, ?> parameters, Connection connection)
			throws JRException, SQLException {
		JasperReport report2 = (JasperReport) JRLoader.loadObject(reportPath);

		@SuppressWarnings("unchecked")
		JasperPrint fill = JasperFillManager.fillReport(report2, (Map<String, Object>) parameters, connection);

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		if (format.equalsIgnoreCase("pdf") || format.equalsIgnoreCase("")) {

			JasperExportManager.exportReportToPdfStream(fill, out);
		}
		return out.toByteArray();

	}

}