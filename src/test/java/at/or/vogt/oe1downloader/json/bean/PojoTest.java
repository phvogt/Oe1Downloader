package at.or.vogt.oe1downloader.json.bean;

import org.junit.jupiter.api.Test;

import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

class PojoTest {

    private static final Validator ACCESSOR_VALIDATOR = ValidatorBuilder
            .create()
            .with(new GetterTester())
            .with(new SetterTester())
            .build();

    public static void validateAccessors(final Class<?> clazz) {
        ACCESSOR_VALIDATOR.validate(PojoClassFactory.getPojoClass(clazz));
    }

    @Test
    void testBeanGetterSetter() {
        PojoTest.validateAccessors(Program.class);
        PojoTest.validateAccessors(Broadcast.class);
        PojoTest.validateAccessors(Image.class);
        PojoTest.validateAccessors(Mark.class);
        PojoTest.validateAccessors(Stream.class);
        PojoTest.validateAccessors(Version.class);
    }

}
