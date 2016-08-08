package com.passwordmeter.formula;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;

@Component
public final class FormulaUtil {

    public List<Formula> additionFormulas() {
        return Arrays.asList(new NumberOfCharacters(),
                new UppercaseLetters(),
                new LowercaseLetters(),
                new Numbers(),
                new Symbols());
    }
    
    public List<Formula> allFormulas() {
        final List<Formula> formulas = new ArrayList<>();
        
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AssignableTypeFilter(Formula.class));
        
        Set<BeanDefinition> components = provider.findCandidateComponents(Formula.class.getPackage().getName());
        for (BeanDefinition component : components) {
            try {
                Class<?> formula = Class.forName(component.getBeanClassName());
                
                if(formula.isAssignableFrom(Requirements.class)) {
                    continue;
                }
                
                Class<?>[] interfaces = formula.getInterfaces();
                if(interfaces.length > 0) {
                    if(interfaces[0].isAssignableFrom(Formula.class)) {
                        formulas.add((Formula) formula.newInstance());
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        formulas.add(new Requirements(this.additionFormulas()));
        
        return formulas;
    }
}