package br.com.zup.desafiomercadolivre.config.validacao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId,Object> {

    @PersistenceContext
    EntityManager manager;
    private Class<?> clazz;
    private String domainAttribute;


    public void initialize(ExistsId params) {
        clazz = params.domainClass();
        domainAttribute = params.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String sql = String.format("select 1 from %s where %s = :id", clazz.getName(), domainAttribute);
        Query query = manager.createQuery(sql);
        query.setParameter("id", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um " + clazz + "com o atributo "
                + domainAttribute + " = " + value);
        return list.size() == 1;
    }
}
