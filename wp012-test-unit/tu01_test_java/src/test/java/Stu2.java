import lombok.Data;

@Data
public class Stu2 {
    private Integer id;
    private String name;
    private Integer age;

    public Stu2(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Stu2() {
    }

    @Override
    public String toString() {
        return "Stu2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
