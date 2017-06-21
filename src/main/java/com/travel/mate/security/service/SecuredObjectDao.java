package com.travel.mate.security.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class SecuredObjectDao {

	// url형식의 리소스 정보를 조회하는 default 쿼리
    public static final String DEF_ROLES_AND_URL_QUERY =
      "SELECT A.RESOURCE_PATTERN AS URL, B.AUTHORITY AS AUTHORITY "
      + "FROM SECURED_RESOURCES A, SECURED_RESOURCES_ROLE B "
      + "WHERE A.RESOURCE_ID = B.RESOURCE_ID "
      + "AND A.RESOURCE_TYPE = 'url' "
      + "ORDER BY A.SORT_ORDER ";

    //method 형식의 리소스 정보를 조회하는 default 쿼리
    public static final String DEF_ROLES_AND_METHOD_QUERY =
      "SELECT A.RESOURCE_PATTERN AS METHOD, B.AUTHORITY AS AUTHORITY "
          + "FROM SECURED_RESOURCES A, SECURED_RESOURCES_ROLE B "
          + "WHERE A.RESOURCE_ID = B.RESOURCE_ID "
          + "AND A.RESOURCE_TYPE = 'method' "
          + "ORDER BY A.SORT_ORDER ";


    private String sqlRolesAndUrl;

    private String sqlRolesAndMethod;

/*    private String sqlRegexMatchedRequestMapping;*/

    public SecuredObjectDao() {
        this.sqlRolesAndUrl = DEF_ROLES_AND_URL_QUERY;
        this.sqlRolesAndMethod = DEF_ROLES_AND_METHOD_QUERY;
    }

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate =
            new NamedParameterJdbcTemplate(dataSource);
    }

    /*롤에 대한 URL 정보를 가져오는 SQL을 얻어온다.*/
    public String getSqlRolesAndUrl() {
        return sqlRolesAndUrl;
    }

    /*롤에대한 URL 정보를 가져오는 SQL을 설정한다.*/
    public void setSqlRolesAndUrl(String sqlRolesAndUrl) {
        this.sqlRolesAndUrl = sqlRolesAndUrl;
    }

    public String getSqlRolesAndMethod() {
        return sqlRolesAndMethod;
    }

    public void setSqlRolesAndMethod(String sqlRolesAndMethod) {
        this.sqlRolesAndMethod = sqlRolesAndMethod;
    }

    /*권한과 리소스 정보를 얻어온다.*/
    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndResources(String resourceType) throws Exception {

        LinkedHashMap<Object, List<ConfigAttribute>> resourcesMap = new LinkedHashMap<Object, List<ConfigAttribute>>();

        String sqlRolesAndResources;
        boolean isResourcesUrl = true;
        //리소스가 method 형식일 경우
        if ("method".equals(resourceType)) {
            sqlRolesAndResources = getSqlRolesAndMethod();
            isResourcesUrl = false;
        } 
        //리소스가 url 형식일 경우
        else {
            sqlRolesAndResources = getSqlRolesAndUrl();
        }

        List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate.queryForList(sqlRolesAndResources, new HashMap<String, String>());

        Iterator<Map<String, Object>> itr = resultList.iterator();
        Map<String, Object> tempMap;
        String preResource = null;
        String presentResourceStr;
        Object presentResource;
        while (itr.hasNext()) {
            tempMap = itr.next();

            presentResourceStr = (String) tempMap.get(resourceType);
            // url 인 경우 RequestKey 형식의 key를 Map에 담아야 함
            presentResource = isResourcesUrl ? new AntPathRequestMatcher(presentResourceStr) : presentResourceStr;
            List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();

            /*이미 requestMap 에 해당 Resource 에 대한 Role 이 하나 이상 맵핑되어 있었던 경우, 
            sort_order 는 Resource 에 대해 매겨지므로 같은 Resource 에 대한 Role 맵핑은 연속으로 조회됨.
            해당 맵핑 Role List (SecurityConfig) 의 데이터를 재활용하여 새롭게 데이터 구축*/
            if (preResource != null && presentResourceStr.equals(preResource)) {
                List<ConfigAttribute> preAuthList = resourcesMap.get(presentResource);
                Iterator<ConfigAttribute> preAuthItr = preAuthList.iterator();
                while (preAuthItr.hasNext()) {
                    SecurityConfig tempConfig = (SecurityConfig) preAuthItr.next();
                    configList.add(tempConfig);
                }
            }

            configList.add(new SecurityConfig((String) tempMap.get("authority")));
            
            /*만약 동일한 Resource 에 대해 한개 이상의 Role 맵핑 추가인 경우 
            이전 resourceKey 에 현재 새로 계산된 Role 맵핑 리스트로 덮어쓰게 됨.*/
            resourcesMap.put(presentResource, configList);

            // 이전 resource 비교위해 저장
            preResource = presentResourceStr;
        }
                
        return resourcesMap;
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
        return getRolesAndResources("url");
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndMethod() throws Exception {
        return getRolesAndResources("method");
    }

}

