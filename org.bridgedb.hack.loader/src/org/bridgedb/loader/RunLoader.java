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
package org.bridgedb.loader;

import java.io.IOException;
import org.bridgedb.linkset.LinksetLoader;
import org.bridgedb.linkset.transative.TransativeCreator;
import org.bridgedb.tools.metadata.validator.ValidationType;
import org.bridgedb.utils.BridgeDBException;
import org.bridgedb.utils.ConfigReader;
import org.bridgedb.utils.StoreType;
import org.openrdf.model.URI;
import org.openrdf.rio.RDFHandlerException;

/**
 *  This is a hack as it depends on the files being in the actact locations
 * @author Christian
 */
public class RunLoader {

    private static URI GENERATE_PREDICATE = null;
    private static URI USE_EXISTING_LICENSES = null;
    private static URI NO_DERIVED_BY = null;
    private static final boolean LOAD = true;
    
    public static void main(String[] args) throws BridgeDBException, RDFHandlerException, IOException  {
        ConfigReader.logToConsole();

        LinksetLoader linksetLoader = new LinksetLoader();

/*        String root = "C:/temp/linksets/";
        linksetLoader.clearExistingData(StoreType.LOAD);
        //1-2
        linksetLoader.load(root + "originals/ConceptWiki-Chembl2Targets.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //3-4
        linksetLoader.load(root + "originals/ConceptWiki-ChemSpider.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //5-6
        linksetLoader.load(root + "originals/ConceptWiki-DrugbankTargets.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //7-8
        linksetLoader.load(root + "originals/ConceptWiki-GO.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //9-10
        linksetLoader.load(root + "originals/ConceptWiki-MSH.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //11-12
        linksetLoader.load(root + "originals/ConceptWiki-NCIM.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //13-14
        linksetLoader.load(root + "originals/ConceptWiki-Pdb.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //15-16 
        linksetLoader.load(root + "originals/ConceptWiki-Swissprot.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //17-18 
        linksetLoader.load(root + "originals/Chembl13Id-ChemSpider.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //19-20 
        linksetLoader.load(root + "originals/Chembl13Molecule-Chembl13Id.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //21-22
        linksetLoader.load(root + "originals/Chembl13Targets-Enzyme.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //23-24 
        linksetLoader.load(root + "originals/Chembl13Targets-Swissprot.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //25-26 
        linksetLoader.load(root + "originals/ChemSpider-Chembl2Compounds.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //27-28
        linksetLoader.load(root + "originals/ChemSpider-DrugBankDrugs.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
    
        //29-30 
        TransativeCreator.createTransative(18,20,root + "transitive/ChemSpider-Chembl13Molecule-via-Chembl13Id.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/ChemSpider-Chembl13Molecule-via-Chembl13Id.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //31-33
        TransativeCreator.createTransative(3,29,root + "transitive/ConceptWiki-Chembl13Molecule-via-ChemSpider.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/ConceptWiki-Chembl13Molecule-via-ChemSpider.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //33-34
        TransativeCreator.createTransative(15,24,root + "transitive/ConceptWiki-Chembl13Targets-via-Swissprot.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/ConceptWiki-Chembl13Targets-via-Swissprot.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //35-36
        TransativeCreator.createTransative(3,25,root + "transitive/ConceptWiki-Chembl2Compounds-via-ChemSpider.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/ConceptWiki-Chembl2Compounds-via-ChemSpider.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        //37-38
        TransativeCreator.createTransative(3,27,root + "transitive/ConceptWiki-DrugBankDrugs-via-ChemSpider.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/ConceptWiki-DrugBankDrugs-via-ChemSpider.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        
        //39-40 (was 41-42)
        linksetLoader.load(root + "originals/Chemb13Targets-Chembl13id.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        
        //41-42 (was 43-44)
        TransativeCreator.createTransative(24,39,root + "transitive/Swissprot-Chembl13id-via-Chembl13Targets.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/Swissprot-Chembl13id-via-Chembl13Targets.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        
        //43-44 (was 45-46)
        TransativeCreator.createTransative(33,39,root + "transitive/ConceptWiki-Chembl13id-via-Chembl13Targets.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/ConceptWiki-Chembl13id-via-Chembl13Targets.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        
        //45-46 (was 47-48)
        TransativeCreator.createTransative(22,39,root + "transitive/Enzyme-Chembl13id-via-Chembl13Targets.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/Enzyme-Chembl13id-via-Chembl13Targets.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);

        linksetLoader.load("https://github.com/openphacts/ops-platform-setup/blob/master/void/chebi/chebi93_void.ttl", StoreType.LOAD, ValidationType.VOID);
        linksetLoader.load("https://github.com/openphacts/ops-platform-setup/blob/master/void/drugbank_void.ttl", StoreType.LOAD, ValidationType.VOID);
        linksetLoader.load("https://github.com/openphacts/Documentation/blob/master/datadesc/examples/chembl-rdf-void.ttl", StoreType.LOAD, ValidationType.VOID);
        linksetLoader.load("ftp://ftp.rsc-us.org/OPS/20130314/void_2013-03-14.ttl", StoreType.LOAD, ValidationType.VOID);
        //47-48 (was 49-50)
        linksetLoader.load("ftp://ftp.rsc-us.org/OPS/20130314/CHEBI/LINKSET_EXACT_CHEBI20130314.ttl.gz", StoreType.LOAD, ValidationType.LINKS);
        
        TransativeCreator.createTransative(48,4,root + "transitive/Chebi-ConceptWiki-via-ChemSpider.ttl", 
                StoreType.LOAD, GENERATE_PREDICATE, USE_EXISTING_LICENSES, NO_DERIVED_BY);
        linksetLoader.load(root + "transitive/Chebi-ConceptWiki-via-ChemSpider.ttl", StoreType.LOAD, ValidationType.LINKSMINIMAL);
        
        linksetLoader.load("ftp://ftp.rsc-us.org/OPS/20130314/CHEMBL/LINKSET_EXACT_CHEMBL20130314.ttl.gz", StoreType.LOAD, ValidationType.LINKS);
        linksetLoader.load("ftp://ftp.rsc-us.org/OPS/20130314/DRUGBANK/LINKSET_EXACT_DRUGBANK20130314.ttl.gz", StoreType.LOAD, ValidationType.LINKS);
        linksetLoader.load("ftp://ftp.rsc-us.org/OPS/20130314/MESH/LINKSET_EXACT_MESH20130314.ttl.gz", StoreType.LOAD, ValidationType.LINKS);
        linksetLoader.load("ftp://ftp.rsc-us.org/OPS/20130314/PDB/LINKSET_EXACT_PDB20130314.ttl.gz", StoreType.LOAD, ValidationType.LINKS);
        linksetLoader.load("ftp://ftp.rsc-us.org/OPS/20130314/PDB/LINKSET_RELATED_PDB20130314.ttl.gz", StoreType.LOAD, ValidationType.LINKS);
*/
        
    }

}
