package selab.threetier.logic;

import selab.threetier.storage.Storage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Task extends Entity implements Comparable<Task>{
    private String title;
    private Date start;
    private Date end;

    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }

    public void setStart(Date value) { start = value; }
    public String getStartDate() {
        return new SimpleDateFormat("YYYY-MM-DD").format(start);
    }
    public String getStartTime() {
        return new SimpleDateFormat("HH:mm:ss").format(start);
    }

    public void setEnd(Date value) { end = value; }
    public String getEndTime() {
        return new SimpleDateFormat("HH:mm:ss").format(end);
    }

    public void save() {
        Storage.getInstance().getTasks().addOrUpdate(this);
    }

    public void delete(){
        Storage.getInstance().getTasks().delete( this);
    }
    public static ArrayList<Task> getAll() {
        return Storage.getInstance().getTasks().getAll();
    }
    public boolean hasOverlap(Task task){
        if ((task.start.after(this.end)) || (task.end.before(this.start))){
            return false;
        }
        return true;
    }
    public boolean manteghi(){
        if (this.end.after(this.start)){
            return true;
        }
        return false;
    }
    public int compareTo(Task task){
        return task.start.compareTo(this.start);
    }
}
