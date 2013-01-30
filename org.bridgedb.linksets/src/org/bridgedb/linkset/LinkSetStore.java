// BridgeDb,
// An abstraction layer for identifier mapping services, both local and online.
//
// Copyright 2006-2009  BridgeDb developers
// Copyright 2012-2013  Christian Y. A. Brenninkmeijer
// Copyright 2012-2013  OpenPhacts
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package org.bridgedb.linkset;

import java.util.List;
import org.bridgedb.utils.BridgeDBException;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;

/**
 * Interface for retrieving the RDF MetaData related to LinkSets.
 *
 * The purpose is to isolute the WebService methods from any actual RDF implementation
 *
 * Note only retreives the MetaData not the actuall mappings.
 *
 * @author Christian
 */
public interface LinkSetStore {
    
    /**
     * Obtains a list of all the Linksets in the system.
     *
     * Currently not used.
     * @return list of linksets
     * @throws BridgeDBException
     */
    //public List<String> getLinksetNames() throws BridgeDBException;

    /**
     * Converts all the rdf for this linkset into well formatted text
     * @param id Id of the linkset for which metadata is required
     * @return Well formatted text that can be returned to a user
     * @throws BridgeDBException
     */
    public String getLinksetRDF (int id) throws BridgeDBException;
   
    public String getVoidRDF (int id) throws BridgeDBException;

    public List<Statement> getStatementsForResource(Resource resource) throws BridgeDBException;
}
