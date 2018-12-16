package com.epam.hrynyshyn.filters.security.configuration;


import com.epam.hrynyshyn.exceptions.ConfigurationFailureException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.epam.hrynyshyn.constants.Constants.ErrorMessages.SECURITY_CONFIGURATION_FAILURE;
import static com.epam.hrynyshyn.constants.Constants.Security.CONSTRAINT;
import static com.epam.hrynyshyn.constants.Constants.Security.ROLE;
import static com.epam.hrynyshyn.constants.Constants.Security.URL_PATTERN;

public class ConfigurationFileParser {
    private static Logger logger = Logger.getLogger(ConfigurationFileParser.class);
    private Document document;

    public ConfigurationFileParser(String configurationFileName) {
        File file = new File(configurationFileName);
        logger.info(file);
        this.document = createDocument(file);
    }

    private Document createDocument(File inputFile) {
        Document document;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new ConfigurationFailureException(SECURITY_CONFIGURATION_FAILURE, e);
        }
        return document;
    }

    public List<SecurityConstraint> getSecurityConstraints() {
        List<SecurityConstraint> constraints = new ArrayList<>();
        NodeList constraintNodes = getConstraintNodes(document);
        for (int i = 0; i < constraintNodes.getLength(); i++) {
            Node constraintNode = constraintNodes.item(i);
            if (isElement(constraintNode)) {
                Element element = (Element) constraintNode;
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUrlPatter(getUrlPattern(element));
                constraint.setRoles(getRoles(element));
                constraints.add(constraint);
            }
        }
        return constraints;
    }

    private NodeList getConstraintNodes(Document document) {
        return document.getElementsByTagName(CONSTRAINT);
    }

    private String getUrlPattern(Element element) {
        return element.getElementsByTagName(URL_PATTERN).item(0).getTextContent();
    }

    private List<String> getRoles(Element element) {
        List<String> roles = new ArrayList<>();
        NodeList roleNodes = element.getElementsByTagName(ROLE);
        for (int j = 0; j < roleNodes.getLength(); j++) {
            String role = element.getElementsByTagName(ROLE).item(j).getTextContent();
            roles.add(role);
        }
        return roles;
    }

    private boolean isElement(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE;
    }
}