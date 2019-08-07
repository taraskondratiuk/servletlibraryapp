package main.java.ua.gladiator.model.entity.builders;

import main.java.ua.gladiator.model.entity.Attribute;

public final class AttributeBuilder {
    private Long id;
    private String name;

    private AttributeBuilder() {
    }

    public static AttributeBuilder builder() {
        return new AttributeBuilder();
    }

    public AttributeBuilder buildId(Long id) {
        this.id = id;
        return this;
    }

    public AttributeBuilder buildName(String name) {
        this.name = name;
        return this;
    }

    public Attribute build() {
        Attribute attribute = new Attribute();
        attribute.setId(id);
        attribute.setName(name);
        return attribute;
    }
}
