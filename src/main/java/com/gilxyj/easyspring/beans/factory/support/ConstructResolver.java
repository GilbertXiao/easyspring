package com.gilxyj.easyspring.beans.factory.support;

import com.gilxyj.easyspring.beans.BeanDefinition;
import com.gilxyj.easyspring.beans.ConstructorArgument.ValueHoder;
import com.gilxyj.easyspring.beans.SimpleTypeConverter;
import com.gilxyj.easyspring.beans.TypeMismatchException;
import com.gilxyj.easyspring.beans.factory.BeanCreationException;
import com.gilxyj.easyspring.beans.factory.config.ConfigurableBeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * @program: easyspring
 * @description:
 * @作者 飞码录
 * @微信公众号 飞码录
 * @网站 http://www.codesboy.cn
 * @国际站 http://www.codesboy.com
 * @微信 gilbertxy
 * @GitHub https://github.com/GilbertXiao
 * @Gitee https://gitee.com/gilbertxiao
 * @create: 2020-08-29 16:58
 **/
public class ConstructResolver {

    private static final Logger LOGGER= LoggerFactory.getLogger(ConstructResolver.class);

    private final ConfigurableBeanFactory configurableBeanFactory;

    public ConstructResolver(ConfigurableBeanFactory configurableBeanFactory) {
        this.configurableBeanFactory = configurableBeanFactory;
    }

    public Object autowireConstructor(BeanDefinition bd) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass = null;
        try {
            beanClass = this.configurableBeanFactory.getBeanClassLoader().loadClass(bd.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException( bd.getId(), "Instantiation of bean failed, can't resolve class", e);
        }
        Constructor<?>[] classConstructors = beanClass.getConstructors();
        int argumentCount = bd.getConstructorArgument().getArgumentCount();
        SimpleTypeConverter simpleTypeConverter = new SimpleTypeConverter();
        List<ValueHoder> argumentValues = bd.getConstructorArgument().getArgumentValues();
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(configurableBeanFactory);
        for (int i = 0; i < classConstructors.length; i++) {
            Class<?>[] parameterTypes = classConstructors[i].getParameterTypes();
            int parameterLen = parameterTypes.length;
            if (parameterLen!=argumentCount) {
                continue;
            }
            argsToUse = new Object[parameterLen];
            boolean matchType = this.valuesMatchType(parameterTypes, argumentValues, argsToUse, simpleTypeConverter, resolver);
            if (matchType) {
                constructorToUse = classConstructors[i];
                break;
            }
        }
        //can not find the match constructor
        if (constructorToUse == null) {
            throw new BeanCreationException( bd.getId(), "can't find a apporiate constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new BeanCreationException( bd.getId(), "can't find a create instance using "+constructorToUse);
        }
    }

    private boolean valuesMatchType(Class<?>[] parameterTypes, List<ValueHoder> argumentValues, Object[] argsToUse, SimpleTypeConverter simpleTypeConverter, BeanDefinitionValueResolver resolver) {

        for (int i = 0; i < argumentValues.size(); i++) {
            ValueHoder valueHoder = argumentValues.get(i);
            try {
                Object resolveValue = resolver.resolveValueIfNeccessary(valueHoder.getValue());
                Object convertValue = simpleTypeConverter.convertIfNecessary(resolveValue, parameterTypes[i]);
                argsToUse[i] = convertValue;
            } catch (TypeMismatchException e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
                return false;
            }


        }


        return true;
    }


}
