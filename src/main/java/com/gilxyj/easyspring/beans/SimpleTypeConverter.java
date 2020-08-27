package com.gilxyj.easyspring.beans;

import com.gilxyj.easyspring.beans.propertyeditor.CustomBooleanEditor;
import com.gilxyj.easyspring.beans.propertyeditor.CustomNumberEditor;
import com.gilxyj.easyspring.util.ClassUtils;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
 * @create: 2020-08-26 00:15
 **/
public class SimpleTypeConverter implements TypeConverter{

    private Map<Class<?>, PropertyEditorSupport> editorSupportMap = new ConcurrentHashMap<>();

    @Override
    public <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException {
        if (ClassUtils.isAssignableValue(requiredType,value)) {
            return ((T) value);
        }else{
            if (value instanceof String) {
                PropertyEditor editor = findDefaultEditor(requiredType);
                try {
                    editor.setAsText(((String) value));
                } catch (IllegalArgumentException e) {
                    throw new TypeMismatchException(value,requiredType);
                }
                return ((T) editor.getValue());
            }else {
                throw new RuntimeException("Todo : can't convert value for "+value +" class:"+requiredType);
            }
        }
    }

    private <T> PropertyEditor findDefaultEditor(Class<T> requiredType) {
        PropertyEditor editor = this.getDefaultEditor(requiredType);
        if(editor == null){
            throw new RuntimeException("Editor for "+ requiredType +" has not been implemented");
        }
        return editor;
    }

    private <T> PropertyEditor getDefaultEditor(Class<T> requiredType) {
        if (this.editorSupportMap.isEmpty()) {
            setEditorSupportMap();
        }
        return this.editorSupportMap.get(requiredType);
    }

    private void setEditorSupportMap() {
        // Spring's CustomBooleanEditor accepts more flag values than the JDK's default editor.
        this.editorSupportMap.put(boolean.class, new CustomBooleanEditor(false));
        this.editorSupportMap.put(Boolean.class, new CustomBooleanEditor(true));

        // The JDK does not contain default editors for number wrapper types!
        // Override JDK primitive number editors with our own CustomNumberEditor.
		/*this.defaultEditors.put(byte.class, new CustomNumberEditor(Byte.class, false));
		this.defaultEditors.put(Byte.class, new CustomNumberEditor(Byte.class, true));
		this.defaultEditors.put(short.class, new CustomNumberEditor(Short.class, false));
		this.defaultEditors.put(Short.class, new CustomNumberEditor(Short.class, true));*/
        this.editorSupportMap.put(int.class, new CustomNumberEditor(Integer.class, false));
        this.editorSupportMap.put(Integer.class, new CustomNumberEditor(Integer.class, true));
		/*this.defaultEditors.put(long.class, new CustomNumberEditor(Long.class, false));
		this.defaultEditors.put(Long.class, new CustomNumberEditor(Long.class, true));
		this.defaultEditors.put(float.class, new CustomNumberEditor(Float.class, false));
		this.defaultEditors.put(Float.class, new CustomNumberEditor(Float.class, true));
		this.defaultEditors.put(double.class, new CustomNumberEditor(Double.class, false));
		this.defaultEditors.put(Double.class, new CustomNumberEditor(Double.class, true));
		this.defaultEditors.put(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
		this.defaultEditors.put(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));*/
    }
}
