package io.opsit.qandda;

import java.util.List;

public class Component implements IComponent {
    protected String name;
    protected String displayName;
    protected String groupName;
    protected String version;
    protected String license;
    protected String author;
    protected String packager;    
    protected String description;
    protected String lang;
    protected String langVersion;
    protected String scope;;
    protected List<Component> dependencies;

    
    
    @Override
    public String getLang() {
        return lang;
    }

    @Override
    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String getLangVersion() {
        return langVersion;
    }

    @Override
    public void setLangVersion(String langVersion) {
        this.langVersion = langVersion;
    }
    
    @Override
    public String getLicense() {
        return license;
    }

    @Override
    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getPackager() {
        return packager;
    }

    @Override
    public void setPackager(String packager) {
        this.packager = packager;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public List<Component> getDependencies() {
        return dependencies;
    }

    @Override
    public void setDependencies(List<Component> dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }

    private void dumpField(StringBuilder b, String name, Object value) {
        if (value != null) {
            if (b.length() > 1) {
                b.append(", ");
            }
            b.append(name).append("=").append(value);
        }
    }

    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("[");
        dumpField(b,"name", this.getName());
        dumpField(b,"groupName", this.getGroupName());        
        dumpField(b,"version", this.getVersion());
        dumpField(b,"displayName", this.getDisplayName());        
        dumpField(b,"description", this.getDescription());
        dumpField(b,"packager", this.getPackager());
        dumpField(b,"scope", this.getScope());
        dumpField(b,"author", this.getAuthor());
        dumpField(b,"dependencies", this.getDependencies());
        b.append("]");
        return b.toString();
    }
}


