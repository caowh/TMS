package tms.spring.entity;

/**
 * Created by user on 2017/9/7.
 */
public class PlanHelper {

    private String planName;
    private String uut;
    private String version;
    private String environment;
    private String leader;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getUut() {
        return uut;
    }

    public void setUut(String uut) {
        this.uut = uut;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
}
