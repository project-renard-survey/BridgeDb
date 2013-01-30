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
import org.bridgedb.tools.metadata.validator.ValidationType;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.utils.ConfigReader;
import org.bridgedb.utils.StoreType;

/**
 * @author Christian
 */
public class SetupLoaderWithTestData {
        
   private static final boolean LOAD_DATA = true;
   
   public static void main(String[] args) throws BridgeDBException {
        ConfigReader.logToConsole();
        LinksetLoader linksetLoader = new LinksetLoader();
        linksetLoader.clearExistingData(StoreType.LOAD);
        linksetLoader.loadFile("../org.bridgedb.linksets/test-data/cw-cs.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        linksetLoader.loadFile("../org.bridgedb.linksets/test-data/cs-cm.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        linksetLoader.loadFile("../org.bridgedb.linksets/test-data/cs-cm.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        linksetLoader.loadFile("../org.bridgedb.metadata/test-data/chemspider-void.ttl", StoreType.LOAD, ValidationType.VOID);
        linksetLoader.loadFile("../org.bridgedb.metadata/test-data/chembl-rdf-void.ttl", StoreType.LOAD, ValidationType.VOID);
	}

}
