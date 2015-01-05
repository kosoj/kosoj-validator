package com.kosoj.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: machain
 * Date: 30.8.13
 * Time: 10:42
 */
public class Validation {

    private String id;
    private ValidationSeverity severity;
    private ValidationType type;
    private String message;
    private String attribute;
    private List<ValidationProperty> properties;


    // CONSTRUCTOR
    public Validation() {
        this.severity = ValidationSeverity.ERROR;
    }


    // ACCESSORS
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public ValidationType getType() {
        return type;
    }

    public void setType(final ValidationType type) {
        this.type = type;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(final String attribut) {
        this.attribute = attribut;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public List<ValidationProperty> getProperties() {
        return properties;
    }

    public List<ValidationProperty> getPropertiesExtended() {
        final List<ValidationProperty> result = new ArrayList<ValidationProperty>();
        // id
        if (StringUtils.isNotBlank(id)) {
            result.add(new ValidationProperty("CODE", id));
        }
        // type
        if (type != null) {
            result.add(new ValidationProperty("TYPE", type.name()));
        }
        // attribute
        if (StringUtils.isNotBlank(attribute)) {
            result.add(new ValidationProperty("ATTRIBUTE", attribute));
        }
        // rest
        if (CollectionUtils.isNotEmpty(properties)) {
            result.addAll(properties);
        }

        return result;
    }

    public void setProperties(final List<ValidationProperty> properties) {
        this.properties = properties;
    }

    public void addProperty(final String key, final String value) {
        if (properties == null) {
            properties = new ArrayList<ValidationProperty>();
        }
        properties.add(new ValidationProperty(key, value));
    }

    public ValidationSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(final ValidationSeverity severity) {
        this.severity = severity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("type", type)
                .append("attribut", attribute)
                .append("message", message)
                .append("properties", properties)
                .toString();
    }
}
