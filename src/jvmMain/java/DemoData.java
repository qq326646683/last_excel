import com.alibaba.excel.annotation.ExcelProperty;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DemoData implements Serializable {
//    @ExcelProperty("名字")
    private String name;
//    @ExcelProperty("年龄")
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


}