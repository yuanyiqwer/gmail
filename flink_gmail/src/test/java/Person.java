/**
 * @ClassName Person
 * @Author yy
 * @Description
 * @Date 2021/9/17 11:20
 * @Version 1.0
 **/
public class Person {
    String uname;
    String psword;

    public Person(String uname, String psword) {
        this.uname = uname;
        this.psword = psword;
    }

    public Person() {
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPsword() {
        return psword;
    }

    public void setPsword(String psword) {
        this.psword = psword;
    }
}
