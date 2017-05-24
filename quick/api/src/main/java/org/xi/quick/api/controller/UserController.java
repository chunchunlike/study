package org.xi.quick.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xi.quick.model.UserModel;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public UserModel getById(Integer id) {
        UserModel model = new UserModel();
        model.setUsername("xi");
        model.setName("郗世豪");
        model.setAddress("山西省临汾市尧都区");
        return model;
    }
}
