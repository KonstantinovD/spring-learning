package ch6_hibernate.common;

import lombok.experimental.UtilityClass;


@UtilityClass
public class CommonUtils {

    public static final String LIQUIBASE_CHANGELOG_PROPERTY_NAME = "spring.liquibase.change-log";
    public static final String DATASOURCE_URL_PROPERTY_NAME = "spring.datasource.url";
    public static final String LIQUIBASE_CHANGELOG_PATH = "classpath:%s/changelog.yaml";
    public static final String DATASOURCE_URL = "jdbc:h2:file:%s\\runtime\\embedded-db\\%s";
    public static final String USER_DIR_PROPERTY_NAME = "user.dir";

    public static void setProperties(String configName) {
        String projectDir = System.getProperty(USER_DIR_PROPERTY_NAME);
        System.setProperty(DATASOURCE_URL_PROPERTY_NAME, String.format(DATASOURCE_URL, projectDir, configName));
        System.setProperty(LIQUIBASE_CHANGELOG_PROPERTY_NAME, String.format(LIQUIBASE_CHANGELOG_PATH, configName));
    }

}
