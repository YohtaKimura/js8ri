package ch04.ex02;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.function.Supplier;

public class PropertyWrapper<T> {
    private Supplier<Property<T>> supplier;
    private T value = null;
    private Property<T> property;

    public PropertyWrapper(Supplier<Property<T>> supplier) {
        supplier = supplier;
    }

    public final void setValue(T value) {
        if (property == null) {
            this.value = value;
        } else {
            property.setValue(value);
        }
    }

    public final T getValue() {
        if (property != null) {
            return property.getValue();
        }
        return value;
    }

    public final Property<T> getProperty() {
        if (property != null) {
            return property;
        }

        getPropertyFromSupplier();
        property.setValue(value);
        value = null;
        return property;
    }

    private Property<T> getPropertyFromSupplier() {
        return supplier.get();
    }

    public static void main(String[] args) {
        // Run as debug
        Supplier<Property<String>> supplier = () -> {
            return new Property<String>() {
                private String value;
                @Override
                public void bind(ObservableValue<? extends String> observableValue) {
                }
                @Override
                public void unbind() {
                }
                @Override
                public boolean isBound() {
                    return false;
                }
                @Override
                public void bindBidirectional(Property<String> property) {
                }
                @Override
                public void unbindBidirectional(Property<String> property) {
                }
                @Override
                public Object getBean() {
                    return null;
                }
                @Override
                public String getName() {
                    return null;
                }
                @Override
                public void addListener(ChangeListener<? super String> changeListener) {
                }
                @Override
                public void removeListener(ChangeListener<? super String> changeListener) {
                }
                @Override
                public String getValue() {
                    return value;
                }
                @Override
                public void addListener(InvalidationListener invalidationListener) {
                }
                @Override
                public void removeListener(InvalidationListener invalidationListener) {
                }
                @Override
                public void setValue(String s) {
                    value = s;
                }
            };
        };
        PropertyWrapper<String> pw = new PropertyWrapper<> (supplier);
        pw.setValue("test");
        pw.getValue();
        pw.getProperty();
    }
}
