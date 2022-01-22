package io.opsit.qandda;

import java.util.List;

public interface IModule {
    public String getLang();
    public void setLang(String lang);
    
    public String getLangVersion();
    public void setLangVersion(String langVersion);

    public String getName();
    public void setName(String name);

    public String getLicense();
    public void setLicense(String license);
    
    public String getDescription();
    public void setDescription(String description);

    public String getGroupName();
    public void setGroupName(String groupName);

    public String getVersion();
    public void setVersion(String version);

    public String getPackager();
    public void setPackager(String packager);

    public String getScope();
    public void setScope(String scope);

    public List<Module> getDependencies();
    public void setDependencies(List<Module> dependencies);

    public String getAuthor();
    public void setAuthor(String author);
}
