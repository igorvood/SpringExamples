package ru.vood.spring.restFullStack.wrapRequest;

import org.junit.Assert;
import org.junit.Test;
import ru.vood.spring.restFullStack.consts.CommonStatus;

import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Arrays.asList;

public class WrapperForControllerTest {

    final Supplier<WrapperForService.WrappedObjectForService<String>> supplierError = () -> {
        if (1 == 1)
            throw new RuntimeException();
        return null;
    };
    final Function<String, ErrorMessage> validationFunctionOk = s -> null;
    final Function<String, ErrorMessage> validationFunctionError = s -> new ErrorMessage(asList("Error"));
    final Function<String, WrapperForService.WrappedObjectForService<String>> longListFunctionError = s -> {
        if (1 == 1)
            throw new RuntimeException();
        return supplierError.get();
    };
    WrapperForController wrapperForController = new WrapperForController() {
    };
    WrapperForService wrapperForService = new WrapperForService() {
    };
    final Supplier<WrapperForService.WrappedObjectForService<String>> supplierOk = () -> wrapperForService.wrapObject(() -> "1");
    final Function<String, WrapperForService.WrappedObjectForService<String>> longListFunctionOk = s -> supplierOk.get();

    @Test
    public void wrapObject() {
        final WrapperForController.WrappedObjectForRest<String> stringWrappedObjectForRest = wrapperForController.wrapObject(supplierOk);
        Assert.assertEquals(CommonStatus.OK, stringWrappedObjectForRest.getStatus());
        Assert.assertNotNull(stringWrappedObjectForRest.getContext());
        Assert.assertNotNull(stringWrappedObjectForRest.getContext().getDate());
        Assert.assertNull(stringWrappedObjectForRest.getContext().getContext());
        Assert.assertNotNull(stringWrappedObjectForRest.getContext().getPage());
        Assert.assertEquals(stringWrappedObjectForRest.getObjectList().size(), stringWrappedObjectForRest.getContext().getPage().getTotalRecords());
        Assert.assertEquals(null, stringWrappedObjectForRest.getErrorMessages());
        Assert.assertArrayEquals(supplierOk.get().getObjectList().toArray(), stringWrappedObjectForRest.getObjectList().toArray());
    }

    @Test
    public void wrapObjectError() {
        final WrapperForController.WrappedObjectForRest<String> stringWrappedObjectForRest = wrapperForController.wrapObject(supplierError);
        Assert.assertEquals(CommonStatus.ERROR, stringWrappedObjectForRest.getStatus());
        Assert.assertNull(stringWrappedObjectForRest.getContext());
        Assert.assertNull(stringWrappedObjectForRest.getObjectList());
        Assert.assertNotNull(stringWrappedObjectForRest.getErrorMessages());
        Assert.assertEquals(1, stringWrappedObjectForRest.getErrorMessages().getErrorMessages().size());
    }

    @Test
    public void validateAndWrapObject() {
        final WrapperForController.WrappedObjectForRest<String> wrappedObjectForRest = wrapperForController.validateAndWrapObject(validationFunctionOk, longListFunctionOk, null);
        Assert.assertEquals(CommonStatus.OK, wrappedObjectForRest.getStatus());
        Assert.assertNotNull(wrappedObjectForRest.getContext());
        Assert.assertNotNull(wrappedObjectForRest.getContext().getDate());
        Assert.assertNull(wrappedObjectForRest.getContext().getContext());
        Assert.assertNotNull(wrappedObjectForRest.getContext().getPage());
        Assert.assertEquals(1, wrappedObjectForRest.getContext().getPage().getTotalRecords());
        Assert.assertNull(wrappedObjectForRest.getErrorMessages());

        Assert.assertNotNull(wrappedObjectForRest.getObjectList());
        Assert.assertEquals(1, wrappedObjectForRest.getObjectList().size());
        Assert.assertArrayEquals(longListFunctionOk.apply(null).getObjectList().toArray(), wrappedObjectForRest.getObjectList().toArray());
    }

    @Test
    public void validateAndWrapError() {
        final WrapperForController.WrappedObjectForRest<String> wrappedObjectForRest = wrapperForController.validateAndWrapObject(validationFunctionOk, longListFunctionError, null);
        Assert.assertEquals(CommonStatus.ERROR, wrappedObjectForRest.getStatus());
        Assert.assertNull(wrappedObjectForRest.getContext());
        Assert.assertNotNull(wrappedObjectForRest.getErrorMessages());
        Assert.assertNotNull(wrappedObjectForRest.getErrorMessages().getErrorMessages());
        Assert.assertEquals(1, wrappedObjectForRest.getErrorMessages().getErrorMessages().size());
        Assert.assertNull(wrappedObjectForRest.getObjectList());
    }

    @Test
    public void isNotValidOk() {
        final boolean notValid = wrapperForController.isNotValid(null);
        Assert.assertEquals(false, notValid);
    }

    @Test
    public void isNotValidOk1() {
        final boolean notValid = wrapperForController.isNotValid(new ErrorMessage());
        Assert.assertEquals(false, notValid);
    }

    @Test
    public void isNotValidError() {
        final boolean notValid = wrapperForController.isNotValid(new ErrorMessage(asList("1")));
        Assert.assertEquals(true, notValid);
    }

    @Test
    public void validError() {
    }

    @Test
    public void getError() {
        final WrapperForController.WrappedObjectForRest<Object> error = wrapperForController.getError(new RuntimeException());
        Assert.assertEquals(CommonStatus.ERROR, error.getStatus());
        Assert.assertNull(error.getContext());
        Assert.assertNull(error.getObjectList());
        Assert.assertNotNull(error.getErrorMessages());
        Assert.assertNotNull(error.getErrorMessages().getErrorMessages());
        Assert.assertEquals(1, error.getErrorMessages().getErrorMessages().size());
    }

    @Test
    public void getOk() {
        final WrapperForController.WrappedObjectForRest<String> ok = wrapperForController.getOk(supplierOk.get());
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