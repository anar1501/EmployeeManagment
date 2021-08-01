package az.company.employee.model.concrets;

import az.company.employee.model.abstracts.Entity;

public class Permission implements Entity {

    private int id;

    private String pageName;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Permission{" + "id=" + id + ", pageName=" + pageName + '}';
    }

}
