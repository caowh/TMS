package tms.spring.entity;

/**
 * Created by user on 2017/11/21.
 */
public class PostManAsso {

    private Long id;
    private String readme_url;
    private String env_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReadme_url() {
        return readme_url;
    }

    public void setReadme_url(String readme_url) {
        this.readme_url = readme_url;
    }

    public String getEnv_url() {
        return env_url;
    }

    public void setEnv_url(String env_url) {
        this.env_url = env_url;
    }
}
