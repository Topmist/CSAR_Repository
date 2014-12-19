package org.opentosca.csarrepo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.opentosca.csarrepo.service.UploadCsarFileService;

/**
 * Servlet implementation class UploadCSARServlet
 */
@WebServlet("/uploadcsarfile")
public class UploadCsarFileServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;

	private static final String PARAM_CSAR_ID = "csarId";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadCsarFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		try {
			// Check if we have a file upload request
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			// check if it is actually a file
			if (isMultipart) {
				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload();

				// Parse the request
				FileItemIterator iter = upload.getItemIterator(request);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					InputStream stream = item.openStream();
					// Process the input stream
					String pathName = item.getName();
					// Path from local Upload of a user is the full path
					// get the actual last part of the path
					// (/foo/bar/uploadcsar.csar => uploadcsar.csar)
					String csarName = pathName.substring(pathName.lastIndexOf(File.separator) + 1);
					Long csarId = Long.parseLong(request.getParameter(PARAM_CSAR_ID));

					UploadCsarFileService upService = new UploadCsarFileService(0L, csarId, stream, csarName);
					// TODO, think about better Exceptionhandling (currently we
					// just take first Exception)
					if (upService.hasErrors()) {
						throw new ServletException("UploadCsarService has Errors: " + upService.getErrors().get(0));
					} else {
						// TODO write proper response
						response.getWriter().print("<html><body>Success</body></html>");
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}