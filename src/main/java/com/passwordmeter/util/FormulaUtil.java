package com.passwordmeter.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

import com.passwordmeter.formula.Bonus;
import com.passwordmeter.formula.BonusType;
import com.passwordmeter.formula.Formula;
import com.passwordmeter.formula.Requirements;

/**
 * Utility class responsible for provide a {@link List} containing all the classes
 * that implements the {@link Formula} interface.
 * 
 * @author Luiz Guilherme Paro
 * @see Formula
 */
@Component
public final class FormulaUtil {
    
    /**
     * Method that dynamically searches for all the classes that implement the {@link Formula} interface,
     * creates it and adds in the returned {@link List} implementation.
     * It uses the Spring utility class {@link ClassPathScanningCandidateComponentProvider} to search for
     * candidates of {@link Formula} interface based on its package declaration.
     * 
     * @return list containing all the defined {@link Formula}s in application.
     */
    public List<Formula> allFormulas() {
        final List<Formula> formulas = new ArrayList<>();
        final List<Formula> additionFormulas = new ArrayList<>();
        
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(Formula.class));
        
        Set<BeanDefinition> components = provider.findCandidateComponents(Formula.class.getPackage().getName());
        for (BeanDefinition component : components) {
            try {
                Class<?> formula = Class.forName(component.getBeanClassName());
                if(formula.isAssignableFrom(Requirements.class)) {
                    continue;
                }
                
                if(formula.isAnnotationPresent(Bonus.class)) {
                    Class<?>[] interfaces = formula.getInterfaces();
                    if(interfaces.length > 0) {
                        
                        if(interfaces[0].isAssignableFrom(Formula.class)) {
                            Formula instance = (Formula) formula.newInstance();
                            Bonus annotation = formula.getAnnotation(Bonus.class);
                            
                            if(annotation.type().equals(BonusType.ADDITION)) {
                                additionFormulas.add(instance);
                            }
                            
                            formulas.add(instance);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        formulas.add(new Requirements(additionFormulas));
        
        return Collections.unmodifiableList(formulas);
    }
}