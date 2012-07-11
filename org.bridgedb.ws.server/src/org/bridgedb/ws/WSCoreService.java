package org.bridgedb.ws;

import org.bridgedb.ws.bean.XrefBean;
import org.bridgedb.ws.bean.XrefMapBean;
import org.bridgedb.ws.bean.CapabilitiesBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.bridgedb.DataSource;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperCapabilities;
import org.bridgedb.IDMapperException;
import org.bridgedb.Xref;
import org.bridgedb.ws.bean.DataSourceBean;
import org.bridgedb.ws.bean.DataSourceBeanFactory;
import org.bridgedb.ws.bean.FreeSearchSupportedBean;
import org.bridgedb.ws.bean.MappingSupportedBean;
import org.bridgedb.ws.bean.MappingSupportedBeanFactory;
import org.bridgedb.ws.bean.PropertyBean;
import org.bridgedb.ws.bean.XrefBeanFactory;
import org.bridgedb.ws.bean.XrefExistsBean;
import org.bridgedb.ws.bean.XrefExistsBeanFactory;
import org.bridgedb.ws.bean.XrefMapBeanFactory;

@Path("/")
public class WSCoreService implements WSCoreInterface {

    protected IDMapper idMapper;

    /**
     * Defuault constuctor for super classes.
     * 
     * Super classes will have the responsibilites of setting up the idMapper.
     */
    protected WSCoreService(){
    }
    
    public WSCoreService(IDMapper idMapper) {
        this.idMapper = idMapper;
    }
        
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/getSupportedSrcDataSources")
    @Override
    public List<DataSourceBean> getSupportedSrcDataSources() throws IDMapperException {
        ArrayList<DataSourceBean> sources = new ArrayList<DataSourceBean>();
        System.err.println(idMapper);
        IDMapperCapabilities capabilities = idMapper.getCapabilities();
        Set<DataSource> dataSources = capabilities.getSupportedSrcDataSources();
        for (DataSource dataSource:dataSources){
            DataSourceBean bean = DataSourceBeanFactory.asBean(dataSource);
            sources.add(bean);
        }
        return sources;
    } 

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/freeSearch")
    @Override
    public List<XrefBean> freeSearch(
            @QueryParam("text") String text,
            @QueryParam("limit") String limitString) throws IDMapperException {
        if (text == null) throw new IDMapperException("text parameter missing");
        if (limitString == null || limitString.isEmpty()){
            Set<Xref> mappings = idMapper.freeSearch(text, Integer.MAX_VALUE);
            return setXrefToListXrefBeans(mappings);
        } else {
            int limit = Integer.parseInt(limitString);
            Set<Xref> mappings = idMapper.freeSearch(text,limit);
            return setXrefToListXrefBeans(mappings);
        }
    } 

    protected List<XrefBean> setXrefToListXrefBeans(Set<Xref> xrefs){
       ArrayList<XrefBean> results = new ArrayList<XrefBean>();
        for (Xref xref:xrefs){
           results.add(XrefBeanFactory.asBean(xref));
        }
        return results;        
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/mapID")
    @Override
    public List<XrefMapBean> mapID(
            @QueryParam("id") List<String> id,
            @QueryParam("code") List<String> scrCode,
            @QueryParam("targetCode") List<String> targetCodes) throws IDMapperException {
        if (id == null) throw new IDMapperException("id parameter missing");
        if (id.isEmpty()) throw new IDMapperException("id parameter missing");
        if (scrCode == null) throw new IDMapperException("code parameter missing");
        if (scrCode.isEmpty()) throw new IDMapperException("code parameter missing");
        if (id.size() != scrCode.size()) throw new IDMapperException("Must have same number of id and code parameters");
        ArrayList<Xref> srcXrefs = new ArrayList<Xref>();
        for (int i = 0; i < id.size() ;i++){
            DataSource dataSource = DataSource.getBySystemCode(scrCode.get(i));
            Xref source = new Xref(id.get(i), dataSource);
            srcXrefs.add(source);
        }
        DataSource[] targetDataSources = new DataSource[targetCodes.size()];
        for (int i=0; i< targetCodes.size(); i++){
             targetDataSources[i] = DataSource.getBySystemCode(targetCodes.get(i));
        }
        
        Map<Xref, Set<Xref>>  mappings = idMapper.mapID(srcXrefs, targetDataSources);
        ArrayList<XrefMapBean> results = new ArrayList<XrefMapBean>();
        for (Xref source:mappings.keySet()){
            for (Xref target:mappings.get(source)){
                results.add(XrefMapBeanFactory.asBean(source, target));
            }
        }
        return results;
    } 

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/xrefExists")
    @Override
    public XrefExistsBean xrefExists( 
            @QueryParam("id") String id,
            @QueryParam("code") String scrCode) throws IDMapperException {
        if (id == null) throw new IDMapperException ("\"id\" parameter can not be null");
        if (scrCode == null) throw new IDMapperException ("\"code\" parameter can not be null");            
        DataSource dataSource = DataSource.getBySystemCode(scrCode);
        Xref source = new Xref(id, dataSource);
        return XrefExistsBeanFactory.asBean(source, idMapper.xrefExists(source));
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/getSupportedTgtDataSources")
    @Override
    public List<DataSourceBean> getSupportedTgtDataSources() throws IDMapperException {
        ArrayList<DataSourceBean> targets = new ArrayList<DataSourceBean>();
        Set<DataSource> dataSources = idMapper.getCapabilities().getSupportedSrcDataSources();
        for (DataSource dataSource:dataSources){
            DataSourceBean bean = DataSourceBeanFactory.asBean(dataSource);
            targets.add(bean);
        }
        return targets;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/isFreeSearchSupported")
    @Override
    public FreeSearchSupportedBean isFreeSearchSupported() {
        return new FreeSearchSupportedBean(idMapper.getCapabilities().isFreeSearchSupported());
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/isMappingSupported")
    @Override
    public MappingSupportedBean isMappingSupported(
            @QueryParam("sourceCode") String sourceCode, 
            @QueryParam("targetCode") String targetCode) throws IDMapperException {
        if (sourceCode == null) throw new IDMapperException ("\"sourceCode\" parameter can not be null");
        if (targetCode == null) throw new IDMapperException ("\"targetCode\" parameter can not be null");
        DataSource src = DataSource.getBySystemCode(sourceCode);
        DataSource tgt = DataSource.getBySystemCode(targetCode);
        return MappingSupportedBeanFactory.asBean(src, tgt, idMapper.getCapabilities().isMappingSupported(src, tgt));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/property/{key}")
    @Override
    public PropertyBean getProperty(@PathParam("key")String key) {
        String property = idMapper.getCapabilities().getProperty(key);
        if (property == null){
            property = "key was \"" + key + "\"";
        }
        if (property == null){
            property = "key was \"" + key + "\"";
        }
        return new PropertyBean(key, property);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/getKeys")
    @Override
    public List<PropertyBean> getKeys() {
        Set<String> keys = idMapper.getCapabilities().getKeys();
        ArrayList<PropertyBean> results = new ArrayList<PropertyBean>();
        IDMapperCapabilities idMapperCapabilities = idMapper.getCapabilities();
        for (String key:keys){
            results.add(new PropertyBean(key, idMapperCapabilities.getProperty(key)));
        }
        return results;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Path("/getCapabilities")
    @Override
    public CapabilitiesBean getCapabilities()  {
        return new CapabilitiesBean(idMapper.getCapabilities());
    }


}
