package misc.quartz.task;

/**
 *  Created by arindam on 20/1/17.
 */
public class DemoTask {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;


    public void printMe(){
        System.out.println("Demo Task ...........");
    }
}
