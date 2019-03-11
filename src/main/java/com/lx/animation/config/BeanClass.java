package com.lx.animation.config;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

public class BeanClass implements EmbeddedValueResolverAware,MyInterface{
    private StringValueResolver embeddedValueResolverAware;
    private List<String> list=null;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.embeddedValueResolverAware = stringValueResolver;
    }

    public StringValueResolver getEmbeddedValueResolverAware() {
        return embeddedValueResolverAware;
    }

    public int div(int a, int b) {
        return a / b;
    }

    @Override
    public void setQccc(List<String> list) {
        this.list=list;
    }
    public void getQcc(){
        System.out.println(this.list);
    }
}
