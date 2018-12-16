package com.epam.hrynyshyn.filters.security.configuration;

import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ConfigurationFileParserTest {
    ConfigurationFileParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new ConfigurationFileParser("src/main/webapp/META-INF/security.xml");
    }

    @Test
    public void getSecurityConstraints() throws Exception {
        List<SecurityConstraint> constraints = parser.getSecurityConstraints();
        System.out.println(constraints);
    }

}