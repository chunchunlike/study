package org.xi.quick.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.xi.quick.codegenerator.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xi on 6/1/2017.
 */
public class GsonTest {
    public static void main(String[] args) {
        TemplateTest<List<UserModel>> temp = new TemplateTest<>();
        List<UserModel> list = new ArrayList<>();
        for(int i = 0; i<10; i++){
            UserModel model = new UserModel();
            model.setUsername("xi");
            model.setName("郗世豪");
            model.setAddress("山西省临汾市尧都区");
            list.add(model);
        }
        temp.setT(list);
        TestConvert<List<UserModel>> type = new TestConvert<>();
        String json =type.toJson(temp);
        System.out.println(json);
        TemplateTest<List<UserModel>> temp2 = type.fromJson(json);

        for(UserModel i : temp2.getT()){
            System.out.println(i.getAddress()+i.getName()+i.getUsername());
        }
        TemplateTest.findTest(10);
    }

}

class TemplateTest<T>{
    private T t;
    public void setT(T t) {
        this.t = t;
    }
    public T getT(){
        return t;
    }

    public static <R> R findTest(R r){
        return r;
    }
}

class TestConvert<T> {
    public String toJson(TemplateTest<T> t) {

        Gson gson = new GsonBuilder().create();
        return gson.toJson(t);
    }

    public TemplateTest<T> fromJson(String s) {
        Gson gson = new GsonBuilder().create();
        TemplateTest<T> p = gson.fromJson(s, new TypeToken<TemplateTest<T>>() {
        }.getType());
        return p;
    }
}