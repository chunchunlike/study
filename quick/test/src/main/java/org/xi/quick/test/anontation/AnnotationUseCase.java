package org.xi.quick.test.anontation;

public class AnnotationUseCase {

    @TestAnnotation(id=0,name = "field name")
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @TestAnnotation(id=1,name = "function sout")
    public void Sout(String s) {
        System.out.println(s);
    }
}
