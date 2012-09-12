/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bridgedb.metadata;

import org.junit.Ignore;
import javax.xml.datatype.DatatypeConfigurationException;
import org.bridgedb.metadata.utils.Reporter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Christian
 */
public class DataSet2MetaDataTest extends MetaDataTestBase{
    
    public DataSet2MetaDataTest() throws DatatypeConfigurationException{        
    }
    
    @Test
    public void testHasRequiredValues() throws MetaDataException{
        Reporter.report("HasRequiredValues");
        MetaDataCollection metaData = new MetaDataCollection(loadDataSet2());
        checkRequiredValues(metaData, RequirementLevel.MUST);
        assertFalse(metaData.hasRequiredValues(RequirementLevel.MAY));
    } 

    @Test
    public void testHasCorrectTypes() throws MetaDataException{
        Reporter.report("HasCorrectTypes");
        MetaDataCollection metaData = new MetaDataCollection(loadDataSet2());
        checkCorrectTypes(metaData);
    }

    @Test
    public void testAllStatementsUsed() throws MetaDataException{
        Reporter.report("AllStatementsUsed");
        MetaDataCollection metaData = new MetaDataCollection(loadDataSet2());
        checkAllStatementsUsed(metaData);
    }

}