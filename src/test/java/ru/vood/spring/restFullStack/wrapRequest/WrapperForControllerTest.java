package ru.vood.spring.restFullStack.wrapRequest;

import org.junit.Assert;
import org.junit.Test;
import ru.vood.spring.restFullStack.consts.CommonStatus;

import java.util.Collections;
import java.util.function.Function;
import java.util.function.Supplier;

public class WrapperForControllerTest {

    final Supplier<WrapperForService.WrappedObject<String>> supplierError = () -> {
        throw new RuntimeException();
    };
    final Function<String, ErrorMessage> validationFunctionOk = s -> null;
    final Function<String, ErrorMessage> validationFunctionError = s -> new ErrorMessage(Collections.singletonList("Error"));
    final Function<String, WrapperForService.WrappedObject<String>> longListFunctionError = s -> {
        throw new RuntimeException();
    };
    WrapperForController wrapperForController = new WrapperForController() {
    };
    WrapperForService wrapperForService = new WrapperForService() {
    };
    final Supplier<WrapperForService.WrappedObject<String>> supplierOk = () -> wrapperForService.wrapObject(() -> "1");
    final Function<String, WrapperForService.WrappedObject<String>> longListFunctionOk = s -> supplierOk.get();

    @Test
    public void wrapObject() {
        final WrapperForController.WrappedObject<String> stringWrappedObject = wrapperForController.wrapObject(supplierOk);
        Assert.assertEquals(CommonStatus.OK, stringWrappedObject.getStatus());
        Assert.assertNotNull(stringWrappedObject.getContext());
        Assert.assertNotNull(stringWrappedObject.getContext().getDate());
        Assert.assertNull(stringWrappedObject.getContext().getContext());
        Assert.assertNotNull(stringWrappedObject.getContext().getPage());
        Assert.assertEquals(stringWrappedObject.getObjectList().size(), stringWrappedObject.getContext().getPage().getTotalRecords());
        Assert.assertNull(stringWrappedObject.getErrorMessages());
        Assert.assertArrayEquals(supplierOk.get().getObjectList().toArray(), stringWrappedObject.getObjectList().toArray());
    }

    @Test
    public void wrapObjectError() {
        final WrapperForController.WrappedObject<String> stringWrappedObject = wrapperForController.wrapObject(supplierError);
        Assert.assertEquals(CommonStatus.ERROR, stringWrappedObject.getStatus());
        Assert.assertNull(stringWrappedObject.getContext());
        Assert.assertNull(stringWrappedObject.getObjectList());
        Assert.assertNotNull(stringWrappedObject.getErrorMessages());
        Assert.assertEquals(1, stringWrappedObject.getErrorMessages().getErrorMessages().size());
    }

    @Test
    public void validateAndWrapObject() {
        final WrapperForController.WrappedObject<String> wrappedObject = wrapperForController.validateAndWrapObject(validationFunctionOk, longListFunctionOk, null);
        Assert.assertEquals(CommonStatus.OK, wrappedObject.getStatus());
        Assert.assertNotNull(wrappedObject.getContext());
        Assert.assertNotNull(wrappedObject.getContext().getDate());
        Assert.assertNull(wrappedObject.getContext().getContext());
        Assert.assertNotNull(wrappedObject.getContext().getPage());
        Assert.assertEquals(1, wrappedObject.getContext().getPage().getTotalRecords());
        Assert.assertNull(wrappedObject.getErrorMessages());

        Assert.assertNotNull(wrappedObject.getObjectList());
        Assert.assertEquals(1, wrappedObject.getObjectList().size());
        Assert.assertArrayEquals(longListFunctionOk.apply(null).getObjectList().toArray(), wrappedObject.getObjectList().toArray());
    }

    @Test
    public void validateAndWrapError() {
        final WrapperForController.WrappedObject<String> wrappedObject = wrapperForController.validateAndWrapObject(validationFunctionOk, longListFunctionError, null);
        Assert.assertEquals(CommonStatus.ERROR, wrappedObject.getStatus());
        Assert.assertNull(wrappedObject.getContext());
        Assert.assertNotNull(wrappedObject.getErrorMessages());
        Assert.assertNotNull(wrappedObject.getErrorMessages().getErrorMessages());
        Assert.assertEquals(1, wrappedObject.getErrorMessages().getErrorMessages().size());
        Assert.assertNull(wrappedObject.getObjectList());
    }

    @Test
    public void isNotValidOk() {
        final boolean notValid = wrapperForController.isNotValid(null);
        Assert.assertFalse(notValid);
    }

    @Test
    public void isNotValidOk1() {
        final boolean notValid = wrapperForController.isNotValid(new ErrorMessage());
        Assert.assertFalse(notValid);
    }

    @Test
    public void isNotValidError() {
        final boolean notValid = wrapperForController.isNotValid(new ErrorMessage(Collections.singletonList("1")));
        Assert.assertTrue(notValid);
    }

    @Test
    public void validError() {
    }

    @Test
    public void getError() {
        final WrapperForController.WrappedObject<Object> error = wrapperForController.getError(new RuntimeException());
        Assert.assertEquals(CommonStatus.ERROR, error.getStatus());
        Assert.assertNull(error.getContext());
        Assert.assertNull(error.getObjectList());
        Assert.assertNotNull(error.getErrorMessages());
        Assert.assertNotNull(error.getErrorMessages().getErrorMessages());
        Assert.assertEquals(1, error.getErrorMessages().getErrorMessages().size());
    }

    @Test
    public void getOk() {
        final WrapperForController.WrappedObject<String> ok = wrapperForController.getOk(supplierOk.get());
        Assert.assertEquals(CommonStatus.OK, ok.getStatus());
        Assert.assertNotNull(ok.getContext());
        Assert.assertNotNull(ok.getContext().getPage());
        Assert.assertEquals(1, ok.getContext().getPage().getTotalRecords());
        Assert.assertNotNull(ok.getContext().getDate());
        Assert.assertNull(ok.getContext().getContext());
        Assert.assertNull(ok.getErrorMessages());

        Assert.assertNotNull(ok.getObjectList());
        Assert.assertEquals(1, ok.getObjectList().size());
        Assert.assertArrayEquals(supplierOk.get().getObjectList().toArray(), ok.getObjectList().toArray());
    }
}