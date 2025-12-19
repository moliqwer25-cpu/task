import java.util.ArrayList;
import java.util.List;

// 1. Ортақ интерфейс - Бөлшек пен Бүтінге ортақ әрекеттер
interface TaskComponent {
    void display(String indent);
}

// 2. Leaf
class SimpleTask implements TaskComponent {
    private String title;

    public SimpleTask(String title) {
        this.title = title;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "• " + title);
    }
}

// 3. Composite
class Project implements TaskComponent {
    private String name;
    private List<TaskComponent> children = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public void add(TaskComponent component) {
        children.add(component);
    }

    public void remove(TaskComponent component) {
        children.remove(component);
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "[ЖОБА: " + name + "]");
        // Рекурсивті түрде барлық ішкі элементтерді шығару
        for (TaskComponent component : children) {
            component.display(indent + "  ");
        }
    }
}


public class Task {
    public static void main(String[] args) {

        Project mainProject = new Project("Веб-сайт әзірлеу");


        SimpleTask design = new SimpleTask("Дизайн жасау");
        SimpleTask testing = new SimpleTask("Тестілеу");


        Project development = new Project("Бағдарламалау");
        development.add(new SimpleTask("Frontend (React)"));
        development.add(new SimpleTask("Backend (Spring Boot)"));


        mainProject.add(design);
        mainProject.add(development);
        mainProject.add(testing);


        System.out.println("Ағымдағы тапсырмалар тізімі:");
        mainProject.display("");
    }
}