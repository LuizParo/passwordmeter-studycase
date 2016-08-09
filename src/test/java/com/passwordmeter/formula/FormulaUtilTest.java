package com.passwordmeter.formula;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.passwordmeter.util.FormulaUtil;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FormulaUtilTest {
    
    @Autowired
    private FormulaUtil formulaUtil;

    @Test
    public void shouldInstantiateAllFormulasDefinedInApplication() {
        List<Formula> formulas = this.formulaUtil.allFormulas();
        
        Assert.assertNotNull(formulas);
        Assert.assertFalse(formulas.isEmpty());
        Assert.assertEquals(16, formulas.size());
        formulas.forEach((formula) -> {
            Assert.assertTrue(formula.getClass().getInterfaces()[0].isAssignableFrom(Formula.class));
        });
    }
}