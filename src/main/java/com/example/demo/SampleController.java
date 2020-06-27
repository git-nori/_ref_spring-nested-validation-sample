package com.example.demo;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SampleController {

    @Autowired
    private Validator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest req) {
        binder.setValidator(validator);
    }


    @PostMapping
    @ResponseBody
    public Object post(@Validated({Default.class, B.class}) SampleForm form, BindingResult result) {
        System.out.println(result.getFieldErrors());
        return result.getFieldErrors();
    }

    @PostMapping("/hand")
    @ResponseBody
    public Object postHand(SampleForm form, BindingResult result) {
        ((SmartValidator)validator).validate(form, result);
        System.out.println(result.getFieldErrors());
        return result.getFieldErrors();
    }
}
