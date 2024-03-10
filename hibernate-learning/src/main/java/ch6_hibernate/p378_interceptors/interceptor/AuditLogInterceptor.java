package ch6_hibernate.p378_interceptors.interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Component
public class AuditLogInterceptor extends EmptyInterceptor {

    // вызывается, когда экземпляр сущности становится хранимым.
    @Override
    public boolean onSave(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        return false;
    }

    // вызывается, если во время выталкивания контекста хранения в экземпляре
    // сущности будут обнаружены изменения
    @Override
    public boolean onFlushDirty(
            Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
        return false;
    }

    @Override
    public boolean onLoad(
            Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        return false;
    }

}
