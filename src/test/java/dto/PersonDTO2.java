package dto;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2019-04-09 15:05
 */
public class PersonDTO2 extends BaseDTO {

    /** 姓名*/
    private String name;

    /** 性别 0-女 1-男*/
    private String sex;

    /** 年龄*/
    private int age;

    /** 身高*/
    private double height;

    /** 是否已婚*/
    private boolean hasMarry;

    /** 家庭住址*/
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isHasMarry() {
        return hasMarry;
    }

    public void setHasMarry(boolean hasMarry) {
        this.hasMarry = hasMarry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", hasMarry=" + hasMarry +
                ", address='" + address + '\'' +
                '}';
    }
}
