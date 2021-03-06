package org.opentosca.csarrepo.service;

import java.util.List;

import org.opentosca.csarrepo.exception.PersistenceException;
import org.opentosca.csarrepo.model.Csar;
import org.opentosca.csarrepo.model.repository.CsarRepository;

/**
 * @author Dennis Przytarski
 */
public class ListCsarService extends AbstractService {

	private final CsarRepository csarRepository = new CsarRepository();
	private List<Csar> csarFiles = null;

	/**
	 * @param userId
	 */
	public ListCsarService(long userId) {
		super(userId);
		try {
			this.csarFiles = csarRepository.getAll();
		} catch (PersistenceException e) {
			this.addError(e.getMessage());
		}
	}

	/**
	 * @return List of CSARs
	 */
	public List<Csar> getResult() {
		super.logInvalidResultAccess("getResult");

		return this.csarFiles;
	}

}
