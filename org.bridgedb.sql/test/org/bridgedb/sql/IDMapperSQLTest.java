/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bridgedb.sql;

import java.util.Date;
import org.bridgedb.IDMapperException;
import org.bridgedb.linkset.IDMapperAndLinkListenerTest;
import org.junit.BeforeClass;

/**
 * 
 * @author Christian
 */
public class IDMapperSQLTest extends IDMapperAndLinkListenerTest {
    
    private static final String CREATOR1 = "testCreateProvenance";
    private static final String PREDICATE1 = "testMapping";
    private static final long CREATION1 = new Date().getTime();
    private static IDMapperSQL iDMapperSQL;

    @BeforeClass
    public static void setupIDMapper() throws IDMapperException{
        connectionOk = false;
        SQLAccess sqlAccess = TestIDSqlFactory.createTestSQLAccess();
        connectionOk = true;
        iDMapperSQL = new IDMapperSQL(true, sqlAccess);
        idMapper = iDMapperSQL;
        //provenanceFactory = iDMapperSQL;
        listener = iDMapperSQL;     
        defaultLoadData();
    }
            
}
