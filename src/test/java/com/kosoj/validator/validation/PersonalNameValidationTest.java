package com.kosoj.validator.validation;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import com.kosoj.validator.IEntityValidator;
import com.kosoj.validator.ValidationResult;
import com.kosoj.validator.dummy.entity.PersonalName;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * User: machain
 * Date: 19.9.13
 * Time: 10:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:META-INF/test-validator-app.xml")
public class PersonalNameValidationTest {

    @Autowired
    private IEntityValidator validator;

    @Test
    public void validate_Name_Ok() {
        final PersonalName entity = getName();

        final ValidationResult<PersonalName> result = validator.validate(entity);
        assertTrue(result.isOk());
    }

    @Test
    public void validate_Name_Mandatory() {
        final PersonalName entity = getName();
        entity.setFirstname(null);
        entity.setSurname(null);

        ValidationResult<PersonalName> result = validator.validate(entity);
        assertEquals(2, result.size());
        assertTrue(result.containsId("NME001"));
        assertTrue(result.containsId("NME005"));

        entity.setFirstname(" ");
        entity.setSurname("");
        result = validator.validate(entity);
        assertEquals(2, result.size());
        assertTrue(result.containsId("NME001"));
        assertTrue(result.containsId("NME005"));
    }

    @Test
    public void validate_Name_RegEx() {
        final PersonalName entity = getName();
        entity.setFirstname(entity.getFirstname().toLowerCase());
        entity.setSurname(entity.getSurname().toLowerCase());

        final ValidationResult<PersonalName> result = validator.validate(entity);
        assertEquals(2, result.size());
        assertTrue(result.containsId("NME002"));
        assertTrue(result.containsId("NME006"));
    }

    @Test
    public void validate_Name_MinLength() {
        final PersonalName entity = getName();
        entity.setFirstname("M");
        entity.setSurname("M");

        final ValidationResult<PersonalName> result = validator.validate(entity);
        assertTrue(result.containsId("NME003"));
        assertTrue(result.containsId("NME007"));
    }

    @Test
    public void validate_Name_MaxLength() {
        final PersonalName entity = getName();
        entity.setFirstname(StringUtils.repeat("M", 41));
        entity.setSurname(StringUtils.repeat("M", 41));

        final ValidationResult<PersonalName> result = validator.validate(entity);
        assertTrue(result.containsId("NME004"));
        assertTrue(result.containsId("NME008"));
    }


    // TOOLS
    public static PersonalName getName() {
        return new PersonalName("Phan", "Tom");
    }

}
