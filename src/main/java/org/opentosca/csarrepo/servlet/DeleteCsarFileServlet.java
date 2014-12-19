package org.opentosca.csarrepo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opentosca.csarrepo.service.DeleteCsarFileService;

/**
 * 
 * @author Thomas Kosch (mail@thomaskosch.com)
 *
 */

@SuppressWarnings("serial")
@WebServlet(DeleteCsarFileServlet.PATH)
public class DeleteCsarFileServlet extends AbstractServlet {

	private final static String PARAM_CSAR_FILE_ID = "csarfileid";
	public final static String PATH = "/deletecsarfile";
	private static final Logger LOGGER = LogManager.getLogger(DeleteCsarFileServlet.class);

	public DeleteCsarFileServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		try {
			String csarFileId = request.getParameter(PARAM_CSAR_FILE_ID);
			DeleteCsarFileService deleteCsarFileService = new DeleteCsarFileService(0L, Long.parseLong(csarFileId));
			boolean result = deleteCsarFileService.getResult();
			if (result) {
				response.sendRedirect(getBasePath() + ListCsarServlet.PATH);
			}
		} catch (Exception e) {
			// TODO: Improve error handling
			LOGGER.error("Error while deleting Csar file with Id: " + PARAM_CSAR_FILE_ID, e);
		}
	}
}