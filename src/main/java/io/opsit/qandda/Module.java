package io.opsit.qandda;

public class Module {
    protected String name;
    protected String groupName;
    protected String version;
    
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLangVersion() {
        return langVersion;
    }

    public void setLangVersion(String langVersion) {
        this.langVersion = langVersion;
    }

    protected String packager;    
    protected String description;
    protected String lang;
    protected String langVersion;
    protected String scope;;
    
    protected Module[] dependencies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackager() {
        return packager;
    }

    public void setPackager(String packager) {
        this.packager = packager;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Module[] getDependencies() {
        return dependencies;
    }

    public void setDependencies(Module[] dependencies) {
        this.dependencies = dependencies;
    }
}


