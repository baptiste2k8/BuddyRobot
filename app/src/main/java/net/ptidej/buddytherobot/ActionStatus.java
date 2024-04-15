package net.ptidej.buddytherobot;

public final class ActionStatus {
    private static ActionStatus INSTANCE;

    private String status=null;
    private ActionStatus(){

    }
    public static ActionStatus getInstance(){
        if(INSTANCE==null){
            INSTANCE=new ActionStatus();
        }
        return INSTANCE;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
