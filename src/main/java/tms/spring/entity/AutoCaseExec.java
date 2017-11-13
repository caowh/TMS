package tms.spring.entity;

/**
 * Created by user on 2017/11/10.
 */
public class AutoCaseExec {

    private Long id;
    private int isSendToTestlink;
    private String planname;
    private String ids;
    private String content;
    private Long create_id;
    private String statement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIsSendToTestlink() {
        return isSendToTestlink;
    }

    public void setIsSendToTestlink(int isSendToTestlink) {
        this.isSendToTestlink = isSendToTestlink;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreate_id() {
        return create_id;
    }

    public void setCreate_id(Long create_id) {
        this.create_id = create_id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
